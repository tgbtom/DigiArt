package com.fdmgroup.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.fdmgroup.dao.JDBCConnection;
import com.fdmgroup.dao.JPAAuctionDao;
import com.fdmgroup.dao.JPAProductDao;
import com.fdmgroup.model.Auction;
import com.fdmgroup.model.Bid;
import com.fdmgroup.model.Product;
import com.fdmgroup.model.ProductStatus;
import com.fdmgroup.model.User;
import com.fdmgroup.view.AuctionView;

public class AuctionController{

	private AuctionView auctionView;
	private Scanner scanner;
	private JPAProductDao jpaProductDao;
	private JPAAuctionDao jpaAuctionDao;

	private double startingPrice, bidIncrease;
	private int productId;
	private Date startTime, endTime;

	public AuctionController(Scanner scanner) {
		super();
		this.scanner = scanner;
		this.jpaProductDao = new JPAProductDao();
		this.jpaAuctionDao = new JPAAuctionDao();
	}


	public AuctionView getAuctionView() {
		return auctionView;
	}

	public void setAuctionView(AuctionView auctionView) {
		this.auctionView = auctionView;
	}

	public void showAll() {
		List<Auction> auctions = jpaAuctionDao.findAll();
		auctionView.displayAll(auctions);
	}
	
	public void showMine(User user) {
		
	}
	
	public void createAuction(User user) {
		promptAuctionCreation();
		
		Product product = jpaProductDao.findById(productId);
		if(product.getOwner().getId() == user.getId() && product.getStatus() == ProductStatus.AVAILABLE) {
			this.startTime = new Date(); 
			//end 45 seconds later
			this.endTime = new Date(System.currentTimeMillis() + 45000);
			
			System.out.println(endTime);
			//Wed Apr 01 11:47:24 EDT 2020
			
			Auction auction = new Auction(product, startTime, endTime, bidIncrease, user);
			auction.addBid(new Bid(startingPrice, user, auction));
			
			auction = jpaAuctionDao.create(auction);
			jpaProductDao.updateStatus(product.getProduct_id(), ProductStatus.AUCTIONED);
			
			scheduleJob(auction);

		}
		else {
			System.out.println("You are not able to auction this product");
		}
	}
	
	private void scheduleJob(Auction auction) {
		Connection conn = JDBCConnection.openConnection();
		String actionQuery = "UPDATE products SET status = ''SOLD'' WHERE product_id = " + auction.getProduct().getProduct_id() + ";";
		String jobQuery = "BEGIN " + 
			"    DBMS_SCHEDULER.CREATE_JOB (" + 
			"            job_name => '\"AUCTION_END"+ auction.getAuctionId() +"\"'," + 
			"            job_type => 'PLSQL_BLOCK'," + 
			"            job_action => '" + 
			actionQuery + "  '," + 
			"            number_of_arguments => 0," + 
			"            start_date => TO_DATE('"+ auction.getEndTime().toString().substring(4, 19) + auction.getEndTime().toString().substring(23) +"','Mon dd HH24:mi:ss yyyy')," + 
			"            repeat_interval => NULL," + 
			"            end_date => NULL," + 
			"            enabled => FALSE," + 
			"            auto_drop => TRUE," + 
			"            comments => '');" + 
			"    DBMS_SCHEDULER.SET_ATTRIBUTE( " + 
			"             name => '\"AUCTION_END"+ auction.getAuctionId() +"\"', " + 
			"             attribute => 'logging_level', value => DBMS_SCHEDULER.LOGGING_OFF);" + 
			"    DBMS_SCHEDULER.enable(" + 
			"             name => '\"AUCTION_END"+ auction.getAuctionId() +"\"');" + 
			"END;";

		try {
			PreparedStatement ps = conn.prepareStatement(jobQuery);
			ps.execute();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void promptAuctionCreation() {
		System.out.println("Enter the id of the product you wish to put up for auction: ");
		productId = Integer.parseInt(scanner.nextLine());
		System.out.println("Enter starting price: ");
		startingPrice = Double.parseDouble(scanner.nextLine());
		System.out.println("Enter minimum bid increase: ");
		bidIncrease = Double.parseDouble(scanner.nextLine());
	}
}

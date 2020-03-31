package com.fdmgroup.controller;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

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
	private int productId, duration, auctionId;
	private Date startTime, endTime;

	public AuctionController(Scanner scanner) {
		super();
		this.scanner = scanner;
		this.jpaProductDao = new JPAProductDao();
		this.jpaAuctionDao = new JPAAuctionDao();
	}
	
	public AuctionController(Scanner scanner, int auctionId) {
		super();
		this.scanner = scanner;
		this.auctionId = auctionId;
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
			this.endTime = new Date(); //ADD 'duration' to this date variable
			
			Auction auction = new Auction(product, startTime, endTime, new Bid(startingPrice, user), bidIncrease);
			
			auction = jdbcAuctionDao.create(auction);
			jdbcAuctionDao.updateStatus(product, "Auctioned");
			
			System.out.println("Passed Auction id is: "+ auction.getAuctionId());
			scheduleJob(auction);

		}
		else {
			System.out.println("You are not able to auction this product");
		}
	}
	
	private void scheduleJob(Auction auction) {
		Connection conn = JDBCConnection.getInstance();
		System.out.println(auction.getEnd_time().truncatedTo(ChronoUnit.SECONDS).toString().replace('T', ' '));
		
		String query = "BEGIN\r\n" + 
				"    DBMS_SCHEDULER.CREATE_JOB (\r\n" + 
				"            job_name => '\"AUCTION_END"+ auction.getAuctionId() +"\"',\r\n" + 
				"            job_type => 'PLSQL_BLOCK',\r\n" + 
				"            job_action => '\r\n" + 
				"  UPDATE inventory SET status = ''testaga'' WHERE product_id = "+ auction.getProduct().getProduct_id() +";\r\n" + 
				"  \r\n" + 
				"  commit;\r\n',\r\n" + 
				"            number_of_arguments => 0,\r\n" + 
				"            start_date => TO_TIMESTAMP('"+ auction.getEnd_time().truncatedTo(ChronoUnit.SECONDS).toString().replace('T', ' ') +"','YYYY-MM-DD HH24:MI:SS'),\r\n" + 
				"            repeat_interval => NULL,\r\n" + 
				"            end_date => NULL,\r\n" + 
				"            enabled => FALSE,\r\n" + 
				"            auto_drop => TRUE,\r\n" + 
				"            comments => '');\r\n" + 
				"\r\n" + 
				"         \r\n" + 
				"     \r\n" + 
				" \r\n" + 
				"    DBMS_SCHEDULER.SET_ATTRIBUTE( \r\n" + 
				"             name => '\"AUCTION_END"+ auction.getAuctionId() +"\"', \r\n" + 
				"             attribute => 'logging_level', value => DBMS_SCHEDULER.LOGGING_OFF);\r\n" + 
				"    DBMS_SCHEDULER.enable(\r\n" + 
				"             name => '\"AUCTION_END"+ auction.getAuctionId() +"\"');\r\n" + 
				"END;";
		
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(query);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		System.out.println("Enter the desired duration of the Auction in seconds: ");
		duration = Integer.parseInt(scanner.nextLine());
	}

	@Override
	public void run() {
		Auction updatedAuction = jdbcAuctionDao.findById(auctionId);
		
		if (updatedAuction.getCurrent_bid().getBidder().getId() == updatedAuction.getProduct().getCreator().getId()) {
			jdbcAuctionDao.updateStatus(updatedAuction.getProduct(), "Available");
			
			System.out.println("No bids were placed and the item was returned to creators inventory");
		}
		else {
			jdbcAuctionDao.updateStatus(updatedAuction.getProduct(), "Sold");
			jdbcProductDao.addToInventory(updatedAuction.getCurrent_bid().getBidder(), updatedAuction.getProduct(), "Bought");
			
			System.out.println("The item was bought by: "+ updatedAuction.getCurrent_bid().getBidder().getUsername() + ", for: "+ updatedAuction.getCurrent_bid().getValue());
		}
	}

}

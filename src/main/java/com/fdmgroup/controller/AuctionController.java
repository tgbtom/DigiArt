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
	private JPAProductDao jpaProductDao;
	private JPAAuctionDao jpaAuctionDao;

	private double startingPrice, bidIncrease;
	private int productId;
	private Date startTime, endTime;

	public AuctionController() {
		super();
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
	
	public void createAuction(Auction auction, User user) {
		
		Product product = jpaProductDao.findById(auction.getProduct().getProduct_id());
		if(product.getOwner().getId() == user.getId() && product.getStatus() == ProductStatus.AVAILABLE) {
			
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
		String query = "BEGIN\r\n" + 
				"    DBMS_SCHEDULER.CREATE_JOB (\r\n" + 
				"            job_name => '\"AUCTION_END"+ auction.getAuctionId() +"\"',\r\n" + 
				"            job_type => 'PLSQL_BLOCK',\r\n" + 
				"            job_action => '\r\n" + 
				"  end_this_auction("+ auction.getAuctionId() +");\r\n" + 
				"  \r\n" + 
				"  commit;\r\n',\r\n" + 
				"            number_of_arguments => 0,\r\n" + 
				"            start_date => TO_DATE('"+ auction.getEndTime().toString().substring(4, 19) + auction.getEndTime().toString().substring(23) +"','Mon dd HH24:mi:ss yyyy')," + 
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
			PreparedStatement ps = conn.prepareStatement(query);
			ps.execute();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

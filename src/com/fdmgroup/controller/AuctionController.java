package com.fdmgroup.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import com.fdmgroup.dao.JDBCAuctionDao;
import com.fdmgroup.dao.JDBCConnection;
import com.fdmgroup.dao.JDBCProductDao;
import com.fdmgroup.model.Auction;
import com.fdmgroup.model.Bid;
import com.fdmgroup.model.Product;
import com.fdmgroup.model.User;
import com.fdmgroup.view.AuctionView;

public class AuctionController {

	private AuctionView auctionView;
	private Scanner scanner;
	private JDBCProductDao jdbcProductDao;
	private JDBCAuctionDao jdbcAuctionDao;

	private double startingPrice, bidIncrease;
	private int duration, productId;
	private LocalDateTime startTime, endTime;

	public AuctionController(Scanner scanner) {
		super();
		this.scanner = scanner;
		this.jdbcProductDao = new JDBCProductDao();
		this.jdbcAuctionDao = new JDBCAuctionDao();
	}
	
	public AuctionView getAuctionView() {
		return auctionView;
	}

	public void setAuctionView(AuctionView auctionView) {
		this.auctionView = auctionView;
	}

	public void showAll() {
		ArrayList<Auction> auctions = JDBCAuctionDao.getAll();
		auctionView.displayAll(auctions);
	}
	
	public void showMine(User user) {
		
	}
	
	public void createAuction(User user) {
		promptAuctionCreation();
		
		Product product = jdbcProductDao.findById(productId);
		if(product.getOwner().getId() == user.getId()) {
			this.startTime = LocalDateTime.now(); 
			this.endTime = LocalDateTime.now().plusHours(duration);
			
//			Auction auction = new Auction(product, startTime, endTime, new Bid(startingPrice, user), bidIncrease);
			
//			jdbcAuctionDao.create();
			
			//now update status of inventory from Available to Auctioned
		}
	}
	
	private void promptAuctionCreation() {
		System.out.println("Enter the id of the product you wish to put up for auction: ");
		productId = Integer.parseInt(scanner.nextLine());
		System.out.println("Enter starting price: ");
		startingPrice = Double.parseDouble(scanner.nextLine());
		System.out.println("Enter minimum bid increase: ");
		bidIncrease = Double.parseDouble(scanner.nextLine());
		System.out.println("Enter the desired duration of the Auction in hours: ");
		duration = Integer.parseInt(scanner.nextLine());
	}

	public boolean verifyAvailable(Product product) {
		return false;
	}

}

package com.fdmgroup.controller;



import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import com.fdmgroup.dao.JDBCAuctionDao;
import com.fdmgroup.dao.JDBCProductDao;
import com.fdmgroup.model.Auction;
import com.fdmgroup.model.Bid;
import com.fdmgroup.model.Product;
import com.fdmgroup.model.User;
import com.fdmgroup.view.AuctionView;

public class AuctionController extends TimerTask{

	private AuctionView auctionView;
	private Scanner scanner;
	private JDBCProductDao jdbcProductDao;
	private JDBCAuctionDao jdbcAuctionDao;

	private double startingPrice, bidIncrease;
	private int productId, duration, auctionId;
	private LocalDateTime startTime, endTime;

	public AuctionController(Scanner scanner) {
		super();
		this.scanner = scanner;
		this.jdbcProductDao = new JDBCProductDao();
		this.jdbcAuctionDao = new JDBCAuctionDao();
	}
	
	public AuctionController(Scanner scanner, int auctionId) {
		super();
		this.scanner = scanner;
		this.auctionId = auctionId;
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
		if(product.getOwner().getId() == user.getId() && jdbcProductDao.getStatus(product).equals("Available")) {
			this.startTime = LocalDateTime.now(); 
			this.endTime = LocalDateTime.now().plusSeconds(duration);
			
			Auction auction = new Auction(product, startTime, endTime, new Bid(startingPrice, user), bidIncrease);
			
			auction = jdbcAuctionDao.create(auction);
			jdbcAuctionDao.updateStatus(product, "Auctioned");
			
			Timer timer = new Timer();
//			Comparator<Integer> sortByDesc = (o1, o2) -> o2.compareTo(o1);
			System.out.println("Passed Auction id is: "+ auction.getAuctionId());
			timer.schedule(new AuctionController(scanner, auction.getAuctionId()), Date.from(endTime.atZone(ZoneId.systemDefault()).toInstant()));
			jdbcAuctionDao.testBid(auction);
		}
		else {
			System.out.println("You are not able to auction this product");
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
		System.out.println("Finding Auction #:"+ auctionId);
		if(jdbcAuctionDao.equals(null)) {
			System.out.println("jdbcauctiondao is null :(");
		}
		Auction updatedAuction = jdbcAuctionDao.findById(auctionId);
		System.out.println(updatedAuction.getCurrent_bid().getValue());
		System.out.println(updatedAuction.getCurrent_bid().getBidder().getUsername());
		System.out.println("Auction Ended, sold for: "+ updatedAuction.getCurrent_bid().getValue() + ", bought by: "+ updatedAuction.getCurrent_bid().getBidder().getUsername());
	}

}

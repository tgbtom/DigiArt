package com.fdmgroup.controller;

import java.util.ArrayList;

import com.fdmgroup.dao.AuctionDao;
import com.fdmgroup.model.Auction;
import com.fdmgroup.view.AuctionView;

public class AuctionController {

	private AuctionView auctionView;

	public AuctionController() {
		super();
	}
	
	public AuctionView getAuctionView() {
		return auctionView;
	}

	public void setAuctionView(AuctionView auctionView) {
		this.auctionView = auctionView;
	}

	public void showAll() {
		ArrayList<Auction> auctions = AuctionDao.getAll();
		auctionView.displayAll(auctions);
	}

}

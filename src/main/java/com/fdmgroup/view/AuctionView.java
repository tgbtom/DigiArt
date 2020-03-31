package com.fdmgroup.view;

import java.util.List;

import com.fdmgroup.model.Auction;

public class AuctionView {

	public void displayAll(List<Auction> auctions) {
		for (Auction auction : auctions) {
			System.out.println(auction.toString());
		}
	}

}

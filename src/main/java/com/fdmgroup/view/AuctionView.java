package com.fdmgroup.view;

import java.util.ArrayList;

import com.fdmgroup.model.Auction;

public class AuctionView {

	public void displayAll(ArrayList<Auction> auctions) {
		for (Auction auction : auctions) {
			System.out.println(auction.toString());
		}
	}

}

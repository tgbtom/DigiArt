package com.fdmgroup.dao;

import java.util.ArrayList;
import java.util.List;

import com.fdmgroup.model.Auction;
import com.fdmgroup.model.IStorable;

public class AuctionDao implements IStorage, IEditable{

	public static ArrayList<Auction> getAll() {
		ArrayList<Auction> auctions = new ArrayList<>();
		auctions.add(new Auction());
		auctions.add(new Auction());
		return auctions;
	}

	@Override
	public IStorable create(IStorable t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IStorable findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IStorable update(IStorable t) {
		// TODO Auto-generated method stub
		return null;
	}

}

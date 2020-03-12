package com.fdmgroup.dao;

import java.util.ArrayList;
import java.util.List;

import com.fdmgroup.model.Auction;
import com.fdmgroup.model.Product;
import com.fdmgroup.model.User;

public class JDBCAuctionDao implements IAuctionDao{

	public static ArrayList<Auction> getAll() {
		ArrayList<Auction> auctions = new ArrayList<>();
		auctions.add(new Auction());
		auctions.add(new Auction());
		return auctions;
	}

	@Override
	public Auction update(Auction t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Auction create(Auction t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Auction findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Auction> findAll(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Product> findMine(User user) {
		// TODO Auto-generated method stub
		return null;
	}

}

package com.fdmgroup.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import com.fdmgroup.model.Auction;
import com.fdmgroup.model.Product;
import com.fdmgroup.model.User;

public class JPAAuctionDao implements IAuctionDao{

	@Override
	public Auction create(Auction t) {
		EntityManager em = JPAConnection.getInstance().createEntityManager();
		
		return null;
	}

	@Override
	public Auction findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Auction> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Auction update(Auction t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Auction t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Product> findMine(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateStatus(Product product, String newStatus) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void placeBid(Auction auction, User user, double bidAmount) {
		// TODO Auto-generated method stub
		
	}

}

package com.fdmgroup.dao;

import java.util.ArrayList;

import com.fdmgroup.model.Auction;
import com.fdmgroup.model.Product;
import com.fdmgroup.model.User;

public interface IAuctionDao extends IStorage<Auction>, IEditable<Auction>, IRemovable<Auction>{
	public ArrayList<Product> findMine(User user);
	public void updateStatus(Product product, String newStatus);
	public void placeBid(Auction auction, User user, double bidAmount);
}

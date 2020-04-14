package com.fdmgroup.dao;

import java.util.List;

import com.fdmgroup.model.Auction;
import com.fdmgroup.model.User;

public interface IAuctionDao extends IStorage<Auction>, IEditable<Auction>, IRemovable<Auction>{
	public List<Auction> findMine(User user);
}

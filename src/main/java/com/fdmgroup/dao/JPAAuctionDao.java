package com.fdmgroup.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.fdmgroup.model.Auction;
import com.fdmgroup.model.Bid;
import com.fdmgroup.model.Product;
import com.fdmgroup.model.User;

public class JPAAuctionDao implements IAuctionDao{

	public List<Auction> findByProduct(Product p){
		EntityManager em = JPAConnection.getInstance().createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Auction> query = em.createNamedQuery("auction.findByProduct", Auction.class);
		query.setParameter("product", p);
		List<Auction> auctions = query.getResultList();
		em.close();
		return auctions;
	}
	
	@Override
	public Auction create(Auction auction) {
		EntityManager em = JPAConnection.getInstance().createEntityManager();
		em.getTransaction().begin();
		em.persist(auction);
		em.persist(auction.getBids().get(0));
		em.getTransaction().commit();
		em.close();
		return auction;
	}

	@Override
	public Auction findById(int id) {
		EntityManager em = JPAConnection.getInstance().createEntityManager();
		em.getTransaction().begin();
		Auction auction = em.find(Auction.class, id);
		em.close();
		return auction;
		
	}

	@Override
	public List<Auction> findAll() {
		EntityManager em = JPAConnection.getInstance().createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Auction> query = em.createNamedQuery("auction.findAll", Auction.class);
		List<Auction> auctions = query.getResultList();
		return auctions;
	}

	@Override
	public Auction update(Auction auction) {
		EntityManager em = JPAConnection.getInstance().createEntityManager();
		em.getTransaction().begin();
		for (Bid b : auction.getBids()) {
			em.merge(b);
		}
		auction = em.merge(auction);
		em.getTransaction().commit();
		em.close();
		
		return auction;
	}

	@Override
	public void remove(Auction auction) {
		EntityManager em = JPAConnection.getInstance().createEntityManager();
		em.getTransaction().begin();
		auction = em.merge(auction);

		em.remove(auction);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public List<Auction> findMine(User user) {
		EntityManager em = JPAConnection.getInstance().createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Auction> query = em.createNamedQuery("auction.findMine", Auction.class);
		query.setParameter("seller", user);
		List<Auction> auctions = query.getResultList();
		return auctions;
	}

	@Override
	public void placeBid(Auction auction, User user, double bidAmount) {
		// TODO Auto-generated method stub	
	}
	
	public double getInitialPrice(Auction auction) {
		List<Bid> bids = auction.getBids();
		double lowestBid = bids.get(0).getValue();
		for (Bid b : bids) {
			lowestBid = Math.min(lowestBid, b.getValue());
		}
		return lowestBid;
	}
	
	public Bid getHighestBid(Auction auction) {
		List<Bid> bids = auction.getBids();
		double highestValue = bids.get(0).getValue();
		Bid highestBid = bids.get(0);
		for (Bid b : bids) {
			if(b.getValue() > highestValue) {
				highestValue = b.getValue();
				highestBid = b;
			}
		}
		return highestBid;
	}
}

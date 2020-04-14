package com.fdmgroup.dao;


import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Comparator;
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
	
	public List<Auction> findTopAuctions(){
		//All auctions, ordered from highest number of bids to lowest, omit ended auctions
		List<Auction> listAuctions = findAll();
		Collections.sort(listAuctions, new Comparator<Auction>() {
			@Override
			public int compare(Auction o1, Auction o2) {
				return o2.getBids().size() - o1.getBids().size();
			}
		});
		return listAuctions;
	}
	
	public List<Auction> findRecentAuctions(){
		//All auctions ordered from latest start date to earliest
		List<Auction> listAuctions = findAll();
		Collections.sort(listAuctions, new Comparator<Auction>() {
			@Override
			public int compare(Auction o1, Auction o2) {
				return o2.getStartTime().compareTo(o1.getStartTime());
			}
		});
		return listAuctions;
	}
	
	public List<Auction> findExpiringAuctions(){
		//All auctions ordered from earliest end date to latest
		List<Auction> listAuctions = findAll();
		Collections.sort(listAuctions, new Comparator<Auction>() {
			@Override
			public int compare(Auction o1, Auction o2) {
				return o1.getEndTime().compareTo(o2.getEndTime());
			}
		});
		return listAuctions;
	}
	
	public List<Auction> findExpiringAuctionsReverse(){
		//All auctions ordered from latest end date to earliest
		List<Auction> listAuctions = findAll();
		Collections.sort(listAuctions, new Comparator<Auction>() {
			@Override
			public int compare(Auction o1, Auction o2) {
				return o2.getEndTime().compareTo(o1.getEndTime());
			}
		});
		return listAuctions;
	}
	
	public List<Auction> allAuctionsOrderedByProductName(){
		List<Auction> listAuctions = findAll();
		Collections.sort(listAuctions, new Comparator<Auction>() {
			@Override
			public int compare(Auction o1, Auction o2) {
				return o1.getProduct().getName().compareToIgnoreCase(o2.getProduct().getName());
			}
		});
		return listAuctions;
	}
	
	public String getTimeTillExpire(Auction auction) {
		
		LocalDateTime dateTime = LocalDateTime.now();
		LocalDateTime endDateTime = LocalDateTime.parse(auction.getEndTime().toString().substring(0, 19).replace(' ', 'T'));
		Long days = dateTime.until(endDateTime, ChronoUnit.DAYS);
		Long hours = dateTime.until(endDateTime, ChronoUnit.HOURS) - days * 24;
		Long mins = dateTime.until(endDateTime, ChronoUnit.MINUTES) - ((hours * 60) + (days * 24 * 60));
		Long seconds = dateTime.until(endDateTime, ChronoUnit.SECONDS) - ((mins * 60) + (hours * 60 * 60) + (days * 24 * 60 * 60));
		
		String daysString = (days < 1) ? "" : padNum(Long.toString(days)) + " days, ";
		
		String result = daysString +
				padNum(Long.toString(hours)) + " hours and " +
				padNum(Long.toString(mins)) + " minutes";
		
			return result;
	}
	
	private String padNum(String n) {
		return n.length() < 2 ? "0" + n : n;
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

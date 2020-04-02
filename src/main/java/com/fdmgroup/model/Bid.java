package com.fdmgroup.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity(name = "bid")
@Table(name = "bids")
public class Bid implements IStorable{
	
	@Id
	@Column(name = "bid_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bid_seq")
	@SequenceGenerator(name = "bid_seq", sequenceName = "bid_id_seq", allocationSize = 1)
	private int id;
	
	@Column(name = "amount")
	private double value;
	
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	private User bidder;
	
	@ManyToOne
	@JoinColumn(name = "auction_id", referencedColumnName = "auction_id")
	private Auction auction;
	
	public Bid() {
		super();
	}
	
	public Bid(double value, User bidder, Auction auction) {
		super();
		this.value = value;
		this.bidder = bidder;
		this.auction = auction;
	}
	
	public double getValue() {
		return value;
	}
	
	public void setValue(double value) {
		this.value = value;
	}
	
	public User getBidder() {
		return bidder;
	}
	
	public void setBidder(User bidder) {
		this.bidder = bidder;
	}
}

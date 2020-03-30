package com.fdmgroup.model;

public class Bid {
	private double value;
	private User bidder;
	public Bid() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Bid(double value, User bidder) {
		super();
		this.value = value;
		this.bidder = bidder;
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

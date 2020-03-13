package com.fdmgroup.model;

import java.time.LocalDateTime;

public class Auction implements IStorable{
	private Product product;
	private int auctionId;
	private LocalDateTime start_time, end_time;
	private Bid current_bid;
	private double min_increase;

	public Auction() {
		this(new Product(), LocalDateTime.now(), LocalDateTime.now(), new Bid(), 5.0d);
	}
	public Auction(Product product, LocalDateTime start_time, LocalDateTime end_time, Bid current_bid, double min_increase) {
		super();
		this.product = product;
		this.start_time = start_time;
		this.end_time = end_time;
		this.current_bid = current_bid;
		this.min_increase = min_increase;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public LocalDateTime getStart_time() {
		return start_time;
	}
	public void setStart_time(LocalDateTime start_time) {
		this.start_time = start_time;
	}
	public LocalDateTime getEnd_time() {
		return end_time;
	}
	public void setEnd_time(LocalDateTime end_time) {
		this.end_time = end_time;
	}
	public Bid getCurrent_bid() {
		return current_bid;
	}
	public void setCurrent_bid(Bid current_bid) {
		this.current_bid = current_bid;
	}
	public double getMin_increase() {
		return min_increase;
	}
	public void setMin_increase(double min_increase) {
		this.min_increase = min_increase;
	}
	
	public int getAuctionId() {
		return auctionId;
	}
	public void setAuctionId(int auctionId) {
		this.auctionId = auctionId;
	}
	@Override
	public String toString() {
		return "Auction [product=" + product + ", start_time=" + start_time + ", end_time=" + end_time
				+ ", current_bid=" + current_bid + ", min_increase=" + min_increase + "]";
	}
	
}

package com.fdmgroup.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "auction")
@Table(name = "auctions")
public class Auction implements IStorable{
	
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "auction_seq")
	@SequenceGenerator(name = "auction_seq", sequenceName = "auction_id_seq", allocationSize = 1)
	@Id
	@Column(name = "auction_id")
	private int auctionId;
	
	@OneToOne
	@JoinColumn(name = "product_id", referencedColumnName = "product_id")
	private Product product;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start_time")
	private Date startTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "end_time")
	private Date endTime;
	
	@Column(name = "min_bid_increase")
	private double minIncrease;

	@OneToMany(mappedBy = "auction")
	private List<Bid> bids;
	
	@ManyToOne
	@JoinColumn(name = "seller_id", referencedColumnName = "user_id")
	private User seller;

	public Auction() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Auction(Product product, Date startTime, Date endTime, double minIncrease, User seller) {
		super();
		this.product = product;
		this.startTime = startTime;
		this.endTime = endTime;
		this.minIncrease = minIncrease;
		this.bids = new ArrayList<Bid>();
		this.seller = seller;
	}

	public Auction(Product product, Date startTime, Date endTime, double minIncrease, List<Bid> bids, User seller) {
		super();
		this.product = product;
		this.startTime = startTime;
		this.endTime = endTime;
		this.minIncrease = minIncrease;
		this.bids = bids;
	}

	public int getAuctionId() {
		return auctionId;
	}

	public void setAuctionId(int auctionId) {
		this.auctionId = auctionId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public double getMinIncrease() {
		return minIncrease;
	}

	public void setMinIncrease(double minIncrease) {
		this.minIncrease = minIncrease;
	}

	public List<Bid> getBids() {
		return bids;
	}

	public void setBids(List<Bid> bids) {
		this.bids = bids;
	}
	
	public void addBid(Bid bid) {
		bids.add(bid);
	}

	public User getSeller() {
		return seller;
	}

	public void setSeller(User seller) {
		this.seller = seller;
	}
	
}

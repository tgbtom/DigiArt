package com.fdmgroup.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "auction")
@Table(name = "auctions")
@NamedQueries({
	@NamedQuery(name = "auction.findAll", query = "SELECT a from auction a"),
	@NamedQuery(name = "auction.findMine", query = "SELECT a from auction a WHERE a.seller = :seller"),
	@NamedQuery(name = "auction.findByProduct", query = "SELECT a from auction a WHERE a.product = :product")
})
public class Auction implements IStorable, Comparable<Auction>{
	
	@Id
	@Column(name = "auction_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "auction_seq")
	@SequenceGenerator(name = "auction_seq", sequenceName = "auction_id_seq", allocationSize = 1)
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

	@Enumerated(EnumType.STRING)
	@Column
	private ContractType contract;
	
	@OneToMany(mappedBy = "auction")
	private List<Bid> bids;
	
	@ManyToOne
	@JoinColumn(name = "seller_id", referencedColumnName = "user_id")
	private User seller;

	public Auction() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Auction(Product product, Date startTime, Date endTime, double minIncrease, User seller, ContractType contract) {
		super();
		this.product = product;
		this.startTime = startTime;
		this.endTime = endTime;
		this.minIncrease = minIncrease;
		this.bids = new ArrayList<Bid>();
		this.seller = seller;
		this.contract = contract;
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

	public ContractType getContract() {
		return contract;
	}

	public void setContract(ContractType contract) {
		this.contract = contract;
	}
	
	@Override
	public String toString() {
		return "Auction [auctionId=" + auctionId + ", product=" + product + ", startTime=" + startTime + ", endTime="
				+ endTime + ", minIncrease=" + minIncrease + ", contract=" + contract + ", bids=" + bids + ", seller="
				+ seller + "]";
	}

	public String getContractString() {
		switch (this.contract) {
		case one :
			return "1 Year / Exclusive";
		case three :
			return "3 Years / Exclusive";
		case five :
			return "5 Years / Exclusive";
		case ten :
			return "10 Years / Exclusive";
		case unlimited :
			return "Permanent / Exclusive";
		default:
			return "Permanent / Exclusive";
		}
	}

	@Override
	public int compareTo(Auction o) {
		return  o.getBids().size() - bids.size();
	}
	
}

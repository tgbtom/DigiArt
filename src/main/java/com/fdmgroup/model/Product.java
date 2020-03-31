package com.fdmgroup.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SecondaryTable;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@NamedQuery(name="product.findMine", query="SELECT p FROM product p WHERE p.creator = :creator")

@Entity (name = "product")
@Table (name = "products")
public class Product implements IStorable{
	
	@Id
	@Column (name = "product_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prod_seq")
	@SequenceGenerator(name = "prod_seq", sequenceName = "product_id_seq", allocationSize = 1)
	private int id;
	
	@Column
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "creator_id", referencedColumnName = "user_id", nullable = false)
	private User creator;
	
	@ManyToOne
	@JoinColumn(name = "owner_id", referencedColumnName = "user_id", nullable = false)
	private User owner;
	
	@Enumerated(EnumType.STRING)
	@Column
	private ProductStatus status;
	
	@OneToOne(mappedBy = "product")
	private Auction auction;

	public Product() {
		super();
		this.name = "Default";
		this.creator = (new User());
	}
	
	public Product(String name, User creator) {
		super();
		this.name = name;
		this.creator = creator;
		this.owner = creator;
		this.status = ProductStatus.AVAILABLE;
	}

	public Product(String name, User creator, User owner) {
		super();
		this.name = name;
		this.creator = creator;
		this.owner = owner;
	}
	
	public Product(int product_id, String name, User creator) {
		super();
		this.id = product_id;
		this.name = name;
		this.creator = creator;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}
	
	public int getProduct_id() {
		return id;
	}

	public void setProduct_id(int product_id) {
		this.id = product_id;
	}

	public void setStatus(ProductStatus status) {
		this.status = status;
	}

	public ProductStatus getStatus() {
		return this.status;
	}
	
	public Auction getAuction() {
		return auction;
	}

	public void setAuction(Auction auction) {
		this.auction = auction;
	}
	
	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}
	
}

package com.fdmgroup.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity (name = "product")
@Table (name = "products")
public class Product implements IStorable{
	
	@Id
	@Column (name = "product_id")
	private int id;
	
	@Column
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "creator_id", referencedColumnName = "user_id", nullable = false)
	private User creator;
	
	private String status;

	public Product() {
		super();
		this.name = "Default";
		this.creator = (new User());
	}
	
	public Product(String name, User creator) {
		super();
		this.name = name;
		this.creator = creator;
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

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return this.status;
	}
}

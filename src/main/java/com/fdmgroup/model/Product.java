package com.fdmgroup.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "product")
@Table(name = "products")
public class Product implements IStorable{
	private String status;
	private User owner, creator;
	
	@Id
	@Column
	private int product_id;
	@Column
	private int ownerId;
	@Column
	private String name;

	public Product() {
		super();
		this.name = "Default";
		this.owner = (new User());
		this.creator = (new User());
	}
	
	public Product(String name, User owner, User creator) {
		super();
		this.name = name;
		this.owner = owner;
		this.ownerId = owner.getId();
		this.creator = creator;
	}
	
	public Product(int product_id, String name, User owner, User creator) {
		super();
		this.product_id = product_id;
		this.name = name;
		this.owner = owner;
		this.ownerId = owner.getId();
		this.creator = creator;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	
	
	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
		this.ownerId = owner.getId();
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}
	
	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return this.status;
	}
}

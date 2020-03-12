package com.fdmgroup.model;

public class Product implements IStorable{
	private String name;
	private User owner, creator;
	private int product_id;

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
		this.creator = creator;
	}
	
	public Product(int product_id, String name, User owner, User creator) {
		super();
		this.product_id = product_id;
		this.name = name;
		this.owner = owner;
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
}

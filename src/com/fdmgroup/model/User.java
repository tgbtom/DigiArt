package com.fdmgroup.model;

public class User implements IStorable{
	private int id;
	private String username;
	private String firstname;
	private String lastname;
	private String role, salt;
	private double wallet;
	public User() {
		super();
	}
	public User(int id, String username, String firstname, String lastname, String role, double wallet, String salt) {
		super();
		this.id = id;
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.wallet = wallet;
		this.role = role;
		this.salt = salt;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public double getWallet() {
		return wallet;
	}
	public void setWallet(double wallet) {
		this.wallet = wallet;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", firstname=" + firstname
				+ ", lastname=" + lastname + "]";
	}
	public String getSalt() {
		return salt;
	}
	
}

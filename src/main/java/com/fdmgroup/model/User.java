package com.fdmgroup.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@NamedQueries({
	@NamedQuery(name="user.findByUsername", query="SELECT u FROM user u WHERE u.username = :username"),
	@NamedQuery(name="user.authenticate", query="SELECT COUNT(u) FROM user u WHERE u.username = :username AND u.password = :password"),
	@NamedQuery(name="user.findAll", query="SELECT u FROM user u WHERE role NOT LIKE 'Admin'")
})


@Entity(name = "user")
@Table(name = "users")
public class User implements IStorable{
	
	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
	@SequenceGenerator(name="user_seq", sequenceName = "user_id_seq", allocationSize = 1)
	private int id;
	
	@Column
	private String username;
	
	@Column
	private String password;
	
	@Column(name = "first_name")
	private String firstname;
	
	@Column(name = "last_name")
	private String lastname;
	
	@Column
	private String email;
	
	@Column
	private String role;
	
	@Column
	private String salt;
	
	@Column
	private double wallet;
	
	@Column
	private int locked;
	
	@OneToMany(mappedBy = "owner")
	private List<Product> productsOwned;
	
	@OneToMany(mappedBy = "creator")
	private List<Product> productsCreated;
	
	@OneToMany(mappedBy = "bidder")
	private List<Bid> bids;
	
	@OneToMany(mappedBy = "seller")
	private List<Auction> auctions;

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
	
	public User(String username, String password, String firstname, String lastname, String email, String role, double wallet, String salt) {
		super();
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
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
	
	public String getSalt() {
		return salt;
	}
	
	public void setSalt(String salt) {
		this.salt = salt;
	}
  
	public List<Product> getProductsOwned() {
		return productsOwned;
	}

	public List<Product> getProductsCreated() {
		return productsCreated;
	}

	public List<Auction> getAuctions() {
		return auctions;
	}

	public void setAuctions(List<Auction> auctions) {
		this.auctions = auctions;
	}
	
	public void addAuction(Auction auction) {
		auctions.add(auction);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getLocked() {
		return locked;
	}

	public void setLocked(int locked) {
		this.locked = locked;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", firstname=" + firstname
				+ ", lastname=" + lastname + "]";
	}
	
}

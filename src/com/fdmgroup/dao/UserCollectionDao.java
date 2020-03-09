package com.fdmgroup.dao;

import java.util.ArrayList;
import java.util.List;

import com.fdmgroup.model.User;

public class UserCollectionDao implements IUserDao{
	private List<User> users = null;
	public UserCollectionDao() {
		super();
		users = new ArrayList<>();
		users.add(new User(1, "jdoe", "1234", "John", "Doe", "admin", 100.0d));
		users.add(new User(2, "asmith", "5678", "Alex", "Smith", "member", 150.0d));
		users.add(new User(3, "mjane", "2345", "Mary", "Jane", "member", 100.0d));
	}

	@Override
	public User create(User t) {
		User foundUser = findByUsername(t.getUsername());
		if(foundUser == null) {
			users.add(t);
			return t;
		}
		return null;
	}

	@Override
	public User findById(int id) {
		for (User user : users) {
			if(user.getId() == id)
				return user;
		}
		return null;
	}

	@Override
	public List<User> findAll() {
		return users;
	}

	@Override
	public User update(User t) {
		User user = findById(t.getId());
		if(t.getPassword() != null && !t.getPassword().isEmpty())
				user.setPassword(t.getPassword());
		return user;
	}

	@Override
	public boolean remove(User t) {
		return users.remove(t);
	}

	@Override
	public User findByUsername(String name) {
		for (User user : users) {
			if(user.getUsername().equals(name))
				return user;
		}
		return null;
	}

	@Override
	public List<User> findByFirstname(String firstname) {
		List<User> userByFirstname = new ArrayList<>();
		for (User user : users) {
			if(user.getFirstname().equals(firstname))
				userByFirstname.add(user);
		}
		return userByFirstname;
	}

	public void depositWallet(User user, double depositAmount) {
		user.setWallet(user.getWallet() + depositAmount);
	}

	public void withdrawWallet(User user, double withdrawAmount) {
		user.setWallet((user.getWallet() - withdrawAmount < 0) ? user.getWallet() : user.getWallet() - withdrawAmount);
	}

}

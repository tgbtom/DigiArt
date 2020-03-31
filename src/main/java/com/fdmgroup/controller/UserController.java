package com.fdmgroup.controller;


import com.fdmgroup.dao.JPAUserDao;
import com.fdmgroup.model.User;
import com.fdmgroup.view.UserView;

public class UserController {
	
	private UserView userView;
	private JPAUserDao jpaUserDao;
	
	public UserController() {
		super();
		this.jpaUserDao = new JPAUserDao();
	}

	public void showProfile(User user) {
		userView.showProfile(user);
	}

	public UserView getUserView() {
		return userView;
	}

	public void setUserView(UserView userView) {
		this.userView = userView;
	}

	public void deposit(User user, double depositAmount) {
		jpaUserDao.deposit(user, depositAmount);
		System.out.println("You deposited "+ depositAmount + " into your wallet.");
	}

	public void withdraw(User user, double withdrawAmount) {
		if(user.getWallet() - withdrawAmount >= 0) {
			jpaUserDao.withdraw(user, withdrawAmount);
			System.out.println("You withdrew "+ withdrawAmount + " from your wallet.");
		}
		else {
			System.out.println("Not enough funds in your wallet to make the requested withdrawal.");
		}
	}
	
}

package com.fdmgroup.controller;

import com.fdmgroup.dao.UserCollectionDao;
import com.fdmgroup.model.User;
import com.fdmgroup.view.UserView;

public class UserController {
	
	private UserView userView;
	
	public UserController() {
		super();
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
		new UserCollectionDao().depositWallet(user, depositAmount);
		System.out.println("You deposited "+ depositAmount + " into your wallet.");
	}

	public void withdraw(User user, double withdrawAmount) {
		new UserCollectionDao().withdrawWallet(user, withdrawAmount);
		if(user.getWallet() - withdrawAmount > 0) {
			System.out.println("You withdrew "+ withdrawAmount + " into your wallet.");
		}
		else {
			System.out.println("Not enough funds in your wallet to make the requested withdrawal.");
		}
	}
	
}

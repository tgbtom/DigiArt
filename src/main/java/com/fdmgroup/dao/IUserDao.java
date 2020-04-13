package com.fdmgroup.dao;

import java.util.Optional;

import com.fdmgroup.model.User;

public interface IUserDao extends IStorage<User>, IEditable<User>, IRemovable<User>{
	public Optional<User> findByUsername(String name);
	public void deposit(User user, double amountToDeposit);
	public boolean withdraw(User user, double amountToWithdraw);
	
	//Test multiple commit
}

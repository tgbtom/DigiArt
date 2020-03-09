package com.fdmgroup.dao;

import java.util.List;
import com.fdmgroup.model.User;

public interface IUserDao extends IStorage<User>, IEditable<User>, IRemovable<User>{
	public User findByUsername(String name);
	public List<User> findByFirstname(String firstname);
}

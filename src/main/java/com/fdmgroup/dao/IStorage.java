package com.fdmgroup.dao;


import java.util.List;

import com.fdmgroup.model.IStorable;
import com.fdmgroup.model.User;

public interface IStorage<T extends IStorable> {
	public T create(T t);
	public T findById(int id);
	public List<T> findAll();
}

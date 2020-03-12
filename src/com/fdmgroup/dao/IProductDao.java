package com.fdmgroup.dao;

import java.util.ArrayList;

import com.fdmgroup.model.Product;
import com.fdmgroup.model.User;


public interface IProductDao extends IStorage<Product>{
	public ArrayList<Product> findMine(User user);
}

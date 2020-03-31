package com.fdmgroup.dao;

import java.util.List;

import com.fdmgroup.model.Product;
import com.fdmgroup.model.User;


public interface IProductDao extends IStorage<Product>, IRemovable<Product>, IEditable<Product>{
	public List<Product> findMine(User user);
	public boolean addToInventory(User user, Product product, String status);
}

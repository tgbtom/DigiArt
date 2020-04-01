package com.fdmgroup.dao;

import java.util.List;

import com.fdmgroup.model.Product;
import com.fdmgroup.model.ProductStatus;
import com.fdmgroup.model.User;


public interface IProductDao extends IStorage<Product>, IRemovable<Product>, IEditable<Product>{
	public List<Product> findMine(User user);
	public void updateStatus(int productId, ProductStatus newStatus);
}

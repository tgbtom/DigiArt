package com.fdmgroup.dao;

import java.util.List;

import com.fdmgroup.model.Product;
import com.fdmgroup.model.User;

public class JPAProductDao implements IStorage<Product>, IEditable<Product>, IRemovable<Product>{

	@Override
	public boolean remove(Product t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Product update(Product t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product create(Product t) {
		
		return null;
	}

	@Override
	public Product findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> findAll(User user) {
		// TODO Auto-generated method stub
		return null;
	}

}

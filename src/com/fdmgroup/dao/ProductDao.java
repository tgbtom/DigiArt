package com.fdmgroup.dao;

import java.util.ArrayList;

import com.fdmgroup.model.IStorable;
import com.fdmgroup.model.Product;

public class ProductDao implements IStorage<Product>{

	@Override
	public ArrayList<Product> findAll() {
		ArrayList<Product> products = new ArrayList<>();
		products.add(new Product());
		products.add(new Product());
		products.add(new Product());
		return products;
	}

	@Override
	public Product create(Product t) {
		System.out.println(t.toString());
		return null;
	}

	@Override
	public Product findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
}

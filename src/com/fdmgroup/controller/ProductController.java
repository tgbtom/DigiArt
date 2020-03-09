package com.fdmgroup.controller;

import java.util.List;

import com.fdmgroup.dao.ProductDao;
import com.fdmgroup.model.Product;
import com.fdmgroup.view.ProductView;

public class ProductController {

	private ProductView productView;

	public ProductController() {
		super();
	}

	public ProductView getProductView() {
		return productView;
	}

	public void setProductView(ProductView productView) {
		this.productView = productView;
	}

	public void addNewProduct() {
		productView.showAddForm();
	}
	
	public void insertProduct(Product product) {
		new ProductDao().create(product);
	}

	public void showAll() {
		productView.showAll();
	}
	
}

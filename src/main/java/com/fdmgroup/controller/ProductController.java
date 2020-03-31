package com.fdmgroup.controller;

import com.fdmgroup.dao.JPAProductDao;
import com.fdmgroup.model.Product;
import com.fdmgroup.model.User;
import com.fdmgroup.view.ProductView;

public class ProductController {

	private ProductView productView;
	private JPAProductDao jpaProductDao;

	public ProductController() {
		super();
		this.jpaProductDao = new JPAProductDao();
	}

	public ProductView getProductView() {
		return productView;
	}

	public void setProductView(ProductView productView) {
		this.productView = productView;
	}

	public void addNewProduct(User user) {
		productView.showAddForm(user);
	}
	
	public void insertProduct(User user, String productName) {
		Product myProduct = new Product(productName, user);
		jpaProductDao.create(myProduct);
	}

	public void showAll(User user) {
		productView.showAll(user);
	}
	
}

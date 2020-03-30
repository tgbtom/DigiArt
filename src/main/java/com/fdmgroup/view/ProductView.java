package com.fdmgroup.view;

import java.util.ArrayList;

import java.util.Scanner;

import com.fdmgroup.controller.ProductController;
import com.fdmgroup.dao.JDBCProductDao;
import com.fdmgroup.model.Product;
import com.fdmgroup.model.User;

public class ProductView {

	private Scanner scanner;
	private ProductController productController;
	private JDBCProductDao jdbcProductDao;
	
	public ProductView(Scanner scanner) {
		super();
		this.scanner = scanner;
		this.jdbcProductDao = new JDBCProductDao();
	}

	public ProductController getProductController() {
		return productController;
	}

	public void setProductController(ProductController productController) {
		this.productController = productController;
	}

	public void showAddForm(User user) {
		System.out.println("Enter new Product Name: ");
		String productName = scanner.nextLine();
		productController.insertProduct(user, productName);
	}
	
	public void showAll(User user) {
		ArrayList<Product> products = jdbcProductDao.findMine(user);
		System.out.println("Product Id | Name");
		for (Product product : products) {
			System.out.println(product.getProduct_id() + "| " + product.getName());
		}
		
	}
	
}

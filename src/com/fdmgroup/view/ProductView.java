package com.fdmgroup.view;

import java.util.ArrayList;

import java.util.Scanner;

import com.fdmgroup.controller.ProductController;
import com.fdmgroup.dao.ProductDao;
import com.fdmgroup.model.Product;

public class ProductView {

	private Scanner scanner;
	private ProductController productController;
	
	public ProductView(Scanner scanner) {
		super();
		this.scanner = scanner;
	}

	public ProductController getProductController() {
		return productController;
	}

	public void setProductController(ProductController productController) {
		this.productController = productController;
	}

	public void showAddForm() {
		System.out.println("Enter new Product Name: ");
		Product product = new Product();
		product.setName(scanner.nextLine());
		productController.insertProduct(product);
	}
	
	public void showAll() {
		ArrayList<Product> products = new ProductDao().findAll();
		for (Product product : products) {
			System.out.println(product.getName());
		}
		
	}
	
}

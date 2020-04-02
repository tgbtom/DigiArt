package com.fdmgroup.test;

import com.fdmgroup.dao.JPAProductDao;
import com.fdmgroup.dao.JPAUserDao;
import com.fdmgroup.model.Product;
import com.fdmgroup.model.ProductStatus;

public class ProductTest {

	private static JPAProductDao jpaProductDao = new JPAProductDao();
	private static JPAUserDao jpaUserDao = new JPAUserDao();
	
	public static void main(String[] args) {
//		create();
//		update();
//		delete();
	}
	
	private static void create() {
		Product product = new Product("Test Product 1", jpaUserDao.findById(1));
		jpaProductDao.create(product);
	}
	
	private static void update() {
		Product product = jpaProductDao.findById(20);
		product.setName("LG G8");
		product.setStatus(ProductStatus.SOLD);
		jpaProductDao.update(product);
	}
	
	private static void delete() {
		Product product = jpaProductDao.findById(20);
		jpaProductDao.remove(product);
	}
	
}

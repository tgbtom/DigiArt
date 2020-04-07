package com.fdmgroup.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.persistence.EntityManager;

import com.fdmgroup.dao.JPAConnection;
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
	
	public void insertProduct(User user, String productName, byte[] image, String description) {
		Product myProduct = new Product(productName, user, image, description);
		jpaProductDao.create(myProduct);
	}
	
	public void showProduct(int productId) {
		Product prod = jpaProductDao.findById(productId);
		
		EntityManager em = JPAConnection.getInstance().createEntityManager();
		em.getTransaction().begin();
		ByteArrayInputStream bais = new ByteArrayInputStream(prod.getImage());
		BufferedImage bi;
		try {
			bi = ImageIO.read(bais);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ImageIO.write(bi, "png", bos);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		em.close();
	}

	public void showAll(User user) {
		productView.showAll(user);
	}
	
}

package com.fdmgroup.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.fdmgroup.model.Product;
import com.fdmgroup.model.User;

public class JPAProductDao implements IProductDao{
	
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
	public Product create(Product product) {
		EntityManager em = JPAConnection.getInstance().createEntityManager();
		em.getTransaction().begin();
		em.persist(product);
		em.getTransaction().commit();
		em.close();
		return product;
	}

	@Override
	public Product findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> findMine(User user) {
		EntityManager em = JPAConnection.getInstance().createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Product> query = em.createNamedQuery("product.findMine", Product.class);
		query.setParameter("creator", user);
		List<Product> products = query.getResultList();
		return products;
	}

	@Override
	public boolean addToInventory(User user, Product product, String status) {
		// TODO Auto-generated method stub
		return false;
	}

}

package com.fdmgroup.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.fdmgroup.model.Auction;
import com.fdmgroup.model.Product;
import com.fdmgroup.model.ProductStatus;
import com.fdmgroup.model.User;

public class JPAProductDao implements IProductDao{
	
	@Override
	public void remove(Product product) {
		
		//remove any auctions that are on product x
		JPAAuctionDao jpaAuctionDao = new JPAAuctionDao();
		List<Auction> auctions = jpaAuctionDao.findByProduct(product);
		
		for (Auction a : auctions) {
			jpaAuctionDao.remove(a);
		}
		
		EntityManager em = JPAConnection.getInstance().createEntityManager();
		em.getTransaction().begin();
		product = em.merge(product);
		em.remove(product);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public Product update(Product product) {
		EntityManager em = JPAConnection.getInstance().createEntityManager();
		em.getTransaction().begin();
		product = em.merge(product);
		em.getTransaction().commit();
		em.close();
		
		return product;
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
		EntityManager em = JPAConnection.getInstance().createEntityManager();
		em.getTransaction().begin();
		Product product = em.find(Product.class, id);
		em.close();
		return product;
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
	public void updateStatus(int productId, ProductStatus newStatus) {
		EntityManager em = JPAConnection.getInstance().createEntityManager();
		em.getTransaction().begin();
		Product product = em.find(Product.class, productId);
		product.setStatus(newStatus);
		em.getTransaction().commit();
		em.close();
	}

}

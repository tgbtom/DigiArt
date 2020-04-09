package com.fdmgroup.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.fdmgroup.model.User;

public class JPAUserDao implements IUserDao{
	
	@Override
	public User create(User user) {
		EntityManager em = JPAConnection.getInstance().createEntityManager();
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
		em.close();
		
		return user;
	}

	@Override
	public User findById(int id) {
		EntityManager em = JPAConnection.getInstance().createEntityManager();
		em.getTransaction().begin();
		User user = em.find(User.class, id);
		em.close();
		return user;
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User update(User user) {
		EntityManager em = JPAConnection.getInstance().createEntityManager();
		em.getTransaction().begin();
		em.merge(user);
		em.getTransaction().commit();
		em.close();
		return user;
	}

	@Override
	public void remove(User t) {
		// TODO Auto-generated method stub
	}

	@Override
	public Optional<User> findByUsername(String name) {
		EntityManager em = JPAConnection.getInstance().createEntityManager();
		em.getTransaction().begin();
		TypedQuery<User> query = em.createNamedQuery("user.findByUsername", User.class);
		query.setParameter("username", name);
		User user = null;
		try {
			user = query.getSingleResult();
		} catch (NoResultException e) {}	
		return Optional.ofNullable(user);
	}

	@Override
	public void deposit(User user, double amountToDeposit) {
		EntityManager em = JPAConnection.getInstance().createEntityManager();
		em.getTransaction().begin();
		user = em.merge(user);
		user.setWallet(user.getWallet() + amountToDeposit);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public void withdraw(User user, double amountToWithdraw) {
		EntityManager em = JPAConnection.getInstance().createEntityManager();
		em.getTransaction().begin();
		user = em.merge(user);
		if (user.getWallet() - amountToWithdraw >= 0) {
			user.setWallet(user.getWallet() - amountToWithdraw);
			em.getTransaction().commit();
		}
		else {
			System.out.println("Not enough funds to withdraw the amount specified");
		}
		em.close();
	}

}

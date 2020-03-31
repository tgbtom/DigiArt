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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User update(User t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(User t) {
		// TODO Auto-generated method stub
		return false;
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
	public List<User> findByFirstname(String firstname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deposit(User user, double amountToDeposit) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void withdraw(User user, double amountToWithdraw) {
		// TODO Auto-generated method stub
		
	}

}

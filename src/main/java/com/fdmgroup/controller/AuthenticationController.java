package com.fdmgroup.controller;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.fdmgroup.dao.IUserDao;
import com.fdmgroup.dao.JPAConnection;
import com.fdmgroup.dao.JPAUserDao;
import com.fdmgroup.model.Password;
import com.fdmgroup.model.User;
import com.fdmgroup.view.DashboardView;
import com.fdmgroup.view.HomeView;

public class AuthenticationController {

	private IUserDao userDao;
	private DashboardView dashboardView;
	private HomeView homeView;
	
	private JPAUserDao jpaUserDao;
	
	public AuthenticationController() {
		super();
		this.userDao = new JPAUserDao();
	}

	public IUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	public DashboardView getDashboradView() {
		return dashboardView;
	}

	public void setDashboradView(DashboardView dashboradView) {
		this.dashboardView = dashboradView;
	}

	public HomeView getHomeView() {
		return homeView;
	}

	public void setHomeView(HomeView homeView) {
		this.homeView = homeView;
	}

	public boolean login(String username, String password) {
		Optional<User> user = userDao.findByUsername(username);
		if (user.isPresent()) {
			Password hashedPass = hashPassword(password, toByteArray(user.get().getSalt()));
			if(authenticateUser(username, hashedPass.getHashedPass())) {
				return true;
			}
		}
		System.out.println("user no exist");
		return false;
	}
	
	private boolean authenticateUser(String username, String hashedPass) {
		EntityManager em = JPAConnection.getInstance().createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Long> query = em.createNamedQuery("user.authenticate", java.lang.Long.class);
		query.setParameter("username", username);
		query.setParameter("password", hashedPass);
		try {
			if (query.getSingleResult() > 0) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		em.close();
		return false;
	}

	public void logout() {
		homeView.showInitialOptions();
	}
	
	public boolean register(String username, String password, String fname, String lname, String email) {

		if(userDao.findByUsername(username).isPresent()) {
			System.out.println("Username is already in use, please try something new!");
			return false;
		}
		else {
			Password passwd = hashPassword(password);
			String hashedPassword = passwd.getHashedPass();
			String salt = passwd.getSalt();
			
			User user = new User(username, hashedPassword, fname, lname, email, "Member", 0.00d, salt);
			userDao.create(user);
			return true;
		}
	}

	public Password hashPassword(String password) {
		MessageDigest md;
		Password output;
		try {
			md = MessageDigest.getInstance("SHA-256");
			
			SecureRandom random = new SecureRandom();
			byte[] salt = new byte[16];
			random.nextBytes(salt);
			
			md.update(salt);
			
			byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));
			
			StringBuilder sb = new StringBuilder();
			for (byte b : hashedPassword) {
				sb.append(String.format("%02x", b));
			}
			
			output = new Password(sb.toString(), Arrays.toString(salt));
			return output;
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private Password hashPassword(String password, byte[] salt) {
		MessageDigest md;
		Password output;
		try {
			md = MessageDigest.getInstance("SHA-256");
			
			md.update(salt);
			
			byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));
			
			StringBuilder sb = new StringBuilder();
			for (byte b : hashedPassword) {
				sb.append(String.format("%02x", b));
			}
			
			output = new Password(sb.toString(), Arrays.toString(salt));
			return output;
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	private static byte[] toByteArray(String salt) {
		String brokenSalt = salt.replace('[', 'A');
		brokenSalt = brokenSalt.replace(']', 'A');
		brokenSalt = brokenSalt.replaceAll("[A ]", "");
		
		String[] bytes = brokenSalt.split(",");
		byte[] newBytes = new byte[bytes.length];
		
		for (int i = 0; i < bytes.length; i++) {
			newBytes[i] = (byte) Integer.parseInt(bytes[i]);
		}
		return newBytes;
	}
}

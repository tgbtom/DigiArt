package com.fdmgroup.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAConnection {

	private static EntityManagerFactory emf;
	
	public static EntityManagerFactory getInstance() {
		if (emf == null) {
			emf = Persistence.createEntityManagerFactory("DigiArtPU");
		}
		return emf;
	}
	
}

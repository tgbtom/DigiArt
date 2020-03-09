package com.fdmgroup.controller;

import com.fdmgroup.dao.IUserDao;
import com.fdmgroup.model.User;
import com.fdmgroup.view.DashboardView;
import com.fdmgroup.view.HomeView;

public class AuthenticationController {

	private IUserDao userDao;
	private DashboardView dashboradView;
	private HomeView homeView;
	
	public AuthenticationController() {
		super();
	}

	public IUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	public DashboardView getDashboradView() {
		return dashboradView;
	}

	public void setDashboradView(DashboardView dashboradView) {
		this.dashboradView = dashboradView;
	}

	public HomeView getHomeView() {
		return homeView;
	}

	public void setHomeView(HomeView homeView) {
		this.homeView = homeView;
	}

	public void login(String username, String password) {
		User user = userDao.findByUsername(username);
		if(user != null && user.getPassword().equals(password)) {
			dashboradView.showDashboard(user);
			return;
		}
		homeView.showLoginOptions();
	}

	public void logout() {
		homeView.showInitialOptions();
	}

}

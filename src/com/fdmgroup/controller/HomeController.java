package com.fdmgroup.controller;

import com.fdmgroup.view.HomeView;

public class HomeController {
	private HomeView homeView;
	public HomeController() {
		super();
	}
	public HomeController(HomeView homeView) {
		super();
		this.homeView = homeView;
	}
	public HomeView getHomeView() {
		return homeView;
	}
	public void setHomeView(HomeView homeView) {
		this.homeView = homeView;
	}
	
	public void showHome() {
		homeView.showInitialOptions();
	}
}





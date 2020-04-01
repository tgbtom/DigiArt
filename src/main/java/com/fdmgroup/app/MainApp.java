package com.fdmgroup.app;

import java.util.Scanner;

import com.fdmgroup.controller.AuctionController;
import com.fdmgroup.controller.AuthenticationController;
import com.fdmgroup.controller.HomeController;
import com.fdmgroup.controller.ProductController;
import com.fdmgroup.controller.UserController;
import com.fdmgroup.dao.IUserDao;
import com.fdmgroup.dao.JPAUserDao;
import com.fdmgroup.view.AuctionView;
import com.fdmgroup.view.DashboardView;
import com.fdmgroup.view.HomeView;
import com.fdmgroup.view.ProductView;
import com.fdmgroup.view.UserView;

public class MainApp {
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		IUserDao userDao = new JPAUserDao();
		
		//views
		HomeView hv = new HomeView(scanner);
		DashboardView dv = new DashboardView(scanner);
		ProductView pv = new ProductView(scanner);
		UserView uv = new UserView(scanner);
		AuctionView aucv = new AuctionView();
		
		//Controllers1
		HomeController hc = new HomeController();
		AuthenticationController ac = new AuthenticationController();
		ProductController pc = new ProductController();
		UserController uc = new UserController();
		AuctionController auc = new AuctionController(scanner);
		
		hc.setHomeView(hv);
		ac.setHomeView(hv);
		ac.setDashboradView(dv);
		ac.setUserDao(userDao);
		pc.setProductView(pv);
		uc.setUserView(uv);
		auc.setAuctionView(aucv);
		
		hv.setAuthenticationController(ac);
		hv.setHomeController(hc);
		dv.setAutheticationController(ac);
		dv.setProductController(pc);
		dv.setUserController(uc);
		dv.setAuctionController(auc);
		pv.setProductController(pc);
		uv.setDashboardView(dv);
		uv.setUserController(uc);
		uv.setAuctionController(auc);
			
		hc.showHome();
		scanner.close();
	}
}
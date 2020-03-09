package com.fdmgroup.view;

import java.util.Scanner;

import com.fdmgroup.controller.AuthenticationController;
import com.fdmgroup.controller.HomeController;

public class HomeView {

	private HomeController homeController;
	private AuthenticationController authenticationController;
	private Scanner scanner;
	public HomeView() {
		super();
	}
	
	public HomeView(Scanner scanner) {
		super();
		this.scanner = scanner;
	}

	public HomeView(HomeController homeController) {
		super();
		this.homeController = homeController;
	}
	
	public HomeView(HomeController homeController, AuthenticationController authenticationController) {
		super();
		this.homeController = homeController;
		this.authenticationController = authenticationController;
	}
	public HomeController getHomeController() {
		return homeController;
	}
	public void setHomeController(HomeController homeController) {
		this.homeController = homeController;
	}
	
	public AuthenticationController getAuthenticationController() {
		return authenticationController;
	}
	public void setAuthenticationController(AuthenticationController authenticationController) {
		this.authenticationController = authenticationController;
	}
	public void showInitialOptions() {
		System.out.println("Welcome to my Solo Project Version 1.0.0");
		System.out.println("Please choose one of the option:");
		System.out.println("1) Login");
		System.out.println("2) Register");
		System.out.println("3) Exit");
		System.out.println("---------------------------------------------------");
		String userInput = scanner.nextLine();
		switch(userInput) {
			case "1" :
					showLoginOptions();
					break;
			case "2" :
					showRegistrationOptions();
					break;
			case "3" :
					System.out.println("Thanks, Goodbye!");
					System.exit(0);
					break;
			default :
					System.out.println("Please enter a valid option.");
					showInitialOptions();
		}
	}
	
	public void showRegistrationOptions() {
		// TODO Auto-generated method stub
		
	}
	public void showLoginOptions() {
		System.out.println("-----Login--------------");
		System.out.println("Please Enter username: ");
		String username = scanner.nextLine();
		System.out.println("Please Enter password: ");
		String password = scanner.nextLine();
		authenticationController.login(username,password);
	}

}





package com.fdmgroup.view;

import java.util.Scanner;

import com.fdmgroup.controller.AuctionController;
import com.fdmgroup.controller.AuthenticationController;
import com.fdmgroup.controller.ProductController;
import com.fdmgroup.controller.UserController;
import com.fdmgroup.model.User;

public class DashboardView {
	private Scanner scanner;
	private AuthenticationController autheticationController;
	private ProductController productController;
	private UserController userController;
	private AuctionController auctionController;

	public DashboardView() {
		super();
	}

	public ProductController getProductController() {
		return productController;
	}

	public void setProductController(ProductController productController) {
		this.productController = productController;
	}

	public DashboardView(Scanner scanner) {
		super();
		this.scanner = scanner;
	}

	public AuthenticationController getAutheticationController() {
		return autheticationController;
	}

	public void setAutheticationController(AuthenticationController autheticationController) {
		this.autheticationController = autheticationController;
	}
	
	public UserController getUserController() {
		return userController;
	}

	public void setUserController(UserController userController) {
		this.userController = userController;
	}

	public AuctionController getAuctionController() {
		return auctionController;
	}

	public void setAuctionController(AuctionController auctionController) {
		this.auctionController = auctionController;
	}

	public void showDashboard(User user) {
		System.out.println("Welcome to the Dashboard");
		System.out.println("Please select one option : ");
		System.out.println("1) Logout");
		System.out.println("2) Add New Product");
		System.out.println("3) View All Products");
		System.out.println("4) User Profile");
		System.out.println("5) View All Auctions");
		String userInput = scanner.nextLine();
		switch (userInput) {
		case "1":
			autheticationController.logout();
			break;

		case "2":
			productController.addNewProduct();
			showDashboard(user);
			break;
			
		case "3":
			productController.showAll();
			showDashboard(user);
			break;
			
		case "4":
			userController.showProfile(user);
			break;
			
		case "5":
			auctionController.showAll();
			showDashboard(user);
			break;
			
		default:
			System.out.println("Please enter valid option");
			showDashboard(user);
		}
	}

}

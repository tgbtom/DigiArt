package com.fdmgroup.view;

import java.util.Scanner;

import com.fdmgroup.controller.AuctionController;
import com.fdmgroup.controller.UserController;
import com.fdmgroup.dao.JPAUserDao;
import com.fdmgroup.model.User;

public class UserView {

	private Scanner scanner;
	private DashboardView dashboardView;
	private UserController userController;
	private AuctionController auctionController;
	private JPAUserDao jpaUserDAo;

	public UserView(Scanner scanner) {
		super();
		this.scanner = scanner;
		this.jpaUserDAo = new JPAUserDao();
	}
	
	public DashboardView getDashboardView() {
		return dashboardView;
	}

	public void setDashboardView(DashboardView dashboardView) {
		this.dashboardView = dashboardView;
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

	public void showProfile(int userId) {
		User user = jpaUserDAo.findById(userId);
		System.out.println("==================================");
		System.out.println("Username is: "+ user.getUsername());
		System.out.println("First name is: "+ user.getFirstname());
		System.out.println("Last Name is: "+ user.getLastname());
		System.out.println("Wallet funds: "+ user.getWallet());
		
		System.out.println("1) Change Password");
		System.out.println("2) Deposit");
		System.out.println("3) Withdraw");
		System.out.println("4) View My Auctions");
		System.out.println("5) Return to Dashboard");
		
		String userInput = scanner.nextLine();
		
		switch (userInput) {
		case "1":
			break;
		case "2":
			double depositAmount = promptDeposit();
			userController.deposit(user, depositAmount);
			showProfile(userId);
			break;
		case "3":
			double withdrawAmount = promptWithdraw();
			userController.withdraw(user, withdrawAmount);
			showProfile(userId);
			break;
		case "4":
			auctionController.showMine(user);
			showProfile(userId);
			break;
		case "5":
			dashboardView.showDashboard(userId);
			break;
		default:
			System.out.println("Please enter a valid option");
			showProfile(userId);
			break;
		}
	}

	private double promptWithdraw() {
		System.out.println("How much money would you like to withdraw?");
		double amount = Double.parseDouble(scanner.nextLine());
		return amount;
	}

	private double promptDeposit() {
		System.out.println("How much money would you like to deposit?");
		double amount = Double.parseDouble(scanner.nextLine());
		return amount;
	}

}

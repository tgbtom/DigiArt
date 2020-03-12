package com.fdmgroup.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.fdmgroup.model.User;

public class JDBCUserDao implements IUserDao{
	
	@Override
	public User create(User t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findById(int id) {
		Connection conn = JDBCConnection.openConnection();
		try {
			String query = "SELECT user_id, username, first_name, last_name, wallet, salt FROM users WHERE user_id = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				User user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), "member", rs.getDouble(5), rs.getString(6));
				return user;
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
		Connection conn = JDBCConnection.openConnection();
		try {
			String query = "SELECT user_id, username, first_name, last_name, wallet, salt FROM users WHERE username LIKE ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				User user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), "member", rs.getDouble(5), rs.getString(6));
				return Optional.ofNullable(user);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("Invalid Credentials, please try again");
		return Optional.empty();
	}

	@Override
	public List<User> findByFirstname(String firstname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deposit(User user, double amountToDeposit) {
		double newBalance = user.getWallet() + amountToDeposit;
		Connection conn = JDBCConnection.openConnection();
		
		String query = "UPDATE users SET wallet = ? WHERE user_id = ?";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(query);
			ps.setDouble(1, newBalance);
			ps.setInt(2, user.getId());
			
			ps.executeUpdate();
			
			user.setWallet(newBalance);
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void withdraw(User user, double amountToWithdraw) {
		double newBalance = user.getWallet() - amountToWithdraw;
		Connection conn = JDBCConnection.openConnection();
		
		String query = "UPDATE users SET wallet = ? WHERE user_id = ?";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(query);
			ps.setDouble(1, newBalance);
			ps.setInt(2, user.getId());
			
			ps.executeUpdate();
			
			user.setWallet(newBalance);
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<User> findAll(User user) {
		// TODO Auto-generated method stub
		return null;
	}

}

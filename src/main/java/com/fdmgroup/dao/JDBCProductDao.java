package com.fdmgroup.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Persistence;

import com.fdmgroup.model.Product;
import com.fdmgroup.model.User;

public class JDBCProductDao implements IProductDao {

	private JDBCUserDao jdbcUserDao;

	public JDBCProductDao() {
		super();
		this.jdbcUserDao = new JDBCUserDao();
	}

	@Override
	public ArrayList<Product> findMine(User user) {
		ArrayList<Product> products = new ArrayList<>();
		Connection conn = JDBCConnection.getInstance();

		System.out.println("Finding products for user id: " + user.getId());

		String sql = "SELECT inv.product_id, inv.status, prod.name, prod.creator_id FROM inventory inv "
				+ "JOIN products prod ON prod.product_id = inv.product_id WHERE inv.user_id = ? "
				+ "ORDER BY inv.product_id";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, user.getId());
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String status = rs.getString(2);
				int productId = rs.getInt(1);
				String productName = rs.getString(3);
				int creatorId = rs.getInt(4);
				
				User owner = findOwner(productId);
				User creator = jdbcUserDao.findById(creatorId);
				
				
				Product product = new Product(productId, productName, owner, creator);
				product.setStatus(status);
				products.add(product);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JDBCConnection.closeConnection();
		return products;
	}

	public User findOwner(int productId) {
		Connection conn = JDBCConnection.getInstance();

		User result = new User();

		String query = "SELECT user_id FROM inventory WHERE product_id = ? AND status IN ('Available', 'Auctioned', 'Bought')";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, productId);

			ResultSet rs = ps.executeQuery();

			if(rs.next()) {
				int ownerId = rs.getInt(1);
				result = jdbcUserDao.findById(ownerId);
			}
		} catch (SQLException e) {
			System.out.println("Here Error 550");
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Product create(Product product) {
		Connection conn = JDBCConnection.getInstance();

		String query = "INSERT INTO products (product_id, name, creator_id) VALUES (PRODUCT_ID_SEQ.NEXTVAL, ?, ?)";
		String query2 = "INSERT INTO inventory (user_id, product_id, status) VALUES (?, ?, 'Available')";

		PreparedStatement ps;
		Product createdProduct = new Product();
		createdProduct.setProduct_id(-1);
		try {
			ps = conn.prepareStatement(query, new String[] { "PRODUCT_ID" });
			ps.setString(1, product.getName());
			ps.setInt(2, product.getCreator().getId());

			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			int productId = rs.getInt(1);

			ps = conn.prepareStatement(query2);
			ps.setInt(1, product.getOwner().getId());
			ps.setInt(2, productId);
			ps.executeUpdate();

			createdProduct = product;
			createdProduct.setProduct_id(productId);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return createdProduct;
	}

	@Override
	public Product findById(int id) {
		Connection conn = JDBCConnection.getInstance();
		Product productToReturn = new Product();
		String query = "SELECT name, creator_id FROM products WHERE product_id = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				productToReturn.setProduct_id(id);
				productToReturn.setName(rs.getString(1));
				productToReturn.setCreator(jdbcUserDao.findById(rs.getInt(2)));
				productToReturn.setOwner(findOwner(id));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return productToReturn;
	}

	@Override
	public List<Product> findAll(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getStatus(Product product) {
		Connection conn = JDBCConnection.getInstance();
		
		String query = "SELECT status FROM inventory WHERE product_id = ? AND user_id = ?";
		String result = "";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, product.getProduct_id());
			ps.setInt(2, product.getOwner().getId());
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				result = rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public boolean addToInventory(User user, Product product, String status) {
		Connection conn = JDBCConnection.getInstance();
		
		String query = "INSERT INTO inventory (user_id, product_id, status) VALUES (?, ?, ?)";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, user.getId());
			ps.setInt(2, product.getProduct_id());
			ps.setString(3, status);
			
			int rowsUpdated = ps.executeUpdate();
			
			return rowsUpdated == 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}

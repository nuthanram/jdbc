package com.ty.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ty.dto.User;
import com.ty.util.AES;
import com.ty.util.AppConstants;
import com.ty.util.ConnectionObject;

public class Userdao {
	public int saveUser(User u) {
		if (u != null) {
			String sql = "INSERT INTO user VALUES(?,?,?,?,?)";
			Connection con = ConnectionObject.getConnection();
			try {
				String secret = AES.encrypt(u.getPassword(), AppConstants.SECRET);
				PreparedStatement preparedStatement = con.prepareStatement(sql);
				preparedStatement.setInt(1, u.getId());
				preparedStatement.setString(2, u.getName());
				preparedStatement.setString(3, u.getEmail());
				preparedStatement.setString(4, secret);
				preparedStatement.setLong(5, u.getMobile());
				return preparedStatement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (con != null) {
					try {
						con.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return 0;
	}

	public List<User> showAll() {
		List<User> al = new ArrayList<User>();
		Connection con = ConnectionObject.getConnection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from user");
			while (rs.next()) {
				User u = new User();
				u.setId(rs.getInt(1));
				u.setName(rs.getString(2));
				u.setEmail(rs.getString(3));
				u.setPassword(AES.decrypt(rs.getString(4), AppConstants.SECRET));
				u.setMobile(rs.getLong(5));
				al.add(u);
			}
			return al;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	public User getDetailsById(int id) {
		String sql = "select * from user where id=" + id;
		if (sql != null && id != 0) {
			Connection con = ConnectionObject.getConnection();
			try {
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				if (rs.next()) {
					User u = new User();
					u.setId(rs.getInt(1));
					u.setName(rs.getString(2));
					u.setEmail(rs.getString(3));
					u.setPassword(AES.decrypt(rs.getString(4), AppConstants.SECRET));
					u.setMobile(rs.getLong(5));
					return u;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (con != null) {
					try {
						con.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return null;
	}

	public int updateUser(int id, User u) {
		return 0;
	}

	public int deleteUserById(int id) {
		return 0;

	}

	public User validateUser(String email, String password) {
		String sql = "select * from user where email=? and password=?";
		Connection con = ConnectionObject.getConnection();
		try {
			String enc = AES.encrypt(password, AppConstants.SECRET);
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, enc);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				User u = new User();
				u.setId(rs.getInt(1));
				u.setName(rs.getString(2));
				u.setEmail(rs.getString(3));
				u.setMobile(rs.getLong(5));
				return u;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

}

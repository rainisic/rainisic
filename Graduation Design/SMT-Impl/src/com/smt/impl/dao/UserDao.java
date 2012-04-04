/* @(#) UserDao.java
 *
 * Date: 2012-4-4
 * 
 * Author: Rainisic
 */
package com.smt.impl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.smt.impl.db.DBConnection;
import com.smt.impl.entity.User;

/**
 * 
 * @author Rainisic
 * 
 */
public class UserDao {

	/** Define the singleton user DAO. */
	private static UserDao userDao;

	/**
	 * Private constructor.
	 */
	private UserDao() {
	}

	/**
	 * Get the singleton user DAO.
	 * 
	 * @return
	 */
	public static UserDao getInstance() {
		if (userDao == null) {
			userDao = new UserDao();
		}
		return userDao;
	}

	/**
	 * 
	 * @return
	 */
	public List<User> list() {

		// Define the SQL statement and result list.
		String sql = "SELECT `id`, `description`, `email`, `gender`, `nickname`, `password`, `permission`, `portrait`, `status`, `username` FROM `user` ORDER BY `id`;";
		List<User> result = null;

		// Define the connection and prepared statement.
		Connection con = null;
		PreparedStatement ps = null;

		try {

			// Instant connection and prepare the statement by SQL.
			con = DBConnection.getConnection();
			ps = con.prepareStatement(sql);

			// Execute query and process the result.
			ResultSet rs = ps.executeQuery();
			result = new LinkedList<User>();
			while (rs.next()) {
				User u = new User();
				u.setId(rs.getInt(1));
				u.setDescription(rs.getString(2));
				u.setEmail(rs.getString(3));
				u.setGender(rs.getString(4));
				u.setNickname(rs.getString(5));
				u.setPassword(rs.getString(6));
				u.setPermission(rs.getInt(7));
				u.setPortrait(rs.getString(8));
				u.setStatus(rs.getInt(9));
				u.setUsername(rs.getString(10));
				result.add(u);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			try {
				if (con != null && !con.isClosed()) {
					con.rollback();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} finally {
			try {
				if (con != null && !con.isClosed()) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}
}

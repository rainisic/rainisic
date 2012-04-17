/* @(#) ConfigurationDao.java
 *
 * Date: 2012-4-17
 * 
 * Author: Rainisic
 */
package com.smt.impl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.smt.impl.db.DBConnection;

/**
 * @author Rainisic
 *
 */
public class ConfigurationDao {

	private static ConfigurationDao configurationDao;
	private ConfigurationDao() {
	}
	
	public static ConfigurationDao getInstance() {
		if (configurationDao == null) {
			configurationDao = new ConfigurationDao();
		}
		return configurationDao;
	}
	
	public void changeTheme(String theme) {
		
		// Define the SQL statement and result list.
		String sql = "UPDATE `smms_configuration` SET `theme`= ?;";

		// Define the connection and prepared statement.
		Connection con = null;
		PreparedStatement ps = null;

		try {

			// Instant connection and prepare the statement by SQL.
			con = DBConnection.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, theme);
			ps.executeUpdate();

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
	}
}

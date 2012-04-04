/* @(#) FeedDao.java
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
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import com.smt.impl.db.DBConnection;
import com.smt.impl.entity.Feed;
import com.smt.impl.entity.User;

/**
 * Feed DAO Class.
 * 
 * @author Rainisic
 * 
 */
public class FeedDao {

	/** Define the singleton feed DAO. */
	private static FeedDao feedDao;

	/**
	 * Private constructor.
	 */
	private FeedDao() {
	}

	/**
	 * Get the instance of FeedDao
	 * 
	 * @return
	 */
	public static FeedDao getInstance() {
		if (feedDao == null) {
			feedDao = new FeedDao();
		}
		return feedDao;
	}

	/**
	 * List all feeds.
	 * 
	 * @return
	 */
	public List<Feed> list() {

		// Define the SQL statement and result list.
		String sql = "SELECT f.`id`, f.`content`, f.`createTime`, f.`status`, f.`reference_id`, u.`username` FROM `feed` f, `user` u WHERE f.`author_id` = u.`id` ORDER BY f.`id`;";
		List<Feed> result = null;

		// Define the connection and prepared statement.
		Connection con = null;
		PreparedStatement ps = null;

		try {

			// Instant connection and prepare the statement by SQL.
			con = DBConnection.getConnection();
			ps = con.prepareStatement(sql);

			// Execute query and process the result.
			ResultSet rs = ps.executeQuery();
			result = new LinkedList<Feed>();
			while (rs.next()) {
				Feed f = new Feed();
				f.setId(rs.getInt(1));
				f.setContent(rs.getString(2));
				Calendar createTime = Calendar.getInstance();
				createTime.setTimeInMillis(rs.getTimestamp(3).getTime());
				f.setCreateTime(createTime);
				f.setStatus(rs.getInt(4));
				Feed reference = new Feed();
				reference.setId(rs.getInt(5));
				f.setReference(reference);
				User user = new User();
				user.setUsername(rs.getString(6));
				f.setAuthor(user);
				result.add(f);
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

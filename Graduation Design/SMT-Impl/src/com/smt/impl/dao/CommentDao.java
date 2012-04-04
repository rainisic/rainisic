/* @(#) CommentDao.java
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
import com.smt.impl.entity.Comment;
import com.smt.impl.entity.Feed;
import com.smt.impl.entity.User;

/**
 * @author Rainisic
 * 
 */
public class CommentDao {

	/** Define the singleton comment DAO. */
	private static CommentDao commentDao;

	/**
	 * Private constructor.
	 */
	private CommentDao() {
	}

	/**
	 * Get the instance of comment DAO.
	 * 
	 * @return
	 */
	public static CommentDao getInstance() {
		if (commentDao == null) {
			commentDao = new CommentDao();
		}
		return commentDao;
	}

	public List<Comment> list() {

		// Define the SQL statement and result list.
		String sql = "SELECT c.`id`, c.`content`, c.`createTime`, c.`status`, c.`feed_id`, u.`username` FROM `comment` c, `user` u WHERE c.`author_id` = u.`id`  ORDER BY c.`id`;";
		List<Comment> result = null;

		// Define the connection and prepared statement.
		Connection con = null;
		PreparedStatement ps = null;

		try {

			// Instant connection and prepare the statement by SQL.
			con = DBConnection.getConnection();
			ps = con.prepareStatement(sql);

			// Execute query and process the result.
			ResultSet rs = ps.executeQuery();
			result = new LinkedList<Comment>();
			while (rs.next()) {
				Comment c = new Comment();
				c.setId(rs.getInt(1));
				c.setContent(rs.getString(2));
				Calendar createTime = Calendar.getInstance();
				createTime.setTimeInMillis(rs.getTimestamp(3).getTime());
				c.setCreateTime(createTime);
				c.setStatus(rs.getInt(4));
				Feed feed = new Feed();
				feed.setId(rs.getInt(5));
				c.setFeed(feed);
				User user = new User();
				user.setUsername(rs.getString(6));
				c.setAuthor(user);
				result.add(c);
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

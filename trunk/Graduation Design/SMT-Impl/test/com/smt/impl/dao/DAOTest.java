package com.smt.impl.dao;

import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Test;
import com.smt.impl.entity.Feed;
import com.smt.impl.entity.User;

public class DAOTest {
	
	private UserDao userDao = UserDao.getInstance();
	private FeedDao feedDao = FeedDao.getInstance();

	@Test
	public void test() {
		
		List<User> users = userDao.list();
		System.out.println(1);
		for (User u : users) {
			System.out.println(u.getUsername());
		}
		
	}
	
	@Test
	public void testFeedList() {
		List<Feed> feeds = feedDao.list();
		for (Feed f : feeds) {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(f.getCreateTime().getTime()));
		}
	}

}

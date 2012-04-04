/* @(#) UserServiceTest.java
 * 
 * Date: 2012-2-19
 *
 * Author: Rainisic
 *
 * Description: none.
 */
package com.smms.service;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.smt.entity.User;
import com.smt.service.UserServiceInterface;

/**
 * @author Rainisic
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class UserServiceTest extends
		AbstractTransactionalJUnit4SpringContextTests {

	@Resource
	private UserServiceInterface userService;

	@Test
	public void checkEmailTest() {
		Assert.assertTrue(userService.checkAvailability("test@smms.com"));
	}

	@Test
	@Rollback(value=false)
	public void registerTest() {
		User user = new User();
		user.setEmail("test@smms.com");
		user.setPassword("123456");
		Assert.assertNotNull(userService.register(user));
	}

	@Test
	public void loginTest() {
		User user = new User("test@smms.com", "123456");
		Assert.assertNotNull(userService.login(user));
	}

	@Test
	@Rollback
	public void modifyTest() {
		User user = new User("test@smms.com", "123456");
		user.setPassword("testPassword");
		user.setNickname("testModify");
		user.setEmail("testEmail@smms.com");
		user.setGender("F");
		user.setDescription("Test Description.");
		Assert.assertNotNull(userService.modify(user));
	}

	@Test
	public void getUserInformationTest() {
		Assert.assertNotNull(userService.getUserInformation("test"));
	}

	@Test
	@Rollback(value=false)
	public void addAndDeleteFriendTest() {
		User user1 = new User();
		user1.setEmail("test1@smms.com");
		user1.setPassword("1");

		User user2 = new User();
		user2.setEmail("test2@smms.com");
		user2.setPassword("2");

		User user3 = new User();
		user3.setEmail("test3@smms.com");
		user3.setPassword("3");

		userService.register(user1);
		userService.register(user2);
		userService.register(user3);

		userService.addFriend(user1.getUsername(), user2.getUsername());
		userService.addFriend(user1.getUsername(), user2.getUsername());
		userService.addFriend(user1.getUsername(), user3.getUsername());
		userService.addFriend(user2.getUsername(), user3.getUsername());
		
		//System.out.println(userService.getUserInformation(user1.getUsername()).getFriends().size());

		userService.deleteFriend(user1.getUsername(), user3.getUsername());

	}
	
	@Test
	@Rollback(value=false)
	public void changeStatusTest() {
		
		User user = userService.getUserInformation("test");
		Assert.assertTrue(userService.changeStatus(user, 1));
		
	}
}

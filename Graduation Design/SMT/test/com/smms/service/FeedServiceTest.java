/* @(#) FeedServiceTest.java
 * 
 * Date: 2012-2-24
 *
 * Author: Rainisic
 *
 * Description: none.
 */
package com.smms.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.smt.dto.FeedDto;
import com.smt.dto.Page;
import com.smt.entity.Feed;
import com.smt.entity.User;
import com.smt.service.FeedServiceInterface;
import com.smt.service.UserServiceInterface;

/**
 * @author Rainisic
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class FeedServiceTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Resource
	FeedServiceInterface feedService;
	
	@Resource
	UserServiceInterface userService;
	
	@Test
	@Rollback(value=false)
	public void publishTest() {
		
		FeedDto feedDto = new FeedDto();
		feedDto.setAuthor(userService.login(new User("test", "123456")));
		feedDto.setContent("Test Feed Content");
		feedService.publish(feedDto);
	}
	
	@Test
	public void listTest() {
		
		Set<User> users = new HashSet<User>();
		users.add(userService.login(new User("test", "123456")));
		
		List<Feed> feeds = feedService.list(users, new Page(0));
		
		for (Feed f : feeds) {
			System.out.println(f.getContent());
		}
	}
	
	@Test
	@Rollback(value=false)
	public void deleteTest() {
		
		feedService.delete(3, Feed.NORMAL);
		
	}
}

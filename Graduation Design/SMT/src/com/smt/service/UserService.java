/* @(#) UserService.java
 * 
 * Date: 2012-2-18
 *
 * Author: Rainisic
 *
 * Description: none.
 */
package com.smt.service;

import java.util.Iterator;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.smt.dao.UserDaoInterface;
import com.smt.entity.User;

/**
 * @author Rainisic
 * 
 */
@Service
public class UserService implements UserServiceInterface {

	/** Define the userDao. */
	@Resource
	private UserDaoInterface userDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.smms.service.UserServiceInterface#checkAvailability(java.lang.String)
	 */
	@Override
	@Transactional
	public boolean checkAvailability(String value) {
		return userDao.query(value) == null ? true : false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.smms.service.UserServiceInterface#register(com.smms.entity.User)
	 */
	@Override
	@Transactional
	public User register(User user) {

		// Check weather the user already exists.
		if (userDao.query(user.getEmail()) == null) {

			// Set default user info.
			user.setUsername(user.getEmail().split("@")[0]);
			user.setNickname(user.getUsername());

			// Register and check the result.
			int identifier = userDao.save(user);

			// Check identifier.
			if (identifier > 0) {

				// Set the identifier and return.
				user.setId(identifier);
				return user;
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.smms.service.UserServiceInterface#login(com.smms.entity.User)
	 */
	@Override
	@Transactional
	public User login(User user) {

		// Query by username.
		User persistentUser = userDao.query(user.getUsername());

		if (persistentUser != null) {

			// Check password.
			if (user.getPassword().equals(persistentUser.getPassword())) {
				return persistentUser;
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.smms.service.UserServiceInterface#modify(com.smms.entity.User)
	 */
	@Override
	@Transactional
	public User modify(User user) {

		// Query by username.
		User persistentUser = userDao.query(user.getUsername());

		if (persistentUser != null) {

			// Check new data availability.
			if (!user.getNickname().equals(persistentUser.getNickname())
					&& userDao.query(user.getNickname()) == null
					&& !user.getEmail().equals(persistentUser.getEmail())
					&& userDao.query(user.getEmail()) == null) {

				// Set new values.
				persistentUser.setPassword(user.getPassword());
				persistentUser.setNickname(user.getNickname());
				persistentUser.setEmail(user.getEmail());
				persistentUser.setGender(user.getGender());
				persistentUser.setDescription(user.getDescription());

				// Update.
				userDao.update(persistentUser);

				return persistentUser;
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.smms.service.UserServiceInterface#getUserInformation(java.lang.String
	 * )
	 */
	@Override
	@Transactional
	public User getUserInformation(String username) {
		return userDao.query(username);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.smms.service.UserServiceInterface#addFriend(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	@Transactional
	public User addFriend(String user, String friend) {

		// Get user and friend.
		User persistentUser = userDao.query(user);
		User persistentFriend = userDao.query(friend);

		if (persistentUser != null && persistentFriend != null) {

			// Add friend.
			persistentUser.getFriends().add(persistentFriend);

			// Update
			userDao.update(persistentUser);

			return persistentUser;
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.smms.service.UserServiceInterface#deleteFriend(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public User deleteFriend(String user, String friend) {

		// Get user and friend.
		User persistentUser = userDao.query(user);
		User persistentFriend = userDao.query(friend);

		if (persistentUser != null && persistentFriend != null) {

			for (Iterator<User> userFriends = persistentUser.getFriends()
					.iterator(); userFriends.hasNext();) {
				if (userFriends.next().getUsername().equals(friend)) {

					// Remove the friend.
					userFriends.remove();

					// Update the user.
					userDao.update(persistentUser);

					return persistentUser;
				}
			}
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.smms.service.UserServiceInterface#changeStatus(com.smms.entity.User,
	 * int)
	 */
	@Override
	public boolean changeStatus(User user, int status) {

		// Get user.
		User persistentUser = userDao.query(user.getUsername());

		if (persistentUser != null) {
			
			// Change status.
			persistentUser.setStatus(status);
			
			// Update the user.
			userDao.save(persistentUser);
			
			return true;
		}
		return false;
	}
}

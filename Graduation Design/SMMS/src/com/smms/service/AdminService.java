/* @(#) AdminService.java
 * 
 * Date: 2012-3-9
 *
 * Author: Rainisic
 *
 * Description: none.
 */
package com.smms.service;

import java.util.Calendar;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.smms.dao.AdminDaoInterface;
import com.smms.dao.LogDaoInterface;
import com.smms.entity.Admin;
import com.smms.entity.Log;

/**
 * @author Rainisic
 *
 */
@Service
public class AdminService implements AdminServiceInterface {
	
	/** Administrator DAO. */
	@Resource
	private AdminDaoInterface adminDao;
	
	/** Log DAO. */
	@Resource
	private LogDaoInterface logDao;
	
	/* (non-Javadoc)
	 * @see com.smms.service.AdminServiceInterface#login(com.smms.entity.Admin)
	 */
	@Override
	@Transactional
	public Admin login(Admin admin) {
		
		// Query the administrator from database.
		admin = adminDao.query(admin);
		
		// Check query result.
		if (admin != null) {
			
			// Write login log.
			Log log = new Log();
			log.setOperator(admin);
			log.setCreateTime(Calendar.getInstance());
			log.setOperation(" Login ");
			log.setDetails("Administrator " + admin.getUsername() + " login successfully.");
			
			// Save to database.
			logDao.save(log);
		}
		return admin;
	}
}

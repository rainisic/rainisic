/* @(#) DataManagerAction.java
 * 
 * Date: 2012-4-3
 *
 * Author: Rainisic
 */
package com.smt.impl.action;

import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.smt.impl.entity.Comment;
import com.smt.impl.entity.Feed;
import com.smt.impl.entity.User;
import com.smt.impl.service.CommentService;
import com.smt.impl.service.FeedService;
import com.smt.impl.service.UserService;

/**
 * 
 * @author Rainisic
 * 
 */
public class DataManagerAction extends ActionSupport {

	/** Default serial version UID. */
	private static final long serialVersionUID = 1L;

	/** Define the service. */
	private UserService userService = UserService.getInstance();
	private FeedService feedService = FeedService.getInstance();
	private CommentService commentService = CommentService.getInstance();

	/** The data URL mapping. */
	private List<String[]> dataURLMapping;

	/** The data contents. */
	private List<String[]> contents;

	/**
	 * Load data URL mapping.
	 * 
	 * @return
	 */
	public String loadDataURLMapping() {
		dataURLMapping = new LinkedList<String[]>();
		dataURLMapping.add(new String[] { "DataManagerAction_loadUsers", "用户" });
		dataURLMapping.add(new String[] { "DataManagerAction_loadFeeds", "微博" });
		dataURLMapping.add(new String[] { "DataManagerAction_loadComments", "回复" });
		return SUCCESS;
	}

	/**
	 * Load all users.
	 * 
	 * @return
	 */
	public String loadUsers() {
		contents = new LinkedList<String[]>();
		contents.add(new String[] { "ID", "用户名", "昵称", "Email", "性别",
				"个人简介", "头像", "账号状态", "权限" });
		for (User u : userService.list()) {
			
			// Define the user state.
			String state = "异常";
			if (u.getStatus() == User.ENABLED) {
				state = "正常";
			} else if (u.getStatus() == User.DETACHED) {
				state = "注销";
			} else if (u.getStatus() == User.BLOCKED) {
				state = "禁止";
			}
			
			// Define the portrait
			String portrait = "<img width=50 height=50 src='" + u.getPortrait() + "'/>";
			
			
			// Define the gender
			String gender = "男";
			if (u.getGender().equals("F")) {
				gender = "女";
			}
			
			contents.add(new String[] { String.valueOf(u.getId()),
					u.getUsername(), u.getNickname(),
					u.getEmail(), gender, u.getDescription(),
					portrait, state,
					String.valueOf(u.getPermission()) });
		}
		return SUCCESS;
	}

	/**
	 * Load all feeds.
	 * 
	 * @return
	 */
	public String loadFeeds() {
		contents = new LinkedList<String[]>();
		contents.add(new String[] { "ID", "内容", "作者", "引用", "创建时间", "状态" });
		for (Feed f : feedService.list()) {
			contents.add(new String[] {
					String.valueOf(f.getId()),
					f.getContent(),
					f.getAuthor().getUsername(),
					String.valueOf(f.getReference().getId()),
					new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(f
							.getCreateTime().getTime()),
					String.valueOf(f.getStatus()) });
		}
		return SUCCESS;
	}

	/**
	 * Load all comments.
	 * 
	 * @return
	 */
	public String loadComments() {
		contents = new LinkedList<String[]>();
		contents.add(new String[] { "ID", "内容", "作者", "微博", "创建时间", "状态" });
		for (Comment c : commentService.list()) {
			contents.add(new String[] {
					String.valueOf(c.getId()),
					c.getContent(),
					c.getAuthor().getUsername(),
					String.valueOf(c.getFeed().getId()),
					new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(c
							.getCreateTime().getTime()),
					String.valueOf(c.getStatus()) });
		}
		return SUCCESS;
	}

	/**
	 * @return the dataURLMapping
	 */
	public List<String[]> getDataURLMapping() {
		return dataURLMapping;
	}

	/**
	 * @param dataURLMapping the dataURLMapping to set
	 */
	public void setDataURLMapping(List<String[]> dataURLMapping) {
		this.dataURLMapping = dataURLMapping;
	}

	/**
	 * @return the contents
	 */
	public List<String[]> getContents() {
		return contents;
	}

	/**
	 * @param contents the contents to set
	 */
	public void setContents(List<String[]> contents) {
		this.contents = contents;
	}
}

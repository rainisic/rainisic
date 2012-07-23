package com.test.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.test.entity.Category;
import com.test.vo.ArticleVo;

/**
 * Servlet implementation class ArticleServlet
 */
@WebServlet("/blog/load")
public class ArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		Map<String, Object> json = new HashMap<String, Object>();
		ArticleVo article = new ArticleVo();
		article.setTitle("Title");
		article.setSummary("This is the summary");
		article.setContent("This is the content");
		Category category = new Category();
		category.setId(1);
		category.setName("Java");
		category.setUrl("java");
		article.setCategory(category);
		article.setTags("Java , Linux");
		article.setFakepath("test.html");
		article.setPublishTime(new SimpleDateFormat("yyyy-mm-dd hh:MM:ss")
				.format(Calendar.getInstance().getTime()));

		json.put("article", article);

		JSONObject jsonObject = JSONObject.fromObject(json);
		response.getWriter().print(jsonObject);
	}

}

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
 * Servlet implementation class BlogServlet
 */
@WebServlet("/blog")
public class BlogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Map<String, Object> json = new HashMap<String, Object>();
		List<ArticleVo> articles = new LinkedList<ArticleVo>();
		for (int i = 0; i < 10; i++) {
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
			articles.add(article);
		}

		json.put("articles", articles);

		JSONObject jsonObject = JSONObject.fromObject(json);
		response.getWriter().print(jsonObject);
	}
}

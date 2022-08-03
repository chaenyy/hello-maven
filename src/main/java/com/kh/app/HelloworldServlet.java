package com.kh.app;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class HelloworldServlet
 */
@WebServlet("/helloworld")
public class HelloworldServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, Object> map = new HashMap<>();
		map.put("msg", "안녕하세요");
		map.put("num", 123456789);
		map.put("today", new Date());
		
		// Gson 의존을 maven을 통해 프로젝트에 주입받기
		// 1. pom.xml 의존등록
		// 2. gson 의존을 지역저장소에 다운
		// 3. project와 연결
		Gson gson = new Gson();
		response.setContentType("application/gson; charset=utf-8");
		gson.toJson(map, response.getWriter());
	}
}






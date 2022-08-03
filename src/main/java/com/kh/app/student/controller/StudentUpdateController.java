package com.kh.app.student.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.kh.app.common.AbstractController;
import com.kh.app.student.model.service.StudentService;

public class StudentUpdateController extends AbstractController {
	static final Logger log = Logger.getLogger(StudentUpdateController.class);
	private StudentService studentService;
	
	public StudentUpdateController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}
	
	@Override
	public String doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("no"));
		String name = request.getParameter("name");
		String tel = request.getParameter("tel");
		
		Map<String, Object> data = new HashMap<>();
		data.put("no", no);
		data.put("name", name);
		data.put("tel", tel);
		
		int result = studentService.updateStudent(data);
		
		return null;
	}
}

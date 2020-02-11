package com.douzone.mysite.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.action.main.MainActionFactory;
import com.douzone.web.action.Action;

//@WebServlet("/main")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Map<String, Object> map = new HashMap<>();
	
	@Override
	public void init() throws ServletException {
		String configPath = this.getServletConfig().getInitParameter("config");
		System.out.println("init() called : " + configPath );
		this.getServletContext().setAttribute("cache", map);
		super.init();
	}
	
	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		System.out.println("service() called");
		super.service(arg0, arg1);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doget() called");
		// filter로 처리한다.
		// request.setCharacterEncoding("utf-8");
		
		String actionName = request.getParameter("a");		
		Action action = new MainActionFactory().getAction(actionName);
		action.execute(request, response);
		
//		WebUtil.forward("/WEB-INF/views/main/index.jsp", request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	@Override
	public void destroy() {
		System.out.println("destory() called");
		super.destroy();
	}
}

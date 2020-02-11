package com.douzone.mysite.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.action.main.MainActionFactory;
import com.douzone.mysite.action.user.UserActionFactory;
import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// filter로 처리한다.
		// request.setCharacterEncoding("utf-8");
		
		String actionName = request.getParameter("a");		
		Action action = new UserActionFactory().getAction(actionName);
		action.execute(request, response);
		
//		if("joinform".equals(action)) {
//			WebUtil.forward("/WEB-INF/views/user/joinform.jsp", request, response);
//		} else if("join".equals(action)) {
//			
//		}
//		else if("joinsuccess".equals(action)) {
//			WebUtil.forward("/WEB-INF/views/user/joinsuccess.jsp", request, response);
//		}
//		else {
//			WebUtil.redirect(request.getContextPath(), request, response);
//		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package com.douzone.mysite.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.action.guestbook.GuestBookActionFactory;
import com.douzone.mysite.action.main.MainActionFactory;
import com.douzone.mysite.repository.GuestBookRepository;
import com.douzone.mysite.vo.GuestBookVo;
import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class GuestBookController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// filter로 처리한다.
		// request.setCharacterEncoding("utf-8");
		
		String actionName = request.getParameter("a");	
		if(actionName == null) {
			actionName = "b";
		}
		Action action = new GuestBookActionFactory().getAction(actionName);
		action.execute(request, response);
		
//		if("add".equals(action)) {
//			String name = request.getParameter("name");
//			String password = request.getParameter("password");
//			String contents = request.getParameter("message");
//
//			GuestBookVo vo = new GuestBookVo();
//			vo.setName(name);
//			vo.setPassword(password);
//			vo.setContents(contents);
//
//			new GuestBookRepository().insert(vo);
//
//			WebUtil.redirect(request.getContextPath() + "/guestbook", request, response);
//		}	
//		else if("deleteform".equals(action)) {
//			// Request Dispatch, forwarding
//			WebUtil.forward("/WEB-INF/views/guestbook/deleteform.jsp", request, response);
//			
//		}
//		else if("delete".equals(action)) {
//			String strNo = request.getParameter("no");
//			Long no = Long.parseLong(strNo);
//
//			String inputPassword = request.getParameter("password");
//			String noPassword = new GuestBookRepository().find(no);
//
//			if (inputPassword.equals(noPassword)) {
//				GuestBookVo vo = new GuestBookVo();
//				vo.setNo(no);
//
//				new GuestBookRepository().delete(vo);
//
//				WebUtil.redirect(request.getContextPath() + "/guestbook", request, response);
//			}
//		}
//		else {
//			// default 요청 처리 (list)
//			// list (default) 처리
//			List<GuestBookVo> list = new GuestBookRepository().findAll();
//
//			request.setAttribute("list", list);
//			// Request Dispatch
//
//			WebUtil.forward("/WEB-INF/views/guestbook/list.jsp", request, response);
//		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

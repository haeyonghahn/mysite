package com.douzone.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class DeleteFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		String no = request.getParameter("no");
		String state = request.getParameter("state");
		
		request.setAttribute("no", no);
		request.setAttribute("state", state);
		
		WebUtil.forward("/WEB-INF/views/board/deleteform.jsp", request, response);
	}

}

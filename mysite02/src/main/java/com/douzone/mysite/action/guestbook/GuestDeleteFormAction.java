package com.douzone.mysite.action.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class GuestDeleteFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		WebUtil.forward("/WEB-INF/views/guestbook/deleteform.jsp", request, response);
	}
}

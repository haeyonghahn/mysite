package com.douzone.mysite.action.guestbook;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.GuestBookRepository;
import com.douzone.mysite.vo.GuestBookVo;
import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class GuestAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// default 요청 처리 (list)
		// list (default) 처리
		List<GuestBookVo> list = new GuestBookRepository().findAll();

		request.setAttribute("list", list);
		// Request Dispatch

		WebUtil.forward("/WEB-INF/views/guestbook/list.jsp", request, response);
	}

}

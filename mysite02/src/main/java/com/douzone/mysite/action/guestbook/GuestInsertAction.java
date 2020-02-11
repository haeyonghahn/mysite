package com.douzone.mysite.action.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.GuestBookRepository;
import com.douzone.mysite.vo.GuestBookVo;
import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class GuestInsertAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String contents = request.getParameter("message");

		GuestBookVo vo = new GuestBookVo();
		vo.setName(name);
		vo.setPassword(password);
		vo.setContents(contents);

		new GuestBookRepository().insert(vo);

		WebUtil.redirect(request.getContextPath() + "/guestbook", request, response);
	}

}

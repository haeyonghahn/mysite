package com.douzone.mysite.action.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.GuestBookRepository;
import com.douzone.mysite.vo.GuestBookVo;
import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class GuestDeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String strNo = request.getParameter("no");
		Long no = Long.parseLong(strNo);

		String inputPassword = request.getParameter("password");
		String noPassword = new GuestBookRepository().find(no);

		if (inputPassword.equals(noPassword)) {
			GuestBookVo vo = new GuestBookVo();
			vo.setNo(no);

			new GuestBookRepository().delete(vo);

			WebUtil.redirect(request.getContextPath() + "/guestbook", request, response);
		}
		else {
			System.out.println("비밀번호가 틀렸습니다.");
		}
	}
}

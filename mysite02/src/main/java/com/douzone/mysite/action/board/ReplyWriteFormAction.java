package com.douzone.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class ReplyWriteFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String strgNo = request.getParameter("gNo");
		String stroNo = request.getParameter("oNo");
		String strdepth = request.getParameter("depth");
		
		System.out.println(strgNo);
		
		int gNo = Integer.parseInt(strgNo);
		int oNo = Integer.parseInt(stroNo);
		int depth = Integer.parseInt(strdepth);
		
		request.setAttribute("gNo", gNo);
		request.setAttribute("oNo", oNo);
		request.setAttribute("depth", depth);
		
		
		WebUtil.forward("/WEB-INF/views/board/replywriteform.jsp", request, response);
	}

}

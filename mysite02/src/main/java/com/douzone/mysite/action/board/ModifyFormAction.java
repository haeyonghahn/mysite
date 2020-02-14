package com.douzone.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class ModifyFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {			
		String strNo = request.getParameter("no");
		Long no = Long.parseLong(strNo);
		
		request.setAttribute("page", 1);
	
		BoardVo vo = new BoardRepository().viewContents(no);
		
		request.setAttribute("vo", vo);
		
		WebUtil.forward("/WEB-INF/views/board/modify.jsp", request, response);
	}

}

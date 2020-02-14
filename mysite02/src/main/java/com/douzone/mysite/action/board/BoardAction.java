package com.douzone.mysite.action.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class BoardAction implements Action {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// default 요청 처리 (list)
		// list (default) 처리
		List<BoardVo> list = new BoardRepository().findAll();
			
		request.setAttribute("list", list);
		// Request Dispatch
		WebUtil.forward("/WEB-INF/views/board/list.jsp", request, response);
	}

}

package com.douzone.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String strNo = request.getParameter("no");
		Long no = Long.parseLong(strNo);
		String state = request.getParameter("state");
		
		BoardVo vo = new BoardVo();
		vo.setNo(no);
		vo.setState(state);

		new BoardRepository().delete(vo);
		
		WebUtil.redirect(request.getContextPath() + "/board?a=list&page=1", request, response);
	}

}

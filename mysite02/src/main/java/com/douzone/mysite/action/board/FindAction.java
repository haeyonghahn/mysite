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

public class FindAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String kwd = request.getParameter("kwd");
		
		request.setAttribute("kwd", kwd);
		
		List<BoardVo> list = new BoardRepository().find(kwd);
		
		for(int i=0; i<list.size(); i++) {
			if(list.get(i).getContents().equals("삭제")) {
				
			}
		}
			
		request.setAttribute("list", list);
		// Request Dispatch
		
		WebUtil.forward("/WEB-INF/views/board/findlist.jsp", request, response);	
	}

}

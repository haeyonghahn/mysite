package com.douzone.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class ReplyWriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String strUserNo = request.getParameter("userNo");
		Long userNo = Long.parseLong(strUserNo);
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		String strgNo = request.getParameter("gNo");
		String stroNo = request.getParameter("oNo");
		String strdepth = request.getParameter("depth");
		
		int gNo = Integer.parseInt(strgNo);
		int oNo = Integer.parseInt(stroNo);
		int depth = Integer.parseInt(strdepth);
		
		BoardVo vo = new BoardVo();
		vo.setUserNo(userNo);
		vo.setTitle(title);
		vo.setContents(content);
		vo.setgNo(gNo);
		vo.setoNo(oNo);
		vo.setDepth(depth);
		
		new BoardRepository().replyUpdate(vo);
		new BoardRepository().replyWrite(vo);
		
		WebUtil.redirect(request.getContextPath() + "/board?a=list&page=1", request, response);
	}

}

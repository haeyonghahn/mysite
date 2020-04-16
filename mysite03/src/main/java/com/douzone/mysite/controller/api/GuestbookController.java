package com.douzone.mysite.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.mysite.vo.GuestBookVo;

import com.douzone.mysite.dto.JsonResult;
import com.douzone.mysite.service.GuestBookService;

@RestController("GuestbookApiController")	// return하는 값들이 객체로 생성되서 @Responsebody를 안붙여도 된다.
@RequestMapping("/api/guestbook")
public class GuestbookController {

	@Autowired
	private GuestBookService guestbookService;

	@RequestMapping("/list/{no}")
	public JsonResult list(@PathVariable("no") Long startNo) {
		List<GuestBookVo> list = guestbookService.getMessageList(startNo);
		return JsonResult.success(list);
	}
}

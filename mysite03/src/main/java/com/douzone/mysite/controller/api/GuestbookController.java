package com.douzone.mysite.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.mysite.dto.JsonResult;
import com.douzone.mysite.service.GuestBookService;
import com.douzone.mysite.vo.GuestBookVo;

@RestController("GuestbookApiController")	// return하는 값들이 객체로 생성되서 @Responsebody를 안붙여도 된다.
@RequestMapping("/api/guestbook")
public class GuestbookController {

	@Autowired
	private GuestBookService guestbookService;

	@GetMapping("/list/{no}")
	public JsonResult list(@PathVariable("no") Long startNo) {
		List<GuestBookVo> list = guestbookService.getMessageList(startNo);
		return JsonResult.success(list);
	}
	
	@PostMapping("/add")
	public JsonResult add(@RequestBody GuestBookVo vo) {
		guestbookService.add(vo);
		vo.setPassword("");
		return JsonResult.success(vo);
	}
}

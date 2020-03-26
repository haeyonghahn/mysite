package com.douzone.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.mysite.service.GuestBookService;
import com.douzone.mysite.vo.GuestBookVo;


@Controller
@RequestMapping("/guestbook")
public class GuestbookController {
	
	@Autowired
	private GuestBookService guestBookService;
	
	@RequestMapping(value="/list")
	public String list(Model model) {
		List<GuestBookVo> list = guestBookService.getList();
		model.addAttribute("list", list);
		return "guestbook/list";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String add() {
		return "guestbook/list";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String add(GuestBookVo vo) {
		guestBookService.add(vo);
		return "redirect:/guestbook/list";
	}
	
	@RequestMapping(value="/delete/{no}", method=RequestMethod.GET)
	public String delete(@PathVariable("no") Long no, Model model) {
		model.addAttribute("no", no);
		return "/guestbook/delete";
	}
	
	@RequestMapping(value="/delete/{no}", method=RequestMethod.POST)
	public String delete(@PathVariable("no") Long no, @RequestParam(value="password") String password) {
		String userPassword = guestBookService.getPassword(no);
		if(userPassword.equals(password)) {
			guestBookService.delete(no);
		}
		return "redirect:/guestbook/list";
	}
}

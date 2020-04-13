package com.douzone.mysite.controller;

import org.apache.commons.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douzone.mysite.service.SiteService;
import com.douzone.mysite.vo.SiteVo;
import com.douzone.mysite.vo.UserVo;

@Controller
public class MainController {
	
	@Autowired
	private SiteService siteService;
	
	@RequestMapping({"", "/main"})
	public String index(Model model) {
		
		SiteVo siteVo = siteService.getList();
		model.addAttribute("siteVo", siteVo);
		
		return "main/index";
	}
	
	@ResponseBody
	@RequestMapping("/hello")
	public UserVo hello() {
		return new UserVo();
	}
}

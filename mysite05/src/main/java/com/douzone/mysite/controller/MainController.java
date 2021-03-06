package com.douzone.mysite.controller;

import org.apache.commons.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.douzone.mysite.service.SiteService;
import com.douzone.mysite.vo.SiteVo;

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
}

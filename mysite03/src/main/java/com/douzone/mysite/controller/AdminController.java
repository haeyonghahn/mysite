package com.douzone.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.mysite.service.FileUploadService;
import com.douzone.mysite.service.SiteService;
import com.douzone.mysite.vo.SiteVo;
import com.douzone.security.Auth;

@Auth("ADMIN")
@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private SiteService siteService;
	@Autowired
	private FileUploadService fileUploadService;
	
	
	@RequestMapping("")
	public String main(Model model) {
		
		SiteVo siteVo = siteService.getList();
		model.addAttribute("siteVo", siteVo);
		
		return "admin/main";
	}
	
	@RequestMapping(value="/main/update", method=RequestMethod.POST)
	public String main(@RequestParam(value="title") String title
			, @RequestParam(value="welcomeMessage") String welcomeMessage
			, @RequestParam(value="description") String description
			, @RequestParam(value="file1") MultipartFile multipartFile, Model model) {
			
		String url = fileUploadService.restore(multipartFile);
		
		SiteVo siteVo = new SiteVo();
		siteVo.setTitle(title);
		siteVo.setWelcomeMessage(welcomeMessage);
		siteVo.setDescription(description);
		siteVo.setProfile(url);
	
		siteService.update(siteVo);
		
		model.addAttribute("siteVo", siteVo);
		
		return "main/index";
	}
		
	@RequestMapping("/guestbook")
	public String guestbook() {
		return "/admin/guestbook";
	}
	
	@RequestMapping("/board")
	public String board() {
		return "admin/board";
	}
	
	@RequestMapping("/user")
	public String user() {
		return "admin/user";
	}
}

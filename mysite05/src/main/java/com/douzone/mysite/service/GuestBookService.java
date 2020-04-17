package com.douzone.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.GuestBookRepository;
import com.douzone.mysite.vo.GuestBookVo;

@Service
public class GuestBookService {

	@Autowired
	private GuestBookRepository guestBookRepository;
	
	public boolean add(GuestBookVo vo) {
		// TODO Auto-generated method stub
		int count = guestBookRepository.insert(vo);
		return count == 1;
	}
	
	public List<GuestBookVo> getList() { 
		return guestBookRepository.findAll(); 
	}
	
	public String getPassword(Long no) {
		return guestBookRepository.find(no);
	}

	public boolean delete(Long no) {
		return guestBookRepository.delete(no);
	}
	
	public List<GuestBookVo> getMessageList(Long startNo) {
		return guestBookRepository.findAll(startNo);
	}

	public boolean deleteMessage(Long no, String password) {
		return 1 == guestBookRepository.delete(new GuestBookVo(no, password));
	}
}

package com.system.pos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.system.pos.service.UserService;

@RestController //데이터 처리 관련 컨트롤러 
public class RestApiController {
	
	@Autowired
	private UserService userService;
	
	//주소에 매핑되는 데이터
	@GetMapping("/user/checkUserIdExist/{user_id}")
	public String checkUserIdExist(@PathVariable String user_id) {
		boolean chk = userService.checkUserIdExist(user_id); //쳌크
		
		return chk + "";
	}
	
}

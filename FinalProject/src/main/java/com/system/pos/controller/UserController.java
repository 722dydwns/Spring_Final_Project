package com.system.pos.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.system.pos.beans.UserBean;
import com.system.pos.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService; //서비스 객체 주입받고
	
	@Resource(name = "loginUserBean")
	private UserBean loginUserBean; //현재 로그인 여부 갖는 객체 
	
	//로그인
	@GetMapping("/login")
	public String login(@ModelAttribute("tempLoginUserBean") UserBean tempLoginUserBean,
					    @RequestParam(value="fail", defaultValue = "false") boolean fail,
					    Model model) {	
		model.addAttribute("fail", fail);		
		return "user/login";
	}
	@PostMapping("/login_pro")
	public String login_pro(@Valid @ModelAttribute("tempLoginUserBean") UserBean tempLoginUserBean, BindingResult result) {
		if(result.hasErrors()) {
			return "user/login"; //문제 발생 시 이전으로 돌아감
		}
		userService.getLoginUserInfo(tempLoginUserBean);
		if(loginUserBean.isUserLogin() == true) {
			return "user/login_success";
		}else {
			return "user/login_fail";
		}
	}
	//회원가입
	@GetMapping("/join")
	public String join(@ModelAttribute("joinUserBean") UserBean joinUserBean) {
		return "user/join";
	}
	@PostMapping("/join_pro") //폼에 입력한 값 객체로 가져와서 유효성 검사하여 받음
	public String join_pro(@Valid @ModelAttribute("joinUserBean") UserBean joinUserBean, BindingResult result) {
		if(result.hasErrors()) {
			return "user/join";
		}
		userService.addUserInfo(joinUserBean);// DB 삽입
		
		return "user/join_success";
	}
	
	//정보수정
	@GetMapping("/modify")
	public String modify(@ModelAttribute("modifyUserBean")UserBean modifyUserBean) {
		userService.getModifyUserInfo(modifyUserBean); //폼에 입력한 수정 객체여기로 주고
		return "user/modify";
	}
	
	@PostMapping("/modify_pro")
	public String modify_pro(@Valid @ModelAttribute("modifyUserBean")UserBean modifyUserBean, BindingResult result) {
		if(result.hasErrors() ) {
			return "user/modify";
		}
		userService.modifyUserInfo(modifyUserBean); //업데이트 처리 수정 객체
		
		return "user/modify_success";
	}
	
	//로그아웃 처리
	@GetMapping("/logout")
	public String logout() {
		
		loginUserBean.setUserLogin(false);
		
		return "user/logout";
	}
	
	@GetMapping("/not_login")
	public String not_login() {
		
		return "user/not_login";
	}
	
	
	
	
}

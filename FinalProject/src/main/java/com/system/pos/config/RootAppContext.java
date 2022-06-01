package com.system.pos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;

import com.system.pos.beans.UserBean;

@Configuration //프로젝트 작업 시 사용할 bean 정의하는 설정 클래스
public class RootAppContext {
	
	@Bean("loginUserBean") //얘는 여러 장소에서 사용될 애라 여기서 정의한다.-로그인한 유저인지 확인용 
	@SessionScope
	public UserBean loginUserBean() {
		return new UserBean();
	}

	
	
}

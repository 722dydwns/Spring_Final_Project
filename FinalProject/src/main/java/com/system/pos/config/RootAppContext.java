package com.system.pos.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.context.annotation.SessionScope;

import com.system.pos.beans.BoardInfoBean;
import com.system.pos.beans.ContentBean;
import com.system.pos.beans.SellingBean;
import com.system.pos.beans.UserBean;

@Configuration //프로젝트 작업 시 사용할 bean 정의하는 설정 클래스
public class RootAppContext {
	//DataSource 객체 빈 등록 
	@Bean
	public DataSource source() {
		//스프링부트에서 DataSouce 객체 등록을 위해서는 pom.xml 에 추가할 의존 설정 
		BasicDataSource source = new BasicDataSource();
		source.setDriverClassName("com.mysql.cj.jdbc.Driver");
		source.setUrl("jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Seoul");
		source.setUsername("root");
		source.setPassword("1234");
		
		return source;
	}
	//JDBC Template  등록 : DataSouce 의 접속 정보 활용하여 DB 연결 
	@Bean 
	public JdbcTemplate db(BasicDataSource source) {
		JdbcTemplate db = new JdbcTemplate(source); //DB 연동
		return db;
	}
	//로그인 User를 @SessionScope으로 빈 등록 시켜둠
	@Bean("loginUserBean") //얘는 여러 장소에서 사용될 애라 여기서 정의한다.-로그인한 유저인지 확인용 
	@SessionScope
	public UserBean loginUserBean() {
		return new UserBean();
	}
	//사용자 데이터 객체 빈 등록 
	@Bean
	public UserBean userBean() {
		return new UserBean();
	}
	//물품 데이터 객체 빈 등록
	@Bean
	public ContentBean contentBean() {
		return new ContentBean();
	}
	// 판매 정보 데이터 객체 빈 등록
	@Bean 
	public SellingBean sellingBean() {
		return new SellingBean();
	}
	//메뉴 게시판 정보 빈 등록
	@Bean
	public BoardInfoBean boardInfoBean() {
		return new BoardInfoBean();
	}
}

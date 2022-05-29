package com.system.pos.beans;

import javax.validation.constraints.NotBlank;

public class ContentBean {
	//필드
	private int content_idx; //자동 증가 
	
	@NotBlank //비면 안된다. 유효성 검사 
	private String content_subject; //제목 
	@NotBlank
	private String content_text;//내용
	
	private int content_board_idx; //보드_인덱스 - 이걸로 메뉴 구분하니까 
	private String content_date; //날짜 자동증가 
	
	//get, set() 
	public int getContent_idx() {
		return content_idx;
	}
	public void setContent_idx(int content_idx) {
		this.content_idx = content_idx;
	}
	public String getContent_subject() {
		return content_subject;
	}
	public void setContent_subject(String content_subject) {
		this.content_subject = content_subject;
	}
	public String getContent_text() {
		return content_text;
	}
	public void setContent_text(String content_text) {
		this.content_text = content_text;
	}
	public int getContent_board_idx() {
		return content_board_idx;
	}
	public void setContent_board_idx(int content_board_idx) {
		this.content_board_idx = content_board_idx;
	}
	public String getContent_date() {
		return content_date;
	}
	public void setContent_date(String content_date) {
		this.content_date = content_date;
	}
	
	
}

package com.system.pos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.pos.beans.ContentBean;
import com.system.pos.mapper.BoardMapper;

@Service
public class BoardService { 

	@Autowired
	private BoardMapper boardMapper;
	
	//사용자가 쓴 write 내용 객체를 Db에 저장하는 코드 
	public void addContentInfo(ContentBean writeContentBean) {
		
		boardMapper.addContentInfo(writeContentBean); //매퍼에 추가 내용물 담기 
	}
	//인덱스 받으면 이름 출력하는 코드 
	public String getBoardInfoName(int board_info_idx) {
		return boardMapper.getBoardInfoName(board_info_idx);
	}
	//목록가져오기 
	public List<ContentBean> getContentList(int board_info_idx) {
		return boardMapper.getContentList(board_info_idx);
	}
	//내용물 text 까지 가져오기 
	public ContentBean getContentInfo(int context_idx) {
		return boardMapper.getContentInfo(context_idx);
	}
	
	
}

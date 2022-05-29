package com.system.pos.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.system.pos.beans.ContentBean;

@Mapper
public interface BoardMapper {
	
	//Content 테이블 내용 업로드 
	@Insert("INSERT INTO content_table(content_subject, content_text,  content_board_idx) VALUES (#{content_subject}, #{content_text}, #{content_board_idx})")
	void addContentInfo(ContentBean wrtieContentBean);
	
	//메뉴 인덱스 번호가져오기 
	@Select("SELECT board_info_name FROM board_info_table WHERE board_info_idx= #{board_info_idx}")
	String getBoardInfoName(int board_info_idx);

	//게시판 내용 목록 내용 가져오기 
	@Select("SELECT content_idx, content_subject, content_date as content_date FROM content_table WHERE content_board_idx = #{board_info_idx} ORDER BY content_idx DESC")
	List<ContentBean> getContentList(int board_info_idx); //얘 받는 애
	
	//내용물 Read 가져외기
	@Select("SELECT content_subject, content_text, content_date as content_date FROM content_table WHERE content_idx = #{content_idx}")
	ContentBean getContentInfo(int content_idx);
	
}

package com.system.pos.mapper;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.system.pos.beans.ContentBean;
import com.system.pos.beans.SellingBean;

@Mapper
public interface BoardMapper { 
	//메뉴 이름 가져오기 
	@Select("SELECT board_info_name FROM board_info_table WHERE board_info_idx= #{board_info_idx}")
	String getBoardInfoName(int board_info_idx);
	
	//사용자가 쓴 내용물 쓰기 
	@Insert("INSERT INTO content_table(content_name,content_price, content_count, content_board_idx) VALUES (#{content_name}, #{content_price}, #{content_count}, #{content_board_idx})")
	void addContentInfo(ContentBean writeContentBean);

	//상품목록 가져오기 
	@Select("SELECT content_idx, content_date as content_date, content_name, content_price, content_count FROM content_table WHERE content_board_idx = #{board_info_idx} ORDER BY content_idx DESC ")
	List<ContentBean> getContentList(int board_info_idx); 

	//'상품명 정보 검색 기능' 상품명 입력받으면 가격, 수량 가져오기 
	@Select("SELECT content_name, content_price, content_count, content_date FROM content_table WHERE content_name = #{content_name}")
	List<ContentBean> getSearchBean(String content_name); //이름 기준으로 값 가져오기 
	
	//'상품명 판매 계산 기능' 사용자가 판매 상품명,-> 가격 가져오기
	@Select("SELECT content_price FROM content_table WHERE content_name = #{content_name}")
	int getSellPrice(String content_name);
	
	//판매 정보 저장하기 
	@Insert("INSERT INTO selling_table(selling_name, selling_price) VALUES (#{selling_name}, #{selling_price})")
	void addSellingInfo(SellingBean sellingInfo); 
	
	//판매 정보 모두 가져오기 
	@Select("SELECT * FROM selling_table ORDER BY selling_idx DESC")
	List<SellingBean> getSellingInfo(); //그냥 전체 다 가져오기 
	
	//'최근 일주일' 통계 정보 가져오기 
	@Select("SELECT * FROM selling_table WHERE selling_date BETWEEN DATE_ADD(NOW(), INTERVAL -1 WEEK ) AND NOW() ORDER BY selling_date ASC")
	List<SellingBean> getOneWeekSellingInfo(); //최근 일주일 
	
	//최근 한달 통계 판매 정보 가져오기 
	@Select("SELECT * FROM selling_table WHERE selling_date BETWEEN DATE_ADD(NOW(), INTERVAL -1 MONTH ) AND NOW() ORDER BY selling_date ASC")
	List<SellingBean> getOneMonthSellingInfo(); //최근 한달 
	
	//지정날짜 판매 정보 가져오기 
	@Select("select * from selling_table where selling_date between date(#{start}) and date(#{end})+1  order by selling_date ASC")
	List<SellingBean> getTotalInfo(String start, String end); //시작 끝 날짜 받아서 select
}

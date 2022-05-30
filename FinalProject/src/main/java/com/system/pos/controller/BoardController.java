package com.system.pos.controller;

import java.util.List;

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

import com.system.pos.beans.ContentBean;
import com.system.pos.beans.SellingBean;
import com.system.pos.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {

	//Service
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/main") //메뉴 게시판 인덱스 번호 받기 타입 불일치 에러뜸
	public String main(@RequestParam("board_info_idx") int board_info_idx,
						Model model) {
		model.addAttribute("board_info_idx", board_info_idx);
		
		model.addAttribute("A", board_info_idx); //if 분기문 쓸 용도 
		
		String boardInfoName = boardService.getBoardInfoName(board_info_idx);
		model.addAttribute("boardInfoName", boardInfoName); //뷰에 전달할 변수 세팅
		
		//상품목록 가져오기 - 메뉴1
		List<ContentBean> contentList = boardService.getContentList(board_info_idx);
		model.addAttribute("contentList", contentList);
		
		//판매목록 띄우고 싶은데 ㅠㅠ 항 되려나 ? ㅠㅠㅠㅠ
		if(board_info_idx == 2) {
			List<SellingBean> sellingList = boardService.getSellingInfo();//전체 다 가져옴 
			model.addAttribute("sellingList", sellingList);
		}
		return "board/main";
	}
	
	//추가 상품목록 쓰기 
	@GetMapping("/write")
	public String write(@ModelAttribute("writeContentBean") ContentBean writeContentBean,
			@RequestParam("board_info_idx") int board_info_idx) {
		
		writeContentBean.setContent_board_idx(board_info_idx); //파라미터에 들어온 보드 게시판 인덱스 번호를 얘한테도 세팅해줌 
		
		return "board/write";
	}
	@PostMapping("/write_pro")
	public String write_pro(@Valid @ModelAttribute("writeContentBean") ContentBean writeContentBean, BindingResult result) {
		if(result.hasErrors()) {
			return "board/write";
		}
		boardService.addContentInfo(writeContentBean);
		
		return "board/write_success";
	}
	
	//상품검색 - 이름 입력 시 -> 가격, 수량 정보 가져오기 
	@RequestMapping("/search")
	public String search(@ModelAttribute("SearchBean") ContentBean SearchBean, Model model) {
		
		if(SearchBean.getContent_name() != null ) {
			List<ContentBean> researchBean = boardService.getSearchBean(SearchBean.getContent_name());
			model.addAttribute("researchBean", researchBean);
		}
		return "board/search";
	}
	//판매처리 (계산 + 판매정보 저장)
	@RequestMapping("/selling")
	public String selling(@ModelAttribute("sellingBean") ContentBean sellingBean, Model model) {
		//상품계산하기 
		if(sellingBean.getContent_name() != null ) {			
			//총가격 계산
			int price = boardService.getSellPrice(sellingBean.getContent_name()); //상품명에 대한 가격 받아서 
			price = price * sellingBean.getContent_count();
			
			sellingBean.setContent_price(price); //총가격 세팅			
			sellingBean.setContent_board_idx(2);
			
			//계산과 동시에 -> 판매정보 DB에 저장
			SellingBean InfoSell = new SellingBean();

			InfoSell.setSelling_name(sellingBean.getContent_name());//이름 저장
			InfoSell.setSelling_price(sellingBean.getContent_price()); //가격저장
			
			boardService.addSellingInfo(InfoSell);			
			
			model.addAttribute("price", price); //판매 가격만 뷰에 바로 보내기	
			
			String idx = "2";
			model.addAttribute("idx", idx);
		} 
		return "board/selling";
	}
	
	
}
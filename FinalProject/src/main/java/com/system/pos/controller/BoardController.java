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
import com.system.pos.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {

	//Service
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/main") //메뉴 게시판 인덱스 번호 받기 타입 불일치 에러뜸 (value="pageNo", required=false)
	public String main(@RequestParam("board_info_idx") int board_info_idx,
						Model model) {
		model.addAttribute("board_info_idx", board_info_idx);
		
		String boardInfoName = boardService.getBoardInfoName(board_info_idx);
		model.addAttribute("boardInfoName", boardInfoName); //뷰에 전달할 변수 세팅
		
		//목록 가져오기
		List<ContentBean> contentList = boardService.getContentList(board_info_idx);
		model.addAttribute("contentList", contentList);
		
		return "board/main";
	}
	
	@GetMapping("/read") //DB 속 데이터를 view 에 보내려면 Model 객체 사용해서 세팅해주어야 함
	public String read(@RequestParam("board_info_idx") int board_info_idx,
						@RequestParam("content_idx")int content_idx,
						Model model) {
		model.addAttribute("board_info_idx", board_info_idx); //여기에서 메뉴게시판 인덱스 일치시켜줌 
		
		ContentBean readContentBean = boardService.getContentInfo(content_idx);
		model.addAttribute("readContentBean", readContentBean);
		
		return "board/read";
	}
	
	//게시글 작성 -> 
	@GetMapping("/write")
	public String write(@ModelAttribute("wrtieContentBean") ContentBean wrtieContentBean,
			@RequestParam("board_info_idx") int board_info_idx) {
		wrtieContentBean.setContent_board_idx(board_info_idx); //파라미터에 들어온 보드 게시판 인덱스 번호를 얘한테도 세팅해줌 

		return "board/write";
	}
	@PostMapping("/wrtie_pro") //사용자 입력 데이터 담기 
	public String wrtie_pro(@Valid @ModelAttribute("wrtieContentBean") ContentBean wrtieContentBean, BindingResult result) {
		if(result.hasErrors()) {
			return "board/wrtie";
		}
		boardService.addContentInfo(wrtieContentBean);
		
		return "board/write_success";
	}
	
	
	@GetMapping("/modify")
	public String modify() {
		return "board/modify";
	}
	
	@GetMapping("/delete")
	public String delete() {
		return "board/delete";
	}
}

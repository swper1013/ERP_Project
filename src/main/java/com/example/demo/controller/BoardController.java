package com.example.demo.controller;

import com.example.demo.dto.BoardDTO;
import com.example.demo.dto.PageRequestDTO;
import com.example.demo.dto.PageResponesDTO;
import com.example.demo.entity.Board;
import com.example.demo.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.util.List;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    //서비스를 가져오기 그리고 필요하다면 user등등 다
    private final BoardService boardService;
    //댓글도
    //이미지도

    @GetMapping("/register")
    public void register(){

    }

    @PostMapping("/register")
    public String registerPro(BoardDTO boardDTO) {
        boardService.register(boardDTO);

        return "redirect:/board/list";
    }

    @GetMapping("/list")
    public String list(Model model, PageRequestDTO pageRequestDTO) {

//        List<BoardDTO> boardList = boardService.selectAll();
//
//        model.addAttribute("list", boardList);

        PageResponesDTO<BoardDTO>  boardDTOPageResponesDTO
                =  boardService.list(pageRequestDTO);

        model.addAttribute("boardDTOPageResponesDTO", boardDTOPageResponesDTO);

        return  null;
    }
}

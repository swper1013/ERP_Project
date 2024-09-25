package com.example.demo.controller;

import com.example.demo.dto.BoardDTO;
import com.example.demo.dto.PageRequestDTO;
import com.example.demo.dto.PageResponesDTO;
import com.example.demo.entity.Board;
import com.example.demo.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String register(BoardDTO boardDTO,Model model){
        model.addAttribute("boardDTO", boardDTO);
        return "board/register";
    }

    @PostMapping("/register")
    public String registerPro(@Valid BoardDTO boardDTO, BindingResult bindingResult,
                              Model model) { //RedirectAttributes redirectAttributes
        //html에서 object를 사용하기 위해서 thymeleaf

        if(bindingResult.hasErrors()) { //유효성검사란 에러가 있냐?
            log.info("에러");
            log.info("에러");
            log.info("에러");
            log.info("에러");
            log.info(bindingResult.getAllErrors()); //유효성검사에 대한 결과
            return "/board/register";
        }

        log.info("파라미터로 입력된 : " + boardDTO);
        boardService.register(boardDTO);
        //방법 2가지
        //redirectAttributes.addAttribute("errors", bindingResult.getAllErrors());
        //model.addAttribute("boardDTO", boardDTO);
        return "redirect:/board/list";
    }

    @GetMapping("/list")
    public String list(Model model, PageRequestDTO pageRequestDTO) {

        log.info("pageR : " + pageRequestDTO);
    //List<BoardDTO> boardList = boardService.selectAll();
    //
    //model.addAttribute("list", boardList);

        PageResponesDTO<BoardDTO>  boardDTOPageResponesDTO
                =  boardService.list(pageRequestDTO);

        model.addAttribute("boardDTOPageResponesDTO", boardDTOPageResponesDTO);

        return  null;
    }
}

package org.zerock.board.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.board.dto.BoardDTO;
import org.zerock.board.dto.BoardListDTO;
import org.zerock.board.dto.PageRequestDTO;
import org.zerock.board.dto.PageResultDTO;
import org.zerock.board.repository.MemberRepository;
import org.zerock.board.service.BoardService;

import java.util.List;

@Controller
@RequestMapping("/board")
@Log4j2
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final MemberRepository mmemberRepository;

    private static final String DUMMY_LOGINED_USER = "hong@sjcusw.ac.kr";

    @GetMapping("/list")
    public String list(@ModelAttribute PageRequestDTO pageRequestDTO, Model model) {

        PageResultDTO result = new PageResultDTO();
        result.setDtoList(boardService.getList(pageRequestDTO));

        model.addAttribute("result", result);
        return "board/list";
    }

    @GetMapping("/{id}")
    public String get(@RequestParam Long id, Model model) {

        model.addAttribute("id", boardService.get(id));
        return "board/read";
    }

    @GetMapping("/register")
    public String register(Model model) {

        model.addAttribute("member", mmemberRepository.findById(DUMMY_LOGINED_USER).orElseThrow());
        return "board/register";
    }

    @PostMapping("/register")
    public String create(BoardDTO dto, RedirectAttributes redirectAttributes) {

        redirectAttributes.addAttribute("id", boardService.create(dto));
        return "redirect:/board/list";
    }

    @PostMapping("/modify")
    public String modify(BoardDTO dto, RedirectAttributes redirectAttributes) {

        redirectAttributes.addAttribute("result", boardService.modify(dto));
        return "redirect:/board/list";
    }

    @PostMapping("/remove")
    public String remove(@RequestParam Long id, RedirectAttributes redirectAttributes) {

        redirectAttributes.addAttribute("result", boardService.removeById(id));
        return "redirect:/board/list";
    }
}

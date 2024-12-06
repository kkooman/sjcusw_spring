package org.zerock.board.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.board.dto.BoardDTO;
import org.zerock.board.dto.PageRequestDTO;
import org.zerock.board.dto.PageResultDTO;
import org.zerock.board.repository.MemberRepository;
import org.zerock.board.service.BoardService;

@Controller
@RequestMapping("/board")
@Log4j2
@RequiredArgsConstructor
public class BoardController {

    private static final String DUMMY_LOGIN_USER = "hong@sjcusw.ac.kr";
    private final BoardService boardService;
    private final MemberRepository mmemberRepository;

    @GetMapping("/list")
    public String list(@ModelAttribute PageRequestDTO pageRequestDTO, Model model) {

        PageResultDTO result = new PageResultDTO();
        result.setDtoList(boardService.getList(pageRequestDTO));

        model.addAttribute("result", result);
        return "board/list";
    }

    @GetMapping("/{id}")
    public String get(@RequestParam Long id, Model model) {

        model.addAttribute("board", boardService.get(id));
        return "board/read";
    }

    @GetMapping("/register")
    public String register(Model model) {

        model.addAttribute("member", mmemberRepository.findById(DUMMY_LOGIN_USER).orElseThrow());
        return "board/register";
    }

    @PostMapping("/register")
    public String create(BoardDTO dto, RedirectAttributes redirectAttributes) {

        redirectAttributes.addAttribute("id", boardService.create(dto));
        return "redirect:/board/list";
    }

    @GetMapping("/modify")
    public String modify(@RequestParam Long id, Model model) {

        model.addAttribute("dto", boardService.get(id));
        return "board/modify";
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

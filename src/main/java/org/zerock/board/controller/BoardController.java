package org.zerock.board.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.board.dto.BoardDTO;
import org.zerock.board.service.BoardService;

@Controller
@RequestMapping("/board")
@Log4j2
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
    public String list(@RequestParam(required = false) String title, Model model) {

        model.addAttribute("list", boardService.getList(title));
        return "list";
    }

    @GetMapping("/create")
    public String create(BoardDTO dto, RedirectAttributes redirectAttributes) {

        redirectAttributes.addAttribute("id", boardService.create(dto));
        return "redirect:/board/list";
    }

    @GetMapping("/modify")
    public String modify(BoardDTO dto, RedirectAttributes redirectAttributes) {

        redirectAttributes.addAttribute("result", boardService.modify(dto));
        return "redirect:/board/list";
    }

    @GetMapping("/remove")
    public String remove(@RequestParam Long id, RedirectAttributes redirectAttributes) {

        redirectAttributes.addAttribute("result", boardService.removeById(id));
        return "redirect:/board/list";
    }
}

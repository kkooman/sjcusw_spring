package org.zerock.board.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.board.entity.Board;
import org.zerock.board.entity.Member;
import org.zerock.board.repository.BoardRepository;
import org.zerock.board.repository.MemberRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@Log4j2
@RequiredArgsConstructor
public class DevInitController {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    @GetMapping("/init")
    public String init() {
        boardRepository.deleteAll();
        memberRepository.deleteAll();

        Member member = Member.builder().name("홍길동").email("hong@sjcusw.ac.kr").pwd("1234").build();
        memberRepository.save(member);

        List<Board> hongBoards = new ArrayList<>();
        hongBoards.add(Board.builder().title("인사드립니다.").content("과제물 이렇게 하는거 맞나요?").writer(member).build());
        hongBoards.add(Board.builder().title("다시한번 인사드립니다.").content("글이 등록이 안된것 같아요. 과제물 이렇게 하는거 맞나요?").writer(member).build());
        boardRepository.saveAll(hongBoards);
        return "ok";
    }
}

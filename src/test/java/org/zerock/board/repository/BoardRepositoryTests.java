package org.zerock.board.repository;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.board.entity.Board;

import java.util.Optional;

@SpringBootTest
@EnableJpaAuditing
public class BoardRepositoryTests {

    @Autowired
    private BoardRepository boardRepository;

    /*
     * @Test public void insertBoard() {
     *
     * IntStream.rangeClosed(1,100).forEach(i -> {
     *
     * Member member = Member.builder().email("user"+i +"@bbb.com").build();
     *
     * Board board = Board.builder() .title("Title..."+i) .content("Content...." +
     * i) .writer(member) .build();
     *
     * boardRepository.save(board);
     *
     * });
     *
     * }
     */


    @Transactional
    @Test
    public void testRead1() {
        //조인명령을 활용한다.
        Optional<Board> result = boardRepository.findById(101L); //데이터베이스에 존재하는 번호

        Board board = result.get();

        System.out.println("testRead1()..............");
        System.out.println(board);
        System.out.println(board.getWriter());

    }


}

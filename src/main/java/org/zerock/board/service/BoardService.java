package org.zerock.board.service;


import org.zerock.board.dto.BoardDTO;
import org.zerock.board.dto.BoardListDTO;
import org.zerock.board.entity.Board;
import org.zerock.board.entity.Member;

import java.util.List;

public interface BoardService {

    Long create(BoardDTO dto);

    boolean modify(BoardDTO dto);

    boolean removeById(Long id);

    List<BoardListDTO> getList(String title);

    BoardDTO get(Long id);

    default Board dtoToEntity(BoardDTO dto) {

        Member member = Member.builder().email(dto.getWriterEmail()).build();

        Board board = Board.builder()
                .bno(dto.getBno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(member)
                .build();
        return board;
    }
}

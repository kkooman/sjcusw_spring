package org.zerock.board.service;


import org.zerock.board.dto.BoardDTO;
import org.zerock.board.dto.BoardListDTO;
import org.zerock.board.dto.PageRequestDTO;

import java.util.List;

public interface BoardService {

    Long create(BoardDTO dto);

    boolean modify(BoardDTO dto);

    boolean removeById(Long id);

    List<BoardListDTO> getList(PageRequestDTO pageRequestDTO);

    BoardDTO get(Long id);
}

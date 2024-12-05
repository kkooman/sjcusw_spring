package org.zerock.board.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.zerock.board.dto.BoardDTO;
import org.zerock.board.dto.BoardListDTO;
import org.zerock.board.dto.PageRequestDTO;
import org.zerock.board.entity.Board;
import org.zerock.board.entity.QBoard;
import org.zerock.board.repository.BoardRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
@Log4j2
public class BoardServiceImpl implements BoardService {

    private final BoardRepository repository;

    @Override
    public Long create(BoardDTO dto) {

        log.debug("create board : {}", dto);

        Board board = Board.of(dto);
        repository.save(board);
        return board.getBno();
    }

    @Override
    public boolean modify(BoardDTO dto) {
        log.info(dto);

        try {
            Board board = repository.findById(dto.getBno()).orElseThrow();
            board.setTitle(dto.getTitle());
            board.setContent(dto.getContent());
            repository.save(board);
            return true;
        } catch (Exception e) {
            log.error("error modify : {}", dto, e);
        }
        return false;
    }

    @Override
    public boolean removeById(Long id) {
        log.info("remove id : {}", id);

        try {
            repository.deleteById(id);
            return true;
        } catch (Exception e) {
            log.error("error delete : {}", id, e);
        }
        return false;
    }

    @Override
    public List<BoardListDTO> getList(PageRequestDTO pageRequestDTO) {
        log.info("title param : {}", pageRequestDTO);

        QBoard board = QBoard.board;
        if (pageRequestDTO.getKeyword() == null) {
            pageRequestDTO.setKeyword("");
        }
        Iterable<Board> list = repository.findAll(board.title.contains(pageRequestDTO.getKeyword()));
        return StreamSupport.stream(list.spliterator(), false).map(Board::toListDTO).collect(Collectors.toList());
    }

    @Override
    public BoardDTO get(Long id) {
        return repository.findById(id).map(Board::toDTO).orElseThrow();
    }

}

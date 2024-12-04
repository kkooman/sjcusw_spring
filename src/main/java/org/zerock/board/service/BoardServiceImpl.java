package org.zerock.board.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.zerock.board.dto.BoardDTO;
import org.zerock.board.dto.BoardListDTO;
import org.zerock.board.entity.Board;
import org.zerock.board.repository.BoardRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class BoardServiceImpl implements BoardService {

    private final BoardRepository repository;

    @Override
    public Long create(BoardDTO dto) {

        log.info(dto);

        Board board = dtoToEntity(dto);
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
    public List<BoardListDTO> getList(String title) {
        log.info("title param : {}", title);
        List<Board> list = repository.findByTitleLike(title);
        return list.stream().map(Board::toDTO).collect(Collectors.toList());
    }
}

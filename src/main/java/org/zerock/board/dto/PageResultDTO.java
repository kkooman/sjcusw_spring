package org.zerock.board.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PageResultDTO {

    private List<BoardListDTO> dtoList;

    private int prev;
    private int next;
    private int page;

    private List pageList;
}
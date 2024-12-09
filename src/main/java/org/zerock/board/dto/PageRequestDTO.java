package org.zerock.board.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PageRequestDTO {

    private int page;
    private String type;
    private String keyword;

}
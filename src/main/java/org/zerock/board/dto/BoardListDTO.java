package org.zerock.board.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class BoardListDTO {
    private Long bno;
    private String title;
    private String writerName;
    private String writerEmail;
    private LocalDateTime regDate;
    private int replyCount;
}

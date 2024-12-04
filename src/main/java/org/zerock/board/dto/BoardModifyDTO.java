package org.zerock.board.dto;


import lombok.*;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardModifyDTO {

    private Long bno;

    private String title;

    private String content;
}

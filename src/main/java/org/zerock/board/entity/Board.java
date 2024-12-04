package org.zerock.board.entity;

import lombok.*;
import org.zerock.board.dto.BoardDTO;
import org.zerock.board.dto.BoardListDTO;

import javax.persistence.*;


@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = "writer")
@Table(name = "tbl_board")
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;

    private String title;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member writer;


    public BoardListDTO toListDTO() {

        BoardListDTO dto = new BoardListDTO();
        dto.setBno(bno);
        dto.setTitle(title);
        dto.setWriter(writer.getName());
        return dto;
    }

    public BoardDTO toDTO() {

        BoardDTO dto = new BoardDTO();
        dto.setBno(bno);
        dto.setTitle(title);
        return dto;
    }
}


package com.spring_boot_basic.service;

import com.spring_boot_basic.dto.BoardDTO;
import com.spring_boot_basic.dto.PageRequestDTO;
import com.spring_boot_basic.dto.PageResponseDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@Log4j2
@SpringBootTest
class BoardServiceImplTest {
    @Autowired
    private BoardService boardService;

    @Test
    public void testRegister() {
        BoardDTO boardDTO = BoardDTO.builder()
                .title("title...register")
                .content("content...register")
                .writer("regiUser01")
                .build();

        boardService.register(boardDTO);
    }

    @Test
    public void testReadOne() {
        Long bno = 101L;
        BoardDTO boardDTO = boardService.readOne(bno);
        log.info(boardDTO);
    }

    @Test
    public void testUpdate() {
        BoardDTO boardDTO = BoardDTO.builder()
                .bno(101L)
                .title("update...100")
                .content("update content 100")
                .build();
        boardService.modify(boardDTO);
    }

    @Test
    public void testList() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .type("tcw").keyword("1").page(1).size(10).build();

        PageResponseDTO<BoardDTO> responseDTO = boardService.list(pageRequestDTO);
        log.info(responseDTO);
        log.info("1) 페이지 번호 : " + responseDTO.getPage());
        log.info("2) 전체 게시물 수 : " + responseDTO.getTotal());
        log.info("3) 현재 페이지에 출력될 게시물을 반복문을 이용해서 순서대로 출력");
        for (BoardDTO boardDTO : responseDTO.getDtoList()) {
            log.info(boardDTO);
        }
    }
}
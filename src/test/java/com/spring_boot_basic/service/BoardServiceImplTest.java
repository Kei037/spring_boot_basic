package com.spring_boot_basic.service;

import com.spring_boot_basic.dto.BoardDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

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
}
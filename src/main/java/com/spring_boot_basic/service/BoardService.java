package com.spring_boot_basic.service;

import com.spring_boot_basic.dto.BoardDTO;
import com.spring_boot_basic.dto.PageRequestDTO;
import com.spring_boot_basic.dto.PageResponseDTO;

public interface BoardService {
    Long register(BoardDTO boardDTO);
    BoardDTO readOne(Long bno);
    void modify(BoardDTO boardDTO);
    void remove(Long bno);
    PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO);
}

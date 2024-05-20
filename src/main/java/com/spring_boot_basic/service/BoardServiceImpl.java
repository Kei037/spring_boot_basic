package com.spring_boot_basic.service;

import com.spring_boot_basic.domain.Board;
import com.spring_boot_basic.dto.BoardDTO;
import com.spring_boot_basic.dto.PageRequestDTO;
import com.spring_boot_basic.dto.PageResponseDTO;
import com.spring_boot_basic.repository.BoardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class BoardServiceImpl implements BoardService {
    private final ModelMapper modelMapper;
    private final BoardRepository boardRepository;

    @Override
    public Long register(BoardDTO boardDTO) {
        Board board = modelMapper.map(boardDTO, Board.class);
        return boardRepository.save(board).getBno(); // return bno
    }

    @Override
    public BoardDTO readOne(Long bno) {
        Optional<Board> optionalBoard = boardRepository.findById(bno);
        Board board = optionalBoard.orElseThrow();
        return modelMapper.map(board, BoardDTO.class);
    }

    @Override
    public void modify(BoardDTO boardDTO) {
        Optional<Board> optionalBoard = boardRepository.findById(boardDTO.getBno());
        Board board = optionalBoard.orElseThrow();
        board.change(boardDTO.getTitle(), boardDTO.getContent());
        boardRepository.save(board);
    }

    @Override
    public void remove(Long bno) {

    }

    @Override
    public PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO) {
        String[] types = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable("bno");

        Page<Board> boardPage = boardRepository.searchAll(types, keyword, pageable);

        List<BoardDTO> boardDTOList = new ArrayList<>();
        for (Board board : boardPage.getContent()) {
            boardDTOList.add(modelMapper.map(board, BoardDTO.class));
        }

        return PageResponseDTO.<BoardDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(boardDTOList)
                .total((int)boardPage.getTotalElements())
                .build();
    }
}

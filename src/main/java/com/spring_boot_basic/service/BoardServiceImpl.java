package com.spring_boot_basic.service;

import com.spring_boot_basic.domain.Board;
import com.spring_boot_basic.dto.BoardDTO;
import com.spring_boot_basic.repository.BoardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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
}

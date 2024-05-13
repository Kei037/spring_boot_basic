package com.spring_boot_basic.repository.search;

import com.spring_boot_basic.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardSearch {
    Page<Board> search1(Pageable pageable);
}

package com.spring_boot_basic.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class PageResponseDTO<E> {
    private int page;
    private int size;
    private int total;

    private int start;  // 시작 페이지 번호
    private int end;    // 끝 페이지 번호

    private boolean prev;   // 이전 페이지 존재 여부
    private boolean next;   // 다음 페이지 존재 여부

    private List<E> dtoList;

    @Builder(builderMethodName = "withAll")
    public PageResponseDTO(PageRequestDTO pageRequestDTO, List<E> dtoList, int total) {
        if (total <= 0) {
            return;
        }
        this.page = pageRequestDTO.getPage();
        this.size = pageRequestDTO.getSize();
        this.total = total;
        this.dtoList = dtoList;

        this.end = (int) (Math.ceil(page / 10.0)) * 10;
        this.start = this.end - 9;
        int last = (int) (Math.ceil(total / (double)this.size));
        this.end = Math.min(last, this.end);

        this.prev = this.start > 1;
        this.next = total > this.end * size;
    }
}

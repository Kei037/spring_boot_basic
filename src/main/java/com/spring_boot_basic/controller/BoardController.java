package com.spring_boot_basic.controller;

import com.spring_boot_basic.dto.BoardDTO;
import com.spring_boot_basic.dto.PageRequestDTO;
import com.spring_boot_basic.dto.PageResponseDTO;
import com.spring_boot_basic.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/board")
@Log4j2
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model) {
        PageResponseDTO<BoardDTO> responseDTO = boardService.list(pageRequestDTO);

        log.info("list...............");

        model.addAttribute("responseDTO", responseDTO);
    }

    @GetMapping("/register")
    public void register() {
        log.info("register get...");
    }

    @PostMapping("/register")
    public String registerPost(@Valid BoardDTO boardDTO,
                               BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        log.info("board POST register...");

        if (bindingResult.hasErrors()) {
            log.info("has errors...");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());

            return "redirect:/board/register";
        }

        log.info(boardDTO);
        Long bno = boardService.register(boardDTO);
        redirectAttributes.addFlashAttribute("result", bno);

        return "redirect:/board/list";
    }
}

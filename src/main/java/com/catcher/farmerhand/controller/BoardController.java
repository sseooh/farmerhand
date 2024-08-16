package com.catcher.farmerhand.controller;

import com.catcher.farmerhand.domain.Board;
import com.catcher.farmerhand.domain.Type;
import com.catcher.farmerhand.repository.BoardRepository;
import com.catcher.farmerhand.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private TypeRepository typeRepository;

    // 귀농귀촌 체험 소식 (프로그램 게시판) - type_id가 1인 데이터만 표시
    @GetMapping("/program-board")
    public String showProgramBoard(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        Type programType = typeRepository.findById(1L).orElseThrow(() -> new IllegalArgumentException("Invalid type ID: 1"));

        List<Board> boards;
        if (keyword == null || keyword.isEmpty()) {
            boards = boardRepository.findByType(programType);
        } else {
            boards = boardRepository.searchByTypeAndKeyword(programType, keyword);
        }

        model.addAttribute("boards", boards);
        model.addAttribute("boardType", "프로그램");
        model.addAttribute("keyword", keyword); // 검색어를 다시 폼에 표시하기 위함
        return "program-board"; // program-board.html 렌더링
    }

    // 귀농귀촌 지원 소식 (정책 게시판) - type_id가 2인 데이터만 표시
    @GetMapping("/policy-board")
    public String showPolicyBoard(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        Type policyType = typeRepository.findById(2L).orElseThrow(() -> new IllegalArgumentException("Invalid type ID: 2"));

        List<Board> boards;
        if (keyword == null || keyword.isEmpty()) {
            boards = boardRepository.findByType(policyType);
        } else {
            boards = boardRepository.searchByTypeAndKeyword(policyType, keyword);
        }

        model.addAttribute("boards", boards);
        model.addAttribute("boardType", "정책");
        model.addAttribute("keyword", keyword); // 검색어를 다시 폼에 표시하기 위함
        return "policy-board"; // policy-board.html 렌더링
    }
}

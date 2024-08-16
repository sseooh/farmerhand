package com.catcher.farmerhand.controller;

import com.catcher.farmerhand.domain.Community;
import com.catcher.farmerhand.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/community-board")
public class CommunityController {

    private final CommunityService communityService;

    @Autowired
    public CommunityController(CommunityService communityService) {
        this.communityService = communityService;
    }

    @GetMapping
    public String getAllCommunities(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        List<Community> communities;

        if (keyword != null && !keyword.isEmpty()) {
            communities = communityService.searchCommunities(keyword);
        } else {
            communities = communityService.getAllCommunities();
        }

        model.addAttribute("communities", communities);
        model.addAttribute("keyword", keyword);
        return "community-board"; // HTML 파일의 이름
    }

    @GetMapping("/{id}")
    public String getCommunityById(@PathVariable Long id, Model model) {
        Optional<Community> community = communityService.getCommunityById(id);
        if (community.isPresent()) {
            model.addAttribute("community", community.get());
            return "community-detail"; // 커뮤니티 상세보기를 위한 HTML 파일의 이름
        } else {
            return "redirect:/community-board"; // 해당 커뮤니티가 없으면 게시판 목록으로 리다이렉트
        }
    }

    @PostMapping
    public String createCommunity(@ModelAttribute Community community) {
        communityService.createCommunity(community);
        return "redirect:/community-board"; // 생성 후 게시판 목록으로 리다이렉트
    }

    @PutMapping("/{id}")
    public String updateCommunity(@PathVariable Long id, @ModelAttribute Community updatedCommunity) {
        communityService.updateCommunity(id, updatedCommunity);
        return "redirect:/community-board"; // 업데이트 후 게시판 목록으로 리다이렉트
    }

    @DeleteMapping("/{id}")
    public String deleteCommunity(@PathVariable Long id) {
        communityService.deleteCommunity(id);
        return "redirect:/community-board"; // 삭제 후 게시판 목록으로 리다이렉트
    }
}

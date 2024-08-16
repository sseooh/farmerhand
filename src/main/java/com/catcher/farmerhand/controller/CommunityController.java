package com.catcher.farmerhand.controller;

import com.catcher.farmerhand.domain.Comment;
import com.catcher.farmerhand.domain.Community;
import com.catcher.farmerhand.service.CommunityService;
import com.catcher.farmerhand.service.UserService;
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
    private final UserService userService;

    @Autowired
    public CommunityController(CommunityService communityService, UserService userService) {
        this.communityService = communityService;
        this.userService = userService;
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

    @GetMapping("/{id}")
    public String getCommunityById(@PathVariable Long id, Model model) {
        Optional<Community> community = communityService.getCommunityById(id);
        if (community.isPresent()) {
            List<Comment> comments = communityService.getCommentsByCommunity(community.get());
            model.addAttribute("community", community.get());
            model.addAttribute("comments", comments);
            return "community-detail"; // 커뮤니티 상세보기를 위한 HTML 파일의 이름
        } else {
            return "redirect:/community-board"; // 해당 커뮤니티가 없으면 게시판 목록으로 리다이렉트
        }
    }

    @PostMapping("/{id}/comments")
    public String addComment(@PathVariable Long id, @RequestParam String content, @RequestParam Long userId) {
        Community community = communityService.getCommunityById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid community Id:" + id));
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setUser(userService.getUserById(userId).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + userId)));
        communityService.addCommentToCommunity(community, comment);
        return "redirect:/community-board/" + id;
    }

    @DeleteMapping("/{communityId}/comments/{commentId}")
    public String deleteComment(@PathVariable Long communityId, @PathVariable Long commentId) {
        communityService.deleteComment(commentId);
        return "redirect:/community-board/" + communityId;
    }
}

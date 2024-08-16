package com.catcher.farmerhand.controller;

import com.catcher.farmerhand.domain.Comment;
import com.catcher.farmerhand.domain.Community;
import com.catcher.farmerhand.domain.Users;
import com.catcher.farmerhand.service.CommentService;
import com.catcher.farmerhand.service.CommunityService;
import com.catcher.farmerhand.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/worried/{worriedId}/comments")
public class CommentController {

    private final CommentService commentService;
    private final CommunityService communityService;
    private final UserService userService;

    @Autowired
    public CommentController(CommentService commentService, CommunityService communityService, UserService userService) {
        this.commentService = commentService;
        this.communityService = communityService;
        this.userService = userService;
    }

    @GetMapping
    public String getComments(@PathVariable Long worriedId, Model model) {
        Community community = communityService.getCommunityById(worriedId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid worried Id:" + worriedId));
        List<Comment> comments = commentService.getCommentsByCommunity(community);
        model.addAttribute("worried", community);
        model.addAttribute("comments", comments);
        return "worried-comments"; // HTML 뷰 파일 이름
    }

    @PostMapping
    public String addComment(@PathVariable Long worriedId, @RequestParam String content, @RequestParam Long userId) {
        Community worried = communityService.getCommunityById(worriedId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid worried Id:" + worriedId));
        Users user = userService.getUserById(userId) // 추가된 사용자 조회 로직
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + userId));
        Comment comment = new Comment(worried, user, content);
        commentService.addComment(comment);
        return "redirect:/worried/" + worriedId + "/comments";
    }

    @DeleteMapping("/{commentId}")
    public String deleteComment(@PathVariable Long worriedId, @PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return "redirect:/worried/" + worriedId + "/comments";
    }
}

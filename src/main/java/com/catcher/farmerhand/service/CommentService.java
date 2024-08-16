package com.catcher.farmerhand.service;

import com.catcher.farmerhand.domain.Comment;
import com.catcher.farmerhand.domain.Community;
import com.catcher.farmerhand.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> getCommentsByCommunity(Community community) {
        return commentRepository.findByCommunity(community);
    }

    public Comment addComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}

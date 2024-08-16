package com.catcher.farmerhand.repository;

import com.catcher.farmerhand.domain.Comment;
import com.catcher.farmerhand.domain.Community;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByCommunity(Community community);
}

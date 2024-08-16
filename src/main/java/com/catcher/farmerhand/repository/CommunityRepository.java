package com.catcher.farmerhand.repository;

import com.catcher.farmerhand.domain.Community;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommunityRepository extends JpaRepository<Community, Long> {

    List<Community> findByTitleContainingOrContentContaining(String titleKeyword, String contentKeyword);
}

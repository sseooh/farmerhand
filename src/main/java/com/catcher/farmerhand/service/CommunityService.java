package com.catcher.farmerhand.service;

import com.catcher.farmerhand.domain.Community;
import com.catcher.farmerhand.repository.CommunityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommunityService {

    private final CommunityRepository communityRepository;

    @Autowired
    public CommunityService(CommunityRepository communityRepository) {
        this.communityRepository = communityRepository;
    }

    public List<Community> getAllCommunities() {
        return communityRepository.findAll();
    }

    public Optional<Community> getCommunityById(Long id) {
        return communityRepository.findById(id);
    }

    public Community createCommunity(Community community) {
        return communityRepository.save(community);
    }

    public Community updateCommunity(Long id, Community updatedCommunity) {
        return communityRepository.findById(id)
                .map(community -> {
                    community.setTitle(updatedCommunity.getTitle());
                    community.setContent(updatedCommunity.getContent());
                    community.setCreatedAt(updatedCommunity.getCreatedAt());
                    community.setUser(updatedCommunity.getUser());
                    return communityRepository.save(community);
                })
                .orElseThrow(() -> new RuntimeException("Community not found with id " + id));
    }

    public void deleteCommunity(Long id) {
        communityRepository.deleteById(id);
    }

    public List<Community> searchCommunities(String keyword) {
        return communityRepository.findByTitleContainingOrContentContaining(keyword, keyword);
    }
}

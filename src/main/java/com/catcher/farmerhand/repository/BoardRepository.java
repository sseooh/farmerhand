package com.catcher.farmerhand.repository;

import com.catcher.farmerhand.domain.Board;
import com.catcher.farmerhand.domain.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    // 기존 메서드
    List<Board> findByType(Type type);

    // 검색 기능을 위한 JPQL 메서드
    @Query("SELECT b FROM Board b WHERE b.type = :type AND (b.title LIKE %:keyword% OR b.content LIKE %:keyword%)")
    List<Board> searchByTypeAndKeyword(@Param("type") Type type, @Param("keyword") String keyword);
}

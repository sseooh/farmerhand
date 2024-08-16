package com.catcher.farmerhand.repository;

import com.catcher.farmerhand.domain.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepository extends JpaRepository<Type, Long> {
    Type findByTypeName(String typeName);
}

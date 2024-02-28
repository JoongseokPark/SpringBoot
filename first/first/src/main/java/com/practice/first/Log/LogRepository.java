package com.practice.first.Log;

import org.springframework.data.jpa.repository.JpaRepository;

// CRUD...<Entity 클래스, PK 타입>
public interface LogRepository extends JpaRepository<LogEntity,Long> {
    
}


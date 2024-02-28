package com.practice.first.temperDB;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
// CRUD...<Entity 클래스, PK 타입>
public interface TemperRepository extends JpaRepository<TemperEntity,Long> {
    
    List<TemperEntity> findTop50ByOrderByIdAsc();
    List<TemperEntity> findByRecordatLessThanEqual(String recordAt);
    List<TemperEntity> findByIdGreaterThanEqualAndIdLessThanEqual(Long id1, Long id2);
    List<TemperEntity> findByRecordatGreaterThanEqualAndRecordatLessThanEqual(String date1, String date2);
}
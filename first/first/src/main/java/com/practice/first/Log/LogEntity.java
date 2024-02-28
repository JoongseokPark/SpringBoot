package com.practice.first.Log;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="logging")
public class LogEntity {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column
   private String recordat;
   private String statement;

   public Long getId() {
       return this.id;
   }

   public void setId(Long id) {
       this.id = id;
   }

   public String getRecordAt() {
       return this.recordat;
   }

   public void setRecordAt(String recordAt) {
       this.recordat = recordAt;
   }

   public String getStatement(String statement){
        return this.statement;
   }

   public void setStatement(String statement){
        this.statement = statement;
   }
}

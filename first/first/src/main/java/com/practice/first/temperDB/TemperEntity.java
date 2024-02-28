package com.practice.first.temperDB;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="temper")
public class TemperEntity {

   @Id
   private Long id;

   @Column
   private String recordat;
   private Float avgtmp;
   private Float maxtmp;
   private Float mintmp;

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

   public Float getAvgTmp() {
       return this.avgtmp;
   }

   public void setAvgTmp(Float avgTmp) {
       this.avgtmp = avgTmp;
   }

   public Float getMaxTmp() {
       return this.maxtmp;
   }

   public void setMaxTmp(Float maxTmp) {
       this.maxtmp = maxTmp;
   }

   public Float getMinTmp() {
       return this.mintmp;
   }

   public void setMinTmp(Float minTmp) {
       this.mintmp = minTmp;
   }


}

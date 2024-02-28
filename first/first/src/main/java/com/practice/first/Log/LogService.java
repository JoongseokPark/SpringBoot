package com.practice.first.Log;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;


@Service
public class LogService {

    private final LogRepository logRepository;

    public LogService(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public void insertLog(String statement) {
        //One row that will be inserted into DB
        LogEntity logEntity = new LogEntity();

        //String variable for RecordAt column via under 3 lines
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTime = currentTime.format(formatter);

        //Final Step that put data into Row(Entity)
        logEntity.setRecordAt(formattedTime);
        logEntity.setStatement(statement);
        
        //Actual Step for Inserting Row into DB Table
        logRepository.save(logEntity);
    }
}
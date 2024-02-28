package com.practice.first.Log;

import org.springframework.web.bind.annotation.*;


@RestController
public class LogController {

    private final LogService logService;

    public LogController(LogService logService) {
        this.logService = logService;
    }

    @PostMapping("/save")
    public void addLog(@RequestBody String statement) {
        logService.insertLog(statement);
    }

    
}
package com.practice.first.Log;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.events.Event;

//Sets of work that called periodically 
@Component
public class ConsoleScheduler {

    private final LogService logService;

    
    public ConsoleScheduler(LogService logService){
        this.logService = logService;
    }
    
    //Scheduler setuped for working every minute
    @Scheduled(cron = "0 0/5 * * * *")
    public void LogWritter(){
        //setting Statement message for log
        String logData = "Server is Running";
        
        logService.insertLog(logData);

    }

    @EventListener
    public void onContextCloseEvent(Event e){
        System.out.println(e);
    }

}
package com.practice.first.temperDB;



import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.practice.first.Log.LogService;
import org.springframework.stereotype.Component;

import groovy.transform.Undefined.EXCEPTION;



@Component
@Controller
public class PageController {

    private final LogService logService;

    public PageController(LogService logService) {
        this.logService = logService;
    }


    @Autowired
    TemperRepository temperRepository;

    //Home url 요청시 home.html 제공
    @GetMapping("/home")
    public String homepage() {
        logService.insertLog("HomePage Called"); //Leaving Log for Calling Chart
        return "home";
    }

    @GetMapping("/Cursor")
    public String ToPlayGround() {
        logService.insertLog("PlayGround Called"); //Leaving Log for Calling Chart
        return "Cursor";
    }
    
    @GetMapping("/OnAndOff")
    public String LightBulb() {
        return "LightBall";
    }

    @GetMapping("/temper")
    public String getTemperData(Model model) {
        List<TemperEntity> temperData = temperRepository.findAll();
        model.addAttribute("temperData", temperData);
        logService.insertLog("Table Called"); //Leaving Log for Calling Chart
        return "Table";
    }

    //Function for Calculating Mean of List
    private float calculateMean(List<Float> list, int routineTime) {
        float sum = 0;
        for (Float value : list) {
            sum += value;
        }
        return sum / routineTime;
    }

    @GetMapping("/graph")
    public String makeGraph(Model model, 
                            @RequestParam(value = "startdate",defaultValue="2000-01-01") String startDate, 
                            @RequestParam(value = "enddate",defaultValue = "2000-12-31") String endDate,
                            @RequestParam(value = "unit",defaultValue = "1") Integer unit) throws ParseException {
        
        //check if given startDate and endDate are Valid. 
        //The data boundary is between 2000 and 2024-02-04
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");   
        //try catch because of formatter.parse otherwise it generate error
        try{
            Date isStartDateValid = formatter.parse(startDate);
            Date isEndDateValid = formatter.parse(endDate);
            if (
                isStartDateValid.before(formatter.parse("2000-01-01")) ||
                isEndDateValid.after(formatter.parse("2024-02-04"))
            ){
                model.addAttribute("errorMessage", "Invalid Boundary. It should be between 2000-01-01 and 2024-02-04");
                return "graph";
            }
        } catch(EXCEPTION e){ 
            System.out.println(e.getMessage());
        }

        //if boundary is valid do below code to fetch Data from DB
        //it will finish with return "graph"

        List<TemperEntity> temperData = 
            temperRepository.findByRecordatGreaterThanEqualAndRecordatLessThanEqual(startDate,endDate);
        model.addAttribute("temperData", temperData);
                    
        // Extracting data for the chart
        List<String> labels = new ArrayList<>(); // List for storing labels
        List<Float> data = new ArrayList<>();   // List for storing data
        List<Float> lowdata = new ArrayList<>();
        List<Float> highdata = new ArrayList<>();  
        List<Float> differdata = new ArrayList<>();
        
        //Small lists that contain data temporary
        String small_labels = ""; 
        List<Float> small_data = new ArrayList<>();   
        List<Float> small_lowdata = new ArrayList<>();
        List<Float> small_highdata = new ArrayList<>();  
        List<Float> small_differdata = new ArrayList<>();

        //unit value choose routineTime value, but to init static value it takes step through tmp variable
        int routineTimetmp = 1; 
        if(unit == 1) routineTimetmp = 1;
        else if(unit == 2) routineTimetmp = 7;
        else if(unit == 3) routineTimetmp = 30;
        else if(unit == 4) routineTimetmp = 91;
        else if(unit == 5) routineTimetmp = 365;
        final int routineTime = routineTimetmp;
        int i=0; 
        
        // Extracting labels and data from the temperData list
        for (TemperEntity entity : temperData) {
            //add data into small setup lists 
            if(i == 0) small_labels = entity.getRecordAt();
            small_data.add(entity.getAvgTmp());
            small_lowdata.add(entity.getMinTmp());
            small_highdata.add(entity.getMaxTmp());
            small_differdata.add(entity.getMaxTmp()-entity.getMinTmp());

            i += 1; //i is value to compare with routineTime

            // for instance, if i is bigger then 1 move data to actual model lists from small setups
            if(i >= routineTime){
                //Get Mean Value of Each SmallData list except small_labels
                float periodMeanSmallData = calculateMean(small_data, routineTime); 
                float periodMeanSmallLowData = calculateMean(small_lowdata, routineTime); 
                float periodMeanSmallHighData = calculateMean(small_highdata, routineTime); 
                float periodMeanSmallDifferData = calculateMean(small_differdata, routineTime); 

                //add meanData to actually list will be attached to Model
                labels.add(small_labels); 
                data.add(periodMeanSmallData);      
                lowdata.add(periodMeanSmallLowData);
                highdata.add(periodMeanSmallHighData);
                differdata.add(periodMeanSmallDifferData);

                //reset small lists and i
                small_data.clear();
                small_lowdata.clear();
                small_highdata.clear();
                small_differdata.clear();
                i = 0;
            }
        }
        
        // final tranist toward Model via Variables made throughout function
        model.addAttribute("labels", labels);
        model.addAttribute("data", data);
        model.addAttribute("lowdata", lowdata);
        model.addAttribute("highdata", highdata);
        model.addAttribute("differdata", differdata);
        model.addAttribute("label", "Temperture");  // Set label for the dataset
        model.addAttribute("title", "Climate Chart"); // Set title for the chart
        logService.insertLog("Chart Called["+startDate+", "+endDate+"]"); //Leaving Log for Calling Chart
        return "graph";
    }

    @GetMapping("/chart")
    public String makeChart() {     
        logService.insertLog("Test Chart Called"); //Leaving Log for Calling Chart   
        return "chart";
    }

    @GetMapping("/ActiveEffect")
    public String getintoCSS() {
        logService.insertLog("CSS test Page Called"); //Leaving Log for Calling Chart
        return "ActiveEffect";
    }
    
}



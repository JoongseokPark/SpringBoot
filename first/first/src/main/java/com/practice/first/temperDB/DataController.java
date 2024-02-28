package com.practice.first.temperDB;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/data")
public class DataController{

    @Autowired
    TemperRepository temperRepository;

    @GetMapping("/top5")
    public List<TemperEntity> getSome() {
        return temperRepository.findTop50ByOrderByIdAsc();
    }

    @GetMapping("/temper")
    public List<TemperEntity> getTemperData(Model model) {
        List<TemperEntity> temperData = temperRepository.findAll();
        model.addAttribute("temperData", temperData);
        return temperData;
    }

}
package com.example.springbootoracle.controller;

import com.example.springbootoracle.entity.Trajectory;
import com.example.springbootoracle.service.TrajectoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class TableController {
    @Autowired
    TrajectoryService trajectoryService;

    @RequestMapping(value = "/table", method = RequestMethod.GET)
//    public List<Trajectory> getAllTrajectories() {
//        return trajectoryService.getAllTrajectories();
//    }
    public String getAllTrajectories(Model model) {
        List<Trajectory> trajList = trajectoryService.getAllTrajectories();
        model.addAttribute("trajList", trajList);
        return "table";
    }
}

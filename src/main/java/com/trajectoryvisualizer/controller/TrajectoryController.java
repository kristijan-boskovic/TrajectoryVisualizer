package com.trajectoryvisualizer.controller;

import com.trajectoryvisualizer.entity.Trajectory;
import com.trajectoryvisualizer.service.TrajectoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class TrajectoryController {
    @Autowired
    TrajectoryService trajectoryService;

    @RequestMapping(value = "/table", method = RequestMethod.GET)
    public String getAllTrajectories(Model model) {
        List<Trajectory> trajList = trajectoryService.getAllTrajectories();
        model.addAttribute("trajList", trajList);
        return "table";
    }

    @GetMapping({"/", "/index"})
    public String showHome() {
        return "index";
    }

    @GetMapping({"/map"})
    public String showMap() {
        return "map";
    }
}

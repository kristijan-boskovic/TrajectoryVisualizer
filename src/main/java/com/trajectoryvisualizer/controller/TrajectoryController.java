package com.trajectoryvisualizer.controller;

import com.sun.xml.internal.bind.v2.TODO;
import com.trajectoryvisualizer.entity.RawStudies;
import com.trajectoryvisualizer.service.TrajectoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class TrajectoryController {
    @Autowired
    TrajectoryService trajectoryService;

    @RequestMapping(value = "/table")
    public String getAllTrajectories(Model model) {
        List<RawStudies> trajList = trajectoryService.getAllTrajectories();
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

//    @RequestMapping(value = "/all")
//        public String getAllStudiesId(Model model) {
//            List<Study> studiesList = Util.getAvailableStudies();
//            model.addAttribute("studiesList", studiesList);
//            return "all";
//    }
    // TO DO: Napravi pomocu Dao i Service sve iz HermesController
    // TO DO: Napravi stranicu i home gumb za traclus studies
}

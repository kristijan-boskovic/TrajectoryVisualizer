package com.trajectoryvisualizer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static com.trajectoryvisualizer.controller.IndexController.rawMap;
import static com.trajectoryvisualizer.controller.IndexController.traclusMap;

/**
 * // TO DO
 */
@Controller
public class TrajectoryController {

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String showHome() {
        return "home";
    }

    @RequestMapping(value = "/map", method = RequestMethod.GET)
    public String rawMap(Model model) {
        model.addAttribute("rawMap", rawMap);
        model.addAttribute("traclusMap", traclusMap);
        return "map";
    }
}
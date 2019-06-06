package com.trajectoryvisualizer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static com.trajectoryvisualizer.controller.ClusterController.clusterDao;
import static com.trajectoryvisualizer.controller.RawController.rawDao;

/**
 * // TO DO
 */
@Controller
public class TrajectoryController {
//    @Autowired
//    RawService rawService;
//    @Autowired
//    TraclusService traclusService;

//    @RequestMapping(value = "/rawtable", method = RequestMethod.GET)
//    public String getAllRawData(Model model) {
//        List<RawStudies> rawList = rawService.getAllRawData();
//        model.addAttribute("rawList", rawList);
//        return "rawtable";
//    }

//    @RequestMapping(value = "/clustertable", method = RequestMethod.GET)
//    public String getAllTraclusData(Model model) {
//        List<TraclusStudies> tracList = traclusService.getAllTraclusData();
//        model.addAttribute("tracList", tracList);
//        return "clustertable";
//    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String showHome() {
        return "home";
    }

    @RequestMapping(value = "/map", method = RequestMethod.GET)
    public String rawMap(Model model) {
        model.addAttribute("rawMapList", rawDao.findAll());
        model.addAttribute("clusterMapList", clusterDao.findAll());
        return "map";
    }
}
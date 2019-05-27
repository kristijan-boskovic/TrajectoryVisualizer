package com.example.springbootoracle.controller;

import com.example.springbootoracle.entity.Trajectory;
import com.example.springbootoracle.service.TrajectoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TrajectoryController {
    @Autowired
    TrajectoryService trajectoryService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Trajectory> getAllTrajectories() {
        return trajectoryService.getAllTrajectories();
    }
//    public String getAllTrajectories(Model model) {
//        model.addAttribute("trajectories", trajectoryService.getAllTrajectories());
//        return "trajectories";
//    }

//    @RequestMapping(value = "/adduser", method = RequestMethod.POST,
//            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody()
//    public Trajectory addNewUser(@RequestBody Trajectory user) {
//        return this.userService.addUser(user);
//    }

    //other controllers omitted for brevity

}

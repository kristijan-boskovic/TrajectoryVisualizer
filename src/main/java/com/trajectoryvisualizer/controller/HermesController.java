package com.trajectoryvisualizer.controller;

import com.trajectoryvisualizer.hermes.Hermes;
import com.trajectoryvisualizer.user.Study;
import com.trajectoryvisualizer.util.Util;
import com.trajectoryvisualizer.util.traclus.Main;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

//@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@Controller
@RequestMapping("/hermes")
public class HermesController {

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Study> getAllStudiesId() {
        return Util.getAvailableStudies();
    }

    @RequestMapping(value = "/study/{id}", method = RequestMethod.GET)
    public ResponseEntity<Study> getStudy(@PathVariable("id") long id) {
        Study study = Util.getStudy(id);

        if(study == null){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(study);
        }
    }

    @RequestMapping(value = "/points/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Double[]>> getPoints(@PathVariable("id") long id) {
        Study study = Util.getStudy(id);

        if(study == null){
            return ResponseEntity.notFound().build();
        }else{
            List<Double[]> points = Util.getPoints(id);
            if(points == null) return ResponseEntity.badRequest().build();

            return ResponseEntity.ok(points);
        }
    }

    @RequestMapping(value = "/clusters/{id}", method = RequestMethod.GET)
    public ResponseEntity<Map<Integer,List<Double[]>>> getClusters(@PathVariable("id") long id) {
        Study study = Util.getStudy(id);

        if(study != null){
            Map<Integer,List<Double[]>> clusters = Util.getClusters(id);
            if(clusters == null) return ResponseEntity.badRequest().build();

            return ResponseEntity.ok(clusters);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "/process/study/{id}", method = RequestMethod.POST,
        consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody()
    public ResponseEntity<String> processStudy(@PathVariable String id) {
        try {
            Util.insertIntoTable(Long.valueOf(id));
            Hermes.loadStudy(Long.valueOf(id));
            Main.main(new String[] {id});
            return ResponseEntity.ok(id);
        } catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

}

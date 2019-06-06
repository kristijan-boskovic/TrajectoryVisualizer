package com.trajectoryvisualizer.controller;

import com.trajectoryvisualizer.entity.RawStudies;
import com.trajectoryvisualizer.entity.TraclusStudies;
import com.trajectoryvisualizer.util.Util;
import com.trajectoryvisualizer.util.traclus.Main;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.trajectoryvisualizer.controller.RawController.rawDao;

/**
 * // TO DO
 */
@Controller
@RequestMapping({"/", "/index"})
public class IndexController {

	public static Map<String, List<RawStudies>> rawMap = new HashMap<>();
	public static Map<String, List<TraclusStudies>> traclusMap = new HashMap<>();

	@RequestMapping(method = RequestMethod.GET)
	public String showHomePage() {
		return "index";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public RedirectView studyChosen(@RequestParam("studyId") String studyId) {
		try {
			rawMap.clear();
			rawMap = Util.insertIntoTable(Long.valueOf(studyId), rawDao, rawMap);

			traclusMap.clear();
			traclusMap = Main.main(Long.valueOf(studyId), traclusMap);
		} catch(Exception e){
			e.printStackTrace();
		}
		return new RedirectView("/home", true);
	}
}

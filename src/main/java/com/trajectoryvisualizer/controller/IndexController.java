package com.trajectoryvisualizer.controller;

import com.trajectoryvisualizer.hermes.Hermes;
import com.trajectoryvisualizer.util.Util;
import com.trajectoryvisualizer.util.traclus.Main;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping({"/", "/index"})
public class IndexController {

	@RequestMapping(method = RequestMethod.GET)
	public String showHomePage() {
		return "index";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public RedirectView studyChosen(@RequestParam("studyId") String studyId) {
		try {
			Util.insertIntoTable(Long.valueOf(studyId));
			Hermes.loadStudy(Long.valueOf(studyId));
			Main.main(Long.valueOf(studyId));
		} catch(Exception e){
			e.printStackTrace();
		}
		return new RedirectView("/home", true);
	}
}

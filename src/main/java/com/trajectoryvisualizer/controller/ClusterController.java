package com.trajectoryvisualizer.controller;

import com.trajectoryvisualizer.dao.ClusterDao;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Displays cluster data table.
 */
@Controller
public class ClusterController {

    public static ClusterDao clusterDao;
    public ClusterController(ClusterDao clusterDao) {
        ClusterController.clusterDao = clusterDao;
    }

    @RequestMapping(value = "/clustertable", method = RequestMethod.GET)
    public String clusterPage(HttpServletRequest request, Model model) {

        int page = 0; //default page number is 0
        int size = 10; //default page size is 10

        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page")) - 1;
        }

        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
            size = Integer.parseInt(request.getParameter("size"));
        }

        model.addAttribute("tracList", clusterDao.findAll(PageRequest.of(page, size)));
        return "clustertable";
    }
}

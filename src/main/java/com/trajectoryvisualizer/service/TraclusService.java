package com.trajectoryvisualizer.service;

import com.trajectoryvisualizer.dao.ClusterDao;
import com.trajectoryvisualizer.entity.TraclusStudies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * // TO DO
 */
@Service
public class TraclusService {

    @Autowired
    ClusterDao clusterDao;
    public List<TraclusStudies> getAllTraclusData() {
        return this.clusterDao.findAll();
    }
}
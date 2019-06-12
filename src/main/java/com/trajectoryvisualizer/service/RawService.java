package com.trajectoryvisualizer.service;

import com.trajectoryvisualizer.dao.RawDao;
import com.trajectoryvisualizer.entity.RawStudies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Gets all raw data.
 */
@Service
public class RawService {

    @Autowired
    RawDao rawDao;
    public List<RawStudies> getAllRawData() {
        return this.rawDao.findAll();
    }
}
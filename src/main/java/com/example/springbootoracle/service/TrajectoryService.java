package com.example.springbootoracle.service;

import com.example.springbootoracle.dao.TrajectoryDao;
import com.example.springbootoracle.entity.Trajectory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrajectoryService {
    @Autowired
    TrajectoryDao trajectoryDao;

    public List<Trajectory> getAllTrajectories() {
        return this.trajectoryDao.findAll();
    }

//    public Trajectory addUser(Trajectory user) {
//        return this.userDao.save(user);
//    }

    //other methods omitted for brevity
}

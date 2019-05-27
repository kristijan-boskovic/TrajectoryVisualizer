package com.example.springbootoracle.dao;

import com.example.springbootoracle.entity.Trajectory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrajectoryDao extends JpaRepository<Trajectory, Integer> {
}

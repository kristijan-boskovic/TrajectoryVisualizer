package com.trajectoryvisualizer.dao;

import com.trajectoryvisualizer.entity.RawStudies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrajectoryDao extends JpaRepository<RawStudies, Integer> {
}

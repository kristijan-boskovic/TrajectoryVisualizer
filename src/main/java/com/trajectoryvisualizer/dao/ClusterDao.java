package com.trajectoryvisualizer.dao;

import com.trajectoryvisualizer.entity.TraclusStudies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClusterDao extends JpaRepository<TraclusStudies, Integer> {
}

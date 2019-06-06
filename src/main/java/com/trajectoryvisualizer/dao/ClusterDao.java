package com.trajectoryvisualizer.dao;

import com.trajectoryvisualizer.entity.RawStudies;
import com.trajectoryvisualizer.entity.TraclusStudies;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * // TO DO
 */
@Repository
public interface ClusterDao extends JpaRepository<TraclusStudies, Integer> {

    @Query(value = "select * from traclus_studies ORDER BY clusterId", nativeQuery = true)
    Page<TraclusStudies> findAll(Pageable var1);

    @Query(value = "select * from traclus_studies ORDER BY clusterId", nativeQuery = true)
    List<RawStudies> getPoints();

    @Transactional
    @Modifying
    @Query(value = "delete from traclus_studies", nativeQuery = true)
    void deleteClusterStudy();

    @Transactional
    @Modifying
    @Query(value = "commit", nativeQuery = true)
    void commit();
}

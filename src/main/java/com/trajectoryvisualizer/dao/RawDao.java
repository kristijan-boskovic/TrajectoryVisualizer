package com.trajectoryvisualizer.dao;

import com.trajectoryvisualizer.entity.RawStudies;
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
public interface RawDao extends JpaRepository<RawStudies, Integer> {
    @Query(value = "select * from raw_studies ORDER BY trajectoryId, year, month, day, hour, minute, second", nativeQuery = true)
    Page<RawStudies> findAll(Pageable var1);

    @Query(value = "select * from raw_studies ORDER BY trajectoryId, year, month, day, hour, minute, second", nativeQuery = true)
    List<RawStudies> getPoints();

    @Transactional
    @Modifying
    @Query(value = "delete from raw_studies", nativeQuery = true)
    void deleteRawStudy();

    @Transactional
    @Modifying
    @Query(value = "commit", nativeQuery = true)
    void commit();
}

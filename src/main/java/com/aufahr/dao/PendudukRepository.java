package com.aufahr.dao;

import com.aufahr.entity.PendudukEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by AufaHR on 10/21/2017.
 */

@Transactional
public interface PendudukRepository extends JpaRepository<PendudukEntity, Long> {
    PendudukEntity findByNik(@Param("nik")String nik);

    @Query("select max(u.id) from PendudukEntity u")
    Long getMaxId();
}

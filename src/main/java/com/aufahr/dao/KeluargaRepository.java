package com.aufahr.dao;

import com.aufahr.entity.KeluargaEntity;
import com.aufahr.entity.KelurahanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

/**
 * Created by AufaHR on 10/21/2017.
 */

@Transactional
public interface KeluargaRepository extends JpaRepository<KeluargaEntity, Long> {
    KeluargaEntity findByNomorKk(String nomorKk);
}

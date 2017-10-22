package com.aufahr.dao;

import com.aufahr.entity.KecamatanEntity;
import com.aufahr.entity.KotaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

/**
 * Created by AufaHR on 10/21/2017.
 */

@Transactional
public interface KotaRepository extends JpaRepository<KotaEntity, Long> {
}

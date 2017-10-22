package com.aufahr.dao;

import com.aufahr.entity.KecamatanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by AufaHR on 10/21/2017.
 */

@Transactional
public interface KecamatanRepository extends JpaRepository<KecamatanEntity, Long> {

}

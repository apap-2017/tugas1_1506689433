package com.aufahr.dao;

import com.aufahr.entity.KelurahanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by AufaHR on 10/21/2017.
 */

@Transactional
public interface KelurahanRepository extends JpaRepository<KelurahanEntity, Long> {
}

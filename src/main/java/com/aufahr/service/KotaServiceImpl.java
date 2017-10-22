package com.aufahr.service;

import com.aufahr.dao.KotaRepository;
import com.aufahr.entity.KotaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 * Created by AufaHR on 10/21/2017.
 */
@Service
public class KotaServiceImpl extends GenericServiceImpl<KotaEntity, Long> implements KotaService {

    private KotaRepository kotaRepository;


    @Autowired
    public KotaServiceImpl(@Qualifier("kotaRepository") KotaRepository kotaRepository) {
        super((JpaRepository) kotaRepository);
        this.kotaRepository = kotaRepository;
    }
}
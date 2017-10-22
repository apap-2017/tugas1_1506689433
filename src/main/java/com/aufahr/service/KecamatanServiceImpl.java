package com.aufahr.service;

import com.aufahr.dao.KecamatanRepository;
import com.aufahr.entity.KecamatanEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 * Created by AufaHR on 10/21/2017.
 */
@Service
public class KecamatanServiceImpl extends GenericServiceImpl<KecamatanEntity, Long> implements KecamatanService {

    private KecamatanRepository kecamatanRepository;


    @Autowired
    public KecamatanServiceImpl(@Qualifier("kecamatanRepository") KecamatanRepository kecamatanRepository) {
        super((JpaRepository) kecamatanRepository);
        this.kecamatanRepository = kecamatanRepository;
    }
}
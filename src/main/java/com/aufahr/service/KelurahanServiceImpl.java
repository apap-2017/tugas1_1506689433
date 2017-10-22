package com.aufahr.service;

import com.aufahr.dao.KecamatanRepository;
import com.aufahr.dao.KelurahanRepository;
import com.aufahr.entity.KecamatanEntity;
import com.aufahr.entity.KelurahanEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 * Created by AufaHR on 10/21/2017.
 */
@Service
public class KelurahanServiceImpl extends GenericServiceImpl<KelurahanEntity, Long> implements KelurahanService {

    private KelurahanRepository kelurahanRepository;


    @Autowired
    public KelurahanServiceImpl(@Qualifier("kelurahanRepository") KelurahanRepository kelurahanRepository) {
        super((JpaRepository) kelurahanRepository);
        this.kelurahanRepository = kelurahanRepository;
    }
}
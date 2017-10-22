package com.aufahr.service;

import com.aufahr.dao.KeluargaRepository;
import com.aufahr.dao.KelurahanRepository;
import com.aufahr.entity.KeluargaEntity;
import com.aufahr.entity.KelurahanEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

/**
 * Created by AufaHR on 10/21/2017.
 */
@Service
public class KeluargaServiceImpl extends GenericServiceImpl<KeluargaEntity, Long> implements KeluargaService {

    private KeluargaRepository keluargaRepository;


    @Autowired
    public KeluargaServiceImpl(@Qualifier("keluargaRepository") KeluargaRepository keluargaRepository) {
        super((JpaRepository) keluargaRepository);
        this.keluargaRepository = keluargaRepository;
    }

    @Override
    public KeluargaEntity findByNomorKk(String nomorKk) {
        return this.keluargaRepository.findByNomorKk(nomorKk);
    }

    @Override
    public List<KeluargaEntity> findAllByCreatedAndKelurahanByIdKelurahan(Date created, KelurahanEntity kelurahanEntity) {
        return null;
    }
}
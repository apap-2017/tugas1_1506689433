package com.aufahr.service;

import com.aufahr.entity.KeluargaEntity;
import com.aufahr.entity.KelurahanEntity;

import java.sql.Date;
import java.util.List;

/**
 * Created by AufaHR on 10/21/2017.
 */
public interface KeluargaService extends GenericService<KeluargaEntity,Long> {
    KeluargaEntity findByNomorKk(String nomorKk);

    List<KeluargaEntity> findAllByCreatedAndKelurahanByIdKelurahan(Date created, KelurahanEntity kelurahanEntity);
}

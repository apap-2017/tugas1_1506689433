package com.aufahr.service;

import com.aufahr.entity.PendudukEntity;

/**
 * Created by AufaHR on 10/21/2017.
 */
public interface PendudukService extends GenericService<PendudukEntity,Long> {
    PendudukEntity findByNik(String nik);

    Long getMaxId();
}
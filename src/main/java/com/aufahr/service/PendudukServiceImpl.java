package com.aufahr.service;

import com.aufahr.dao.PendudukRepository;
import com.aufahr.entity.PendudukEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 * Created by AufaHR on 10/21/2017.
 */
@Service
public class PendudukServiceImpl extends GenericServiceImpl<PendudukEntity, Long> implements PendudukService {
    
    private PendudukRepository pendudukRepository;
    
    
    @Autowired
    public PendudukServiceImpl(@Qualifier("pendudukRepository") PendudukRepository pendudukRepository) {
        super((JpaRepository) pendudukRepository);
        this.pendudukRepository = pendudukRepository;
    }

    public PendudukEntity findByNik(String nik){
        return this.pendudukRepository.findByNik(nik);
    }

    @Override
    public Long getMaxId() {
        return this.pendudukRepository.getMaxId();
    }
}
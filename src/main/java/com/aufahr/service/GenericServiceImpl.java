package com.aufahr.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AufaHR on 10/21/2017.
 */
@Service
public abstract class GenericServiceImpl<E, K extends Serializable> implements GenericService<E, K> {

    private JpaRepository<E, K> genericDao;

    public GenericServiceImpl(JpaRepository<E,K> genericDao) {
        this.genericDao=genericDao;
    }

    public GenericServiceImpl() {
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<E> getAll() {
        return genericDao.findAll();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public E get(K id) {
        return genericDao.findOne(id);
    }

    @Override
    public void add(E entity) {
        genericDao.save(entity);
    }

    @Override
    public void update(E entity) {
        genericDao.save(entity);
    }

    @Override
    public void remove(E entity) {
        genericDao.delete(entity);
    }

    @Override
    public void remove(K id) {
        genericDao.delete(id);
    }
}
package com.aufahr.service;

import java.io.Serializable;
import java.util.List;

public interface GenericService<E, K extends Serializable> {
    public List<E> getAll();
    public E get(K id);
    public void add(E entity);
    public void update(E entity);
    public void remove(E entity);
    public void remove(K id);
}

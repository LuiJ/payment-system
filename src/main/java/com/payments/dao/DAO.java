package com.payments.dao;

import java.util.List;


public interface DAO<T> {
    
    T getById(int id);
    List<T> getAll();
    int save(T entity);
    
}

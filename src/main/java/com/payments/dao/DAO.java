package com.payments.dao;

import java.util.List;
import java.util.Properties;


public interface DAO<T> {
    
    T getById(int id);
    T getByConditionsSingleResult(Properties conditions);
    List<T> getAllByConditions(Properties conditions);
    List<T> getAll();
    int save(T entity);
    
}

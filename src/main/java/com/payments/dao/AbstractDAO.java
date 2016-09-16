package com.payments.dao;

import com.payments.entity.Identifiable;
import java.util.List;


public abstract class AbstractDAO<T extends Identifiable> implements DAO<T> {
    
    protected DAOHelper<T> helper;
    
    public AbstractDAO(Class<T> type){
        helper = new DAOHelper<>(type);
    }

    @Override
    public T getById(int id){
        T entity = helper.getById(id);
        return entity;
    }
    
    @Override
    public List<T> getAll(){
        List<T> entities = helper.getAll();
        return entities;
    }
    
    @Override
    public int save(T entity){
        int newEntityId = helper.save(entity);
        return newEntityId;
    }    
}

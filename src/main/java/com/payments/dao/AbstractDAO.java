package com.payments.dao;

import com.payments.entity.Identifiable;
import java.util.List;
import java.util.Properties;


public abstract class AbstractDAO<T extends Identifiable> implements DAO<T> {
    
    protected DAOHelper<T> helper;
    
    public AbstractDAO(Class<T> type){
        helper = new DAOHelper<>(type);
    }

    @Override
    public T getById(int id){
        String tableName = helper.getTableName();
        String query = "SELECT * FROM " + tableName + " WHERE id=" + id;
        T result = helper.executeSelectQuery(query).get(0);
        return result;
    } 
    
    @Override
    public T getByConditionsSingleResult(Properties conditions){
        T entity = null;
        List<T> entities = getAllByConditions(conditions);
        if (!entities.isEmpty()){
            entity = entities.get(0);
        }
        return entity;
    }
    
    @Override
    public List<T> getAllByConditions(Properties conditions){
        String tableName = helper.getTableName();
        String spliter = " AND ";
        String queryConditions = helper.propertiesToString(conditions, spliter);
        String query = "SELECT * FROM " + tableName + " WHERE " + queryConditions;
        List<T> result = helper.executeSelectQuery(query);
        return result;
    }
    
    @Override
    public List<T> getAll(){
        String tableName = helper.getTableName();
        String query = "SELECT * FROM " + tableName;
        List<T> result = helper.executeSelectQuery(query);
        return result;
    }
    
    @Override
    public int save(T entity) {
        int savedEntityId = 0; 
        int id = entity.getId(); 
        String saveQuery = null;       
        if (id == 0){
            saveQuery = helper.createInsertQuery(entity);
            savedEntityId = helper.executeUpdateQuery(saveQuery).get(0);
        }
        else {
            saveQuery = helper.createUpdateQuery(entity);
            helper.executeUpdateQuery(saveQuery);
            savedEntityId = entity.getId();
        }        
        return savedEntityId;
    }    
}

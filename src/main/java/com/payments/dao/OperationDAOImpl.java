package com.payments.dao;

import com.payments.entity.operation.Operation;
import java.util.List;
import java.util.Properties;


public class OperationDAOImpl implements OperationDAO {

    DAOHelper<Operation> helper;
    
    public OperationDAOImpl(){
        helper = new DAOHelper<>(Operation.class);
    }
    
    @Override
    public List<Operation> getByUserId(int userId){
        String id = String.valueOf(userId);
        Properties conditions = new Properties();
        conditions.put("user_id", id);
        List<Operation> operations = helper.getAllByConditions(conditions);
        return operations;
    }
    
    @Override
    public List<Operation> getAll(){
        List<Operation> operations = helper.getAll();
        return operations;
    }
    
}

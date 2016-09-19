package com.payments.dao;

import com.payments.entity.operation.Operation;
import java.util.List;
import java.util.Properties;


public class OperationDAO extends AbstractDAO<Operation> {
    
    public OperationDAO(){
        super(Operation.class);
    }
    
    public List<Operation> getAllByUserId(int userId){
        String id = String.valueOf(userId);
        Properties conditions = new Properties();
        conditions.put("user_id", id);
        List<Operation> operations = getAllByConditions(conditions);
        return operations;
    }
    
}

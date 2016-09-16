package com.payments.dao;

import com.payments.entity.operation.Operation;
import java.util.List;


public interface OperationDAO {
    
    List<Operation> getByUserId(int userId);
    List<Operation> getAll();
    
}

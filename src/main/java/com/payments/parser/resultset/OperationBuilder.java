package com.payments.parser.resultset;

import com.payments.entity.Identifiable;
import com.payments.entity.operation.Operation;
import com.payments.entity.operation.OperationType;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;


public class OperationBuilder implements EntityBuilder {

    @Override
    public Identifiable build(ResultSet operationResultSet) throws SQLException {
    
        Operation operation = new Operation();
        
        int id = operationResultSet.getInt(Operation.FIELD_ID);
        operation.setId(id);
        
        Date date = operationResultSet.getDate(Operation.FIELD_DATE);
        operation.setDate(date);
        
        OperationType type = OperationType.valueOf(operationResultSet.getString(Operation.FIELD_TYPE));
        operation.setType(type);
        
        BigDecimal amount = operationResultSet.getBigDecimal(Operation.FIELD_AMOUNT);
        operation.setAmount(amount);
        
        String description = operationResultSet.getString(Operation.FIELD_DESCRIPTION);
        operation.setDescription(description);
        
        int userId = operationResultSet.getInt(Operation.FIELD_USER_ID);
        operation.setUserId(userId);
        
        return operation;
    }
    
}

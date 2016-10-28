package com.payments.entity.builder;

import com.payments.dao.DAOFactory;
import com.payments.dao.UserDAO;
import com.payments.entity.Identifiable;
import com.payments.entity.Operation;
import com.payments.entity.OperationType;
import com.payments.entity.User;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;


public class OperationBuilder implements EntityBuilder {

    @Override
    public Identifiable build(ResultSet operationResultSet) throws SQLException {
    
        Operation operation = new Operation();
        
        int id = operationResultSet.getInt(Operation.FIELD_ID);
        operation.setId(id);
        
        Timestamp timestamp = operationResultSet.getTimestamp(Operation.FIELD_DATE);
        long time = timestamp.getTime();
        Date date = new Date(time);
        operation.setDate(date);
        
        OperationType type = OperationType.valueOf(operationResultSet.getString(Operation.FIELD_TYPE));
        operation.setType(type);
        
        long itemNumber = operationResultSet.getLong(Operation.FIELD_ITEM_NUMBER);
        operation.setItemNumber(itemNumber);
        
        BigDecimal amount = operationResultSet.getBigDecimal(Operation.FIELD_AMOUNT);
        operation.setAmount(amount);
        
        String description = operationResultSet.getString(Operation.FIELD_DESCRIPTION);
        operation.setDescription(description);
        
        int userId = operationResultSet.getInt(Operation.FIELD_USER_ID);
        operation.setUserId(userId);
        
        UserDAO userDAO = DAOFactory.INSTANCE.getUserDAO();
        User user = userDAO.getById(userId);
        operation.setUser(user);
        
        return operation;
    }
    
}

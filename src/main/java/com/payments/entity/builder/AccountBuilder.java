package com.payments.entity.builder;

import com.payments.dao.DAOFactory;
import com.payments.dao.UserDAO;
import com.payments.entity.Identifiable;
import com.payments.entity.Status;
import com.payments.entity.Account;
import com.payments.entity.User;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AccountBuilder implements EntityBuilder {

    @Override
    public Identifiable build(ResultSet accountResultSet) throws SQLException {
        
        Account account = new Account();
        
        Integer id = (Integer) accountResultSet.getObject(Account.FIELD_ID);
        account.setId(id);
        
        long number = accountResultSet.getLong(Account.FIELD_NUMBER);
        account.setNumber(number);
        
        Status status = Status.valueOf(accountResultSet.getString(Account.FIELD_STATUS));
        account.setStatus(status);
        
        BigDecimal amount = accountResultSet.getBigDecimal(Account.FIELD_AMOUNT);
        account.setAmount(amount);
        
        Integer userId = (Integer) accountResultSet.getObject(Account.FIELD_USER_ID);
        account.setUserId(userId);
        
        UserDAO userDAO = DAOFactory.INSTANCE.getUserDAO();
        User user = userDAO.getById(userId);
        account.setUser(user);
        
        return account;        
    }
    
}

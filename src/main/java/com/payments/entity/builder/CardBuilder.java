package com.payments.entity.builder;

import com.payments.entity.Identifiable;
import com.payments.entity.Status;
import com.payments.entity.Card;
import java.sql.ResultSet;
import java.sql.SQLException;


public class CardBuilder implements EntityBuilder {

    @Override
    public Identifiable build(ResultSet cardResultSet) throws SQLException {
    
        Card card = new Card();
        
        int id = cardResultSet.getInt(Card.FIELD_ID);
        card.setId(id);
        
        long number = cardResultSet.getLong(Card.FIELD_NUMBER);
        card.setNumber(number);
        
        String pinCode = cardResultSet.getString(Card.FIELD_PIN_CODE);
        card.setPinCode(pinCode);
        
        String salt = cardResultSet.getString(Card.FIELD_SALT);
        card.setSalt(salt);
        
        Status status = Status.valueOf(cardResultSet.getString(Card.FIELD_STATUS));
        card.setStatus(status);
        
        int accountId = cardResultSet.getInt(Card.FIELD_ACCOUNT_ID);
        card.setAccountId(accountId);
        
        return card;
    }
    
}

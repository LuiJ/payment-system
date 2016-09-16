package com.payments.dao;

import com.payments.entity.card.Card;
import java.util.List;


public interface CardDAO {

    Card getByNumber(long number);
    List<Card> getByAccountId(int accountId);
    List<Card> getAll();
    
}

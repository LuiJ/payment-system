package com.payments.logic;

import com.payments.dao.CardDAO;
import com.payments.dao.DAOFactory;
import com.payments.entity.Card;
import com.payments.entity.Status;


public class CardService {

    private final CardDAO cardDAO;
    
    public CardService(){
        cardDAO = DAOFactory.INSTANCE.getCardDAO();
    }
    
    public void block(int cardId){ 
        Card card = cardDAO.getById(cardId);
        card.setStatus(Status.BLOCKED);
        cardDAO.save(card);
    }
    
    public void activate(int cardId){
        Card card = cardDAO.getById(cardId);
        card.setStatus(Status.ACTIVE);
        cardDAO.save(card);
    }    
}

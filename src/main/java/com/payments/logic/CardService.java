package com.payments.logic;

import com.payments.dao.CardDAO;
import com.payments.dao.DAOFactory;
import com.payments.entity.Card;
import com.payments.entity.Status;
import javax.servlet.http.HttpServletRequest;


public class CardService {

    private final CardDAO cardDAO;
    
    public CardService(){
        cardDAO = DAOFactory.INSTANCE.getCardDAO();
    }
    
    public void block(HttpServletRequest request){        
        int cardId = getCardId(request);
        Card card = cardDAO.getById(cardId);
        card.setStatus(Status.BLOCKED);
        cardDAO.save(card);
    }
    
    public void activate(HttpServletRequest request){
        int cardId = getCardId(request);
        Card card = cardDAO.getById(cardId);
        card.setStatus(Status.ACTIVE);
        cardDAO.save(card);
    }
    
    private int getCardId(HttpServletRequest request){
        String cardIdParameter = request.getParameter(Parameter.CARD_ID.getParameter());
        int cardId = Integer.parseInt(cardIdParameter); 
        return cardId;
    }
    
}

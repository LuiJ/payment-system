package com.payments.web.controller;

import com.payments.dao.AccountDAO;
import com.payments.dao.CardDAO;
import com.payments.dao.DAOFactory;
import com.payments.entity.account.Account;
import com.payments.entity.card.Card;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller 
@RequestMapping("/accounts")
public class AccountController {

    
    @RequestMapping(method = RequestMethod.GET)
    public String getAccounts(Model model)
    {
        AccountDAO accountDAO = DAOFactory.INSTANCE.getAccountDAO();
        CardDAO cardDAO = DAOFactory.INSTANCE.getCardDAO();
        List<Account> accounts = accountDAO.getAll();
        for (Account account : accounts){
            int accountId = account.getId();
            List<Card> cards = cardDAO.getAllByAccountId(accountId);
            account.setCards(cards);
        }
        model.addAttribute("accounts", accounts);
        return "accounts";
    }
    
}

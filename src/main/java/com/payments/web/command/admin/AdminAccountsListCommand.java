package com.payments.web.command.admin;

import com.payments.dao.AccountDAO;
import com.payments.dao.DAOFactory;
import com.payments.entity.Account;
import com.payments.web.command.AbstractCommand;
import com.payments.web.view.Attribute;
import com.payments.web.view.View;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AdminAccountsListCommand extends AbstractCommand {

    private static final String PAGE = "accounts";
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException
    {
        AccountDAO accountDAO = DAOFactory.INSTANCE.getAccountDAO();
        List<Account> accounts = accountDAO.getAllWithCards(); 
        
        request.setAttribute(Attribute.PAGE.getName(), PAGE);
        request.setAttribute(Attribute.ACCOUNTS.getName(), accounts);        
        render(request, response, View.ADMIN_ACCOUNTS);
    }
    
}

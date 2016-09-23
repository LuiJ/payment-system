package com.payments.web.command;

import com.payments.checker.PasswordChecker;
import com.payments.dao.AdminDAO;
import com.payments.dao.DAOFactory;
import com.payments.entity.admin.Admin;
import com.payments.exception.AccountNotFoundException;
import com.payments.exception.AuthentificationException;
import com.payments.exception.PaymentsException;
import com.payments.web.view.Attribute;
import com.payments.web.view.View;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AdminLoginCommand extends AbstractCommand {
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException, PaymentsException
    {
        
        AdminDAO adminDAO = DAOFactory.INSTANCE.getAdminDAO();
        
        String login = request.getParameter(Attribute.LOGIN);
        Admin admin = adminDAO.getByLogin(login);
        
        if (admin == null){
            throw new AccountNotFoundException(View.ADMIN_LOGIN);
        }        
                    
        String password = request.getParameter(Attribute.PASSWORD);
        PasswordChecker passwordChecker = new PasswordChecker();
        boolean correct = passwordChecker.check(admin, password);
        if (correct){
            AccountsListCommand command = new AccountsListCommand();
            command.execute(request, response);
        }
        else {
            throw new AuthentificationException(View.ADMIN_LOGIN);
        }
    }   
}

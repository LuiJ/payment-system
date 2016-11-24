package com.payments.web.command.user;

import com.payments.dao.DAOFactory;
import com.payments.dao.OperationDAO;
import com.payments.entity.Operation;
import com.payments.entity.User;
import com.payments.web.command.AbstractServletCommand;
import com.payments.web.view.Attribute;
import com.payments.web.view.View;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class UserOperationsListCommand extends AbstractServletCommand {
    
    private static final String PAGE = "operations";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException
    {       
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Attribute.LOGGED_USER.getName());
        int userId = user.getId();
        
        OperationDAO operationDAO = DAOFactory.INSTANCE.getOperationDAO();
        List<Operation> operations = operationDAO.getAllByUserId(userId);
        
        request.setAttribute(Attribute.PAGE.getName(), PAGE);
        request.setAttribute(Attribute.OPERATIONS.getName(), operations);        
        render(request, response, View.USER_OPERATIONS);
    }    
}

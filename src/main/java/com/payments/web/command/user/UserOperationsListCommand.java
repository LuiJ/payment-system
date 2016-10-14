package com.payments.web.command.user;

import com.payments.dao.DAOFactory;
import com.payments.dao.OperationDAO;
import com.payments.entity.Operation;
import com.payments.entity.User;
import com.payments.web.command.AbstractCommand;
import com.payments.web.view.Attribute;
import com.payments.web.view.View;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class UserOperationsListCommand extends AbstractCommand {
    
    private static final String PAGE = "operations";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException
    {       
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Attribute.LOGGED_USER);
        int userId = user.getId();
        
        OperationDAO operationDAO = DAOFactory.INSTANCE.getOperationDAO();
        List<Operation> operations = operationDAO.getAllByUserId(userId);
        
        request.setAttribute(Attribute.PAGE, PAGE);
        request.setAttribute(Attribute.OPERATIONS, operations);        
        render(request, response, View.USER_OPERATIONS);
    }    
}

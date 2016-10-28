package com.payments.web.command.admin;

import com.payments.dao.DAOFactory;
import com.payments.dao.OperationDAO;
import com.payments.entity.Operation;
import com.payments.web.command.AbstractCommand;
import com.payments.web.view.Attribute;
import com.payments.web.view.View;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AdminOperationsListCommand extends AbstractCommand {
    
    private static final String PAGE = "operations";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException
    {           
        OperationDAO operationDAO = DAOFactory.INSTANCE.getOperationDAO();
        List<Operation> operations = operationDAO.getAll();
        
        request.setAttribute(Attribute.PAGE, PAGE);
        request.setAttribute(Attribute.OPERATIONS, operations);        
        render(request, response, View.ADMIN_OPERATIONS);
    }    
}
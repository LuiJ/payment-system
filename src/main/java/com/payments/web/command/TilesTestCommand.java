package com.payments.web.command;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.tiles.TilesContainer;
import org.apache.tiles.access.TilesAccess;


public class TilesTestCommand extends AbstractCommand {
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        ServletContext context = session.getServletContext();
        TilesContainer container = TilesAccess.getContainer(context);
        container.render("user_accounts", request, response);
    }
    
}

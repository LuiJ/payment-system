package com.payments.web.view;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.tiles.TilesContainer;
import org.apache.tiles.access.TilesAccess;


public class Renderer {
    
    public void render(HttpServletRequest request, HttpServletResponse response, View view) 
            throws ServletException, IOException
    {
        String viewName = view.getViewName();
        HttpSession session = request.getSession();
        ServletContext context = session.getServletContext();
        TilesContainer container = TilesAccess.getContainer(context);
        container.render(viewName, request, response);
    }    
}

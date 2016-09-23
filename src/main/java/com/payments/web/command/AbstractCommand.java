package com.payments.web.command;

import com.payments.exception.PaymentsException;
import com.payments.web.view.Renderer;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public abstract class AbstractCommand implements Command {
    
    private final Renderer renderer;
    
    public AbstractCommand(){
        renderer = new Renderer();
    }
    
    @Override
    public abstract void execute(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException, PaymentsException;
    
    
    protected void render(HttpServletRequest request, HttpServletResponse response, String viewName) 
            throws ServletException, IOException
    {
        renderer.render(request, response, viewName);
    }
    
}

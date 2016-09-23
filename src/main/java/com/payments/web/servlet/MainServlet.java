package com.payments.web.servlet;

import com.payments.exception.AccountNotFoundException;
import com.payments.exception.AuthentificationException;
import com.payments.exception.PaymentsException;
import com.payments.web.command.Command;
import com.payments.web.command.CommandFactory;
import com.payments.web.view.Attribute;
import com.payments.web.view.Renderer;
import com.payments.web.view.View;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class MainServlet extends HttpServlet {
    
    private final Renderer renderer;    
    
    public MainServlet(){
        renderer = new Renderer();
    }
    
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException 
    {     
        try {
            Command command = CommandFactory.create(request);
            command.execute(request, response); 
        }
        catch (AccountNotFoundException e){
            View view = e.getViewToRender();
            request.setAttribute(Attribute.ERROR_MESSAGE, "Incorrect login");
            renderer.render(request, response, view);            
        }   
        catch (AuthentificationException e){            
            View view = e.getViewToRender();
            request.setAttribute(Attribute.ERROR_MESSAGE, "Incorrect password");
            renderer.render(request, response, view);   
        }
        catch (PaymentsException e){
            throw new IllegalStateException();
        }
    }
    
}

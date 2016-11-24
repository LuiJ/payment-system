package com.payments.web.command;

import com.payments.exception.PaymentsException;
import com.payments.web.internationalization.InternationalizationHelper;
import com.payments.web.internationalization.TextPropertiesEnum;
import com.payments.web.view.Renderer;
import com.payments.web.view.View;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public abstract class AbstractServletCommand implements Command {
    
    private final Renderer renderer;
    private InternationalizationHelper internationalizationHelper;
    
    public AbstractServletCommand(){
        renderer = new Renderer();
    }
    
    @Override
    public abstract void execute(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException, PaymentsException;

    public void setInternationalizationHelper(InternationalizationHelper internationalizationHelper) {
        this.internationalizationHelper = internationalizationHelper;
    }
            
    protected void render(HttpServletRequest request, HttpServletResponse response, View view) 
            throws ServletException, IOException
    {
        renderer.render(request, response, view);
    }
    
    protected String getLocalizedText(TextPropertiesEnum textProperty){
        String localizedText = internationalizationHelper.getTextByTextProperty(textProperty);
        return localizedText;
    }
    
}

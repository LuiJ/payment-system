package com.payments.web.internationalization;

import java.util.Locale;
import java.util.ResourceBundle;


public class InternationalizationHelper {
    
    public static final Locale DEFAULT_LOCALE = new Locale("en");
    public static final String BUNDLE_BASE_NAME = "i18n";
    
    private final Locale locale;
    
    public InternationalizationHelper(Locale locale){
        this.locale = locale;
    }    
    
    public String getTextByTextProperty(TextPropertiesEnum textProperty)
    {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(BUNDLE_BASE_NAME, locale);        
        String text = resourceBundle.getString(textProperty.getName()); 
        return text;
    }    
}

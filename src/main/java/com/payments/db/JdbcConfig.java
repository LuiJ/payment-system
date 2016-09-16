package com.payments.db;

import java.util.ResourceBundle;


public enum JdbcConfig {
    
    INSTANCE;
    
    private static final String CONFIGURATION_FILE_NAME = "jdbc";
    private static final String DRIVER = "driver";
    private static final String DB_URL = "db-url";
    private static final String USER = "user";
    private static final String PASSWORD = "password";
    
    private String driver;
    private String dbUrl;    
    private String user;
    private String password;
    
    JdbcConfig(){
        ResourceBundle config = ResourceBundle.getBundle(CONFIGURATION_FILE_NAME);
        driver = config.getString(DRIVER);
        dbUrl = config.getString(DB_URL);
        user = config.getString(USER);
        password = config.getString(PASSWORD);
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
        
}

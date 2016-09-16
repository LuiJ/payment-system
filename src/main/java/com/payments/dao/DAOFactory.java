package com.payments.dao;


public enum DAOFactory {
    
    INSTANCE;
    
    private AccountDAO accountDAO;  
    private AdminDAO adminDAO;
    private CardDAO cardDAO;
    private OperationDAO operationDAO;
    private UserDAO userDAO;

    public AccountDAO getAccountDAO(){
        if (accountDAO == null){
            accountDAO = new AccountDAO();
        }
        return accountDAO;
    }   

    public AdminDAO getAdminDAO() {
        if (adminDAO == null){
            adminDAO = new AdminDAO();
        }
        return adminDAO;
    }

    public CardDAO getCardDAO() {
        if (cardDAO == null){
            cardDAO = new CardDAO();
        }
        return cardDAO;
    }

    public OperationDAO getOperationDAO() {
        if (operationDAO == null){
            operationDAO = new OperationDAO();
        }
        return operationDAO;
    }

    public UserDAO getUserDAO() {
        if (userDAO == null){
            userDAO = new UserDAO();
        }
        return userDAO;
    }
            
}

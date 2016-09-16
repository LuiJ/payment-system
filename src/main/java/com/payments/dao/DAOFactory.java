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
            accountDAO = new AccountDAOImpl();
        }
        return accountDAO;
    }   

    public AdminDAO getAdminDAO() {
        if (adminDAO == null){
            adminDAO = new AdminDAOImpl();
        }
        return adminDAO;
    }

    public CardDAO getCardDAO() {
        if (cardDAO == null){
            cardDAO = new CardDAOImpl();
        }
        return cardDAO;
    }

    public OperationDAO getOperationDAO() {
        if (operationDAO == null){
            operationDAO = new OperationDAOImpl();
        }
        return operationDAO;
    }

    public UserDAO getUserDAO() {
        if (userDAO == null){
            userDAO = new UserDAOImpl();
        }
        return userDAO;
    }
            
}

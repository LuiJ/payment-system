package com.payments.logic;

import com.payments.dao.AccountDAO;
import com.payments.dao.CardDAO;
import com.payments.dao.DAOFactory;
import com.payments.entity.Account;
import com.payments.entity.Card;
import com.payments.entity.Payment;
import com.payments.entity.Status;
import com.payments.exception.IncorrectPaymentDataException;
import com.payments.exception.PaymentAmountException;
import com.payments.exception.PaymentsException;
import com.payments.web.view.View;
import java.math.BigDecimal;


public class PaymentService {
    
    private final AccountDAO accountDAO;
    
    public PaymentService(){
        accountDAO = DAOFactory.INSTANCE.getAccountDAO();
    }

    
    public Payment makePayment(int payerAccountId, long targetNumber, BigDecimal paymentAmount)
                   throws PaymentsException
    {
        Account payerAccount = accountDAO.getById(payerAccountId);        
        checkPaymentData(payerAccount, targetNumber, paymentAmount);
        
        Payment payment = new Payment();
        payment.setPayerAccount(payerAccount);        
        Account targetAccount = getAccountByTargetNumber(targetNumber);
        payment.setTargetAccount(targetAccount);        
        payment.setAmount(paymentAmount);   
        
        doMoneyTransfer(payerAccount, targetAccount, paymentAmount);
        
        return payment;
    }
    
    
    private void checkPaymentData(Account payerAccount, long targetNumber, BigDecimal paymentAmount)
                   throws PaymentsException
    {
        if (payerAccount == null || paymentAmount == BigDecimal.ZERO){
            throw new IncorrectPaymentDataException(View.USER_PAYMENT_ERROR);
        }
        
        String numberString = String.valueOf(targetNumber);
        int numberLength = numberString.length();
        if (numberLength != 16){
            throw new IncorrectPaymentDataException(View.USER_PAYMENT_ERROR);
        }
        
        Account targetAccount = getAccountByTargetNumber(targetNumber);
        if (targetAccount == null){
            throw new IncorrectPaymentDataException(View.USER_PAYMENT_ERROR);
        }
        
        int targetAccountId = targetAccount.getId();
        int payerAccountId = payerAccount.getId();
        if (targetAccountId == payerAccountId){
            throw new IncorrectPaymentDataException(View.USER_PAYMENT_ERROR);
        }
        
        Status targetAccountStatus = targetAccount.getStatus();
        if (targetAccountStatus != Status.ACTIVE){
            throw new IncorrectPaymentDataException(View.USER_PAYMENT_ERROR);
        }
        
        BigDecimal availableAmount = payerAccount.getAmount();
        if (availableAmount.compareTo(paymentAmount) == -1){
            throw new PaymentAmountException(View.USER_PAYMENT_ERROR);
        }        
    }  
    
    
    private Account getAccountByTargetNumber(long targetNumber){
        Account account = accountDAO.getByNumber(targetNumber);
        if (account == null){
            CardDAO cardDAO = DAOFactory.INSTANCE.getCardDAO();
            Card card = cardDAO.getByNumber(targetNumber);
            if (card != null){
                account = card.getAccount();
            }            
        }
        return account;
    }
    
    
    private void doMoneyTransfer(Account payerAccount, 
                                 Account targetAccount, 
                                 BigDecimal paymentAmount)
    {
        BigDecimal payerAmount = payerAccount.getAmount();
        BigDecimal resultPayerAmount = payerAmount.subtract(paymentAmount);
        payerAccount.setAmount(resultPayerAmount);
        accountDAO.save(payerAccount);
        
        BigDecimal targetAmount = targetAccount.getAmount();
        BigDecimal resultTargetAmount = targetAmount.add(paymentAmount);
        targetAccount.setAmount(resultTargetAmount);
        accountDAO.save(targetAccount);
    }
}

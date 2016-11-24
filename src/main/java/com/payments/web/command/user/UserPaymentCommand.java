package com.payments.web.command.user;

import com.payments.dao.CardDAO;
import com.payments.dao.DAOFactory;
import com.payments.entity.Card;
import com.payments.entity.OperationType;
import com.payments.entity.Payment;
import com.payments.entity.User;
import com.payments.exception.PaymentsException;
import com.payments.logic.OperationService;
import com.payments.logic.PaymentService;
import com.payments.web.command.AbstractCommand;
import com.payments.web.command.RequestParameter;
import com.payments.web.internationalization.InternationalizationHelper;
import com.payments.web.internationalization.TextPropertiesEnum;
import com.payments.web.view.Attribute;
import com.payments.web.view.View;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class UserPaymentCommand extends AbstractCommand {
    
    private static final String PAGE = "payment";
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException, PaymentsException
    {    
        String accountIdParameter = request.getParameter(RequestParameter.ACCOUNT_ID.getParameter());
        int payerAccountId = Integer.parseInt(accountIdParameter); 
        
        String operationTypeParameter = request.getParameter(RequestParameter.OPERATION_TYPE.getParameter());
        OperationType operationType = OperationType.valueOf(operationTypeParameter);
        
        String numberParameter = request.getParameter(RequestParameter.NUMBER.getParameter());
        String trimmedNumber = numberParameter.replaceAll("\\s+","");
        long targetNumber = Long.parseLong(trimmedNumber);
        
        String amountParameter = request.getParameter(RequestParameter.AMOUNT.getParameter());
        double amountDouble = Double.parseDouble(amountParameter);
        BigDecimal paymentAmount = BigDecimal.valueOf(amountDouble);
        
        PaymentService paymentService = new PaymentService();
        Payment payment = paymentService.makePayment(payerAccountId, targetNumber, 
                                                     paymentAmount, operationType);
        
        OperationService operationService = new OperationService();
        operationService.savePaymentOperation(payment, operationType);
        
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Attribute.LOGGED_USER.getName());
        int userId = user.getId();
                
        CardDAO cardDAO = DAOFactory.INSTANCE.getCardDAO();        
        List<Card> cards = cardDAO.getAllActiveByUserId(userId); 
        
        request.setAttribute(Attribute.PAGE.getName(), PAGE);        
        request.setAttribute(Attribute.CARDS.getName(), cards);
        
        Locale locale = (Locale) session.getAttribute(Attribute.LOCALE.getName());
        InternationalizationHelper i18nHelper = new InternationalizationHelper(locale);
        String successMessage = i18nHelper.getTextByTextProperty(TextPropertiesEnum.OPERATION_SUCCESS);
        request.setAttribute(Attribute.SUCCESS_MESSAGE.getName(), successMessage);
        
        render(request, response, View.USER_PAYMENT);
    }    
}

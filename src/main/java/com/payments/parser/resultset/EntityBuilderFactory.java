package com.payments.parser.resultset;

import com.payments.entity.account.Account;
import com.payments.entity.admin.Admin;
import com.payments.entity.card.Card;
import com.payments.entity.operation.Operation;
import com.payments.entity.user.User;


public class EntityBuilderFactory {
    
    public static EntityBuilder create(Class type){
        
            EntityBuilder builder = null;
            
            if (type == Account.class){
                builder = new AccountBuilder();
            }
            else if (type == Admin.class){
                builder = new AdminBuilder();
            }
            else if (type == Card.class){
                builder = new CardBuilder();
            }
            else if (type == Operation.class){
                builder = new OperationBuilder();
            }
            else if (type == User.class){
                builder = new UserBuilder();
            }
            else {
                throw new IllegalArgumentException("Incorrect entity type.");
            }

            return builder;        
    }     
}

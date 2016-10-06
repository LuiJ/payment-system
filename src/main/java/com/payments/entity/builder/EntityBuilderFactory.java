package com.payments.entity.builder;

import com.payments.entity.Account;
import com.payments.entity.Admin;
import com.payments.entity.Card;
import com.payments.entity.Operation;
import com.payments.entity.Role;
import com.payments.entity.User;


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
            else if (type == Role.class){
                builder = new RoleBuilder();
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

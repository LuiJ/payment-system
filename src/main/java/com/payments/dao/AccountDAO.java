package com.payments.dao;

import com.payments.entity.account.Account;
import java.util.List;


public interface AccountDAO {
    
    Account getById(int id);
    List<Account> getByUserId(int userId);
    List<Account> getAll();
    
}

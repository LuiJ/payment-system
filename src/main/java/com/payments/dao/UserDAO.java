package com.payments.dao;

import com.payments.entity.user.User;
import java.util.List;


public interface UserDAO {

    User getByLogin(String login);
    List<User> getAll();
    
}

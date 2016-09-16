package com.payments.dao;

import com.payments.entity.admin.Admin;


public interface AdminDAO {

    Admin getByLogin(String login);
    
}

package com.payments.entity.builder;

import com.payments.entity.Identifiable;
import java.sql.ResultSet;
import java.sql.SQLException;


public interface EntityBuilder {

    Identifiable build(ResultSet resultSet) throws SQLException;
    
}

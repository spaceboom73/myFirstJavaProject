package com.apple.java.myproject.databases.facadedatabase;

import com.apple.java.myproject.exception.DatabaseException;

import java.sql.Connection;

public interface DatabaseFacade {
    void connect (String login, String pass) throws DatabaseException;
    Connection getConnection();
    void disconnect();
}

package com.apple.java.myproject.databases.facadedatabase;

import com.apple.java.myproject.exception.DatabaseException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class PostgresDatabaseFacade implements DatabaseFacade{

    private String className = "org.postgresql.Driver";
    private String url = "jdbc:postgresql://192.168.56.101:5432/postgres";
    private Connection connection = null;

    @Override
    public void connect(String login, String password) throws DatabaseException {
        try {
            Class.forName(this.className);
            this.connection = DriverManager.getConnection(this.url, login, password);
        } catch (ClassNotFoundException cnfe) {
            throw new DatabaseException("No driver for PostgreSQL found");
        } catch (SQLException sqle) {
            throw new DatabaseException("Unable to connect to PostgreSQL with the given login and password");
        }
    }

    @Override
    public Connection getConnection() {
        return this.connection;
    }

    @Override
    public void disconnect() {
        if(this.connection != null) {
            try {
                this.connection.close();
            } catch (SQLException sqle) {
                this.connection = null;
                sqle.printStackTrace();
            }
        }
    }
}

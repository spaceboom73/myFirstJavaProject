package com.apple.java.myproject.databases.userfacade;

import com.apple.java.myproject.Users;
import com.apple.java.myproject.databases.facadedatabase.DatabaseFacade;
import com.apple.java.myproject.databases.facadedatabase.PostgresDatabaseFacade;
import com.apple.java.myproject.exception.DatabaseException;
import com.apple.java.myproject.exception.UsersBaseException;
import com.apple.java.myproject.utils.enums.rangUser;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsersDatabase implements UsersDatabaseFacade {

    private DatabaseFacade dbFacade = new PostgresDatabaseFacade();

    private static final String login = "student";
    private static final String pass = "student";
    private static final String tableName = "users";

    private static final String idField = "id";
    private static final String nameField = "name";
    private static final String rangField = "rang";
    private static final String summRecomendationsField = "summ_recomendations";
    private static final String registrationDateField = "registrationdate";

    public UsersDatabase() {

    }
    public void createUser(Users user) throws UsersBaseException {
        try {
            this.dbFacade.connect(this.login, this.pass);
            Connection connection = this.dbFacade.getConnection();
            if (connection != null) {
                Statement statement = connection.createStatement();
                String sql = "insert into " + tableName + "(" +
                        idField + "," + nameField + "," + rangField + "," + registrationDateField + "," + summRecomendationsField
                        + ") VALUES ";
                sql += "('" + user.getId() + "'," +
                        "'" + user.getUserName() + "'," +
                        "'" + user.getRangUser() + "'," +
                        "'" + user.registrationDate + "'," +
                        "'" + user.getSummRatings() + "')";
                statement.executeUpdate(sql);
                System.out.println("[ADD USER]: Новый пользователь успешно добавлен");
                //System.out.println(sql);
            }
        } catch (DatabaseException ex) {
            ex.printStackTrace();
        }
        catch (SQLException ex){
            ex.printStackTrace();//throw new UsersBaseException("Ошибка в работе с базой");
        }
        finally {
            dbFacade.disconnect();
        }
    }
    public Users selectUser(Integer id) throws UsersBaseException {
        Users currentUser = new Users();
        try {
            this.dbFacade.connect(this.login, this.pass);
            Connection connection = this.dbFacade.getConnection();
            if (connection != null) {
                Statement statement = connection.createStatement();
                String query = "select * from " + this.tableName + " where " + idField + " = " + id;
                ResultSet result = statement.executeQuery(query);
                while (result.next()) {
                    currentUser.setId(result.getInt(this.idField));
                    currentUser.setSummRatings(result.getInt(this.summRecomendationsField));
                    currentUser.registrationDate = result.getString(this.registrationDateField);
                    currentUser.setUserName(result.getString(this.nameField));
                    currentUser.setRangUser(rangUser.getChoice(result.getString(this.rangField)));
                    System.out.println("[SELECT USER BY ID]: Пользователь успешно загружен");
                }
            }
        } catch (DatabaseException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            throw new UsersBaseException("Произошла ошибка в работе с БД");
        } finally {
            this.dbFacade.disconnect();
        }
        return currentUser;
    }
    public void updateUser(Users user) throws UsersBaseException {
        try {
            this.dbFacade.connect(this.login, this.pass);
            Connection connection = this.dbFacade.getConnection();
            if (connection != null) {
                Statement statement = connection.createStatement();
                String sql = "update " + tableName + " set " + idField + " = '" + user.getId() + "', " +
                        nameField + " = '" + user.getUserName() + "', " +
                        rangField + " = '" + user.getRangUser() + "', " +
                        summRecomendationsField + " = '" + user.getSummRatings() + "', " +
                        registrationDateField + " = '" + user.registrationDate + "' where " + idField + " = " + user.getId();
                statement.executeUpdate(sql);
                System.out.println("[UPDATE USER]: Обновление выполнено успешно");
            }
        } catch (DatabaseException ex) {
            ex.printStackTrace();
        }
        catch (SQLException ex){
            throw new UsersBaseException("Ошибка в работе с БД");
        }
        finally {
            dbFacade.disconnect();
        }
    }
    public void deleteUser(Integer id) throws  UsersBaseException{
        try {
            this.dbFacade.connect(this.login, this.pass);
            Connection connection = this.dbFacade.getConnection();
            if (connection != null) {
                Statement statement = connection.createStatement();
                String sql = "delete from " + tableName + " where " + idField + " = " + id;
                statement.executeUpdate(sql);
                System.out.println("[DELETE USER]: Удаление выполнено успешно");
            }
        } catch (DatabaseException ex) {
            ex.printStackTrace();
        }
        catch (SQLException ex){
            throw new UsersBaseException("Ошибка в работе с БД");
        }
        finally {
            dbFacade.disconnect();
        }
    }
    public boolean checkUser(Integer id) throws UsersBaseException{
        boolean hasUser = true;
        try {
            this.dbFacade.connect(this.login, this.pass);
            Connection connection = this.dbFacade.getConnection();
            if (connection != null) {
                Statement statement = connection.createStatement();
                String sql = "select * from " + tableName + " where id = '" + id + "'";
                ResultSet result = statement.executeQuery(sql);
                hasUser = false;
                while (result.next()) {
                    Users user = new Users();
                    user.setId(result.getInt(this.idField));
                    if (user.getId().equals(id)) hasUser = true;
                }
            }
        }
        catch (DatabaseException ex){
            ex.printStackTrace();
        }
        catch (SQLException ex){
            throw new UsersBaseException("Ошибка в работе с БД");
        }
        finally {
            this.dbFacade.disconnect();
        }
        return hasUser;
    }
}

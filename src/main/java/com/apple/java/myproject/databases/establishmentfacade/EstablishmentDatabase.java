package com.apple.java.myproject.databases.establishmentfacade;

import com.apple.java.myproject.Establishments;
import com.apple.java.myproject.databases.facadedatabase.DatabaseFacade;
import com.apple.java.myproject.databases.facadedatabase.PostgresDatabaseFacade;
import com.apple.java.myproject.exception.DatabaseException;
import com.apple.java.myproject.exception.EstablishmentBaseException;
import com.apple.java.myproject.textprocessing.EstablishmentBase;
import com.apple.java.myproject.utils.enums.ratingSelection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EstablishmentDatabase implements EstablishmentDatabaseFacade{

    private DatabaseFacade dbFacade = new PostgresDatabaseFacade();

    private static final String login = "student";
    private static final String pass = "student";
    private static final String tableName = "establishments";
    private static final String tableNameChecks = "establishment_checks";
    private static final String tableNameRatings = "establishment_ratings";

    private static final String idField = "id";
    private static final String nameField = "name";
    private static final String summField = "summ";
    private static final String ratingField = "rating";
    private static final String registrationDateField = "registrationdate";

    public EstablishmentDatabase() {

    }
    public void createEstablishment(Establishments establishment) throws EstablishmentBaseException {
        try {
            this.dbFacade.connect(this.login, this.pass);
            Connection connection = this.dbFacade.getConnection();
            if(connection != null){
                Statement statement = connection.createStatement();
                String sql = "insert into " + tableName + "(" + idField + "," + nameField + ") VALUES ('" +
                        establishment.getId() + "','" + establishment.getName() + "')";
                System.out.println(sql);
                statement.executeUpdate(sql);
                statement.executeUpdate(establishment.toBaseCheck());
                statement.executeUpdate(establishment.toBaseRating());
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
    public boolean checkEstablishment(Integer id) throws EstablishmentBaseException{
        boolean hasEstabl = false;
        try {
            this.dbFacade.connect(this.login, this.pass);
            Connection connection = this.dbFacade.getConnection();
            if (connection != null) {
                Statement statement = connection.createStatement();
                String sql = "select * from " + tableName + " where id = '" + id + "'";
                ResultSet result = statement.executeQuery(sql);
                while (result.next()) {
                    Establishments establishment = new Establishments();
                    establishment.setId(result.getInt(this.idField));
                    if (establishment.getId().equals(id)) hasEstabl = true;
                }
            }
        }
        catch (DatabaseException ex){
            ex.printStackTrace();
        }
        catch (SQLException ex){
            throw new EstablishmentBaseException("Ошибка в работе с БД");
        }
        finally {
            this.dbFacade.disconnect();
        }
        return hasEstabl;

    }
    public void updateEstablishment(Establishments establishment) throws EstablishmentBaseException{
        try {
            this.dbFacade.connect(this.login, this.pass);
            Connection connection = this.dbFacade.getConnection();
            if (connection != null) {
                Statement statement = connection.createStatement();
                String sql = "delete from " + tableNameChecks + " where " + idField + " = '" + establishment.getId() + "'";
                statement.executeUpdate(sql);
                sql = "delete from " + tableNameRatings + " where " + idField + " = '" + establishment.getId() + "'";
                statement.executeUpdate(sql);
                sql = "update " + tableName + " set " + idField + " = '" + establishment.getId() + "'," +
                        nameField + " = '" + establishment.getName() + "' where " + idField + " = '" + establishment.getId() + "'";
                statement.executeUpdate(sql);
                statement.executeUpdate(establishment.toBaseRating());
                statement.executeUpdate(establishment.toBaseCheck());
                System.out.println("[UPDATE USER]: Обновление выполнено успешно");
            }
        } catch (DatabaseException ex) {
            ex.printStackTrace();
        }
        catch (SQLException ex){
            throw new EstablishmentBaseException("Ошибка в работе с БД");
        }
        finally {
            dbFacade.disconnect();
        }
    }
    public Establishments selectEstabl(Integer id) throws EstablishmentBaseException {
        Establishments currentEstablishment = new Establishments();
        try {
            this.dbFacade.connect(this.login, this.pass);
            Connection connection = this.dbFacade.getConnection();
            if (connection != null) {
                Statement statement = connection.createStatement();
                String query = "select * from " + this.tableName + " where " + idField + " = " + id;
                ResultSet result = statement.executeQuery(query);
                while (result.next()) {
                    currentEstablishment.setId(result.getInt(this.idField));
                    currentEstablishment.setName(result.getString(this.nameField));
                }
                query = "select * from " + this.tableNameRatings + " where " + idField + " = '" + id + "'";
                result = statement.executeQuery(query);
                while (result.next()) {
                    currentEstablishment.setReiting(ratingSelection.getChoice(result.getInt(this.ratingField)));
                }
                query = "select * from " + this.tableNameChecks + " where " + idField + " = '" + id + "'";
                result = statement.executeQuery(query);
                while (result.next()) {
                    currentEstablishment.setAverageSumm(result.getInt(this.summField));
                }
                System.out.println("[SHOW ESTABLISHMENT INFO]: Заведение успешно загружено");
            }
        } catch (DatabaseException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            throw new EstablishmentBaseException("Произошла ошибка в работе с БД");
        } finally {
            this.dbFacade.disconnect();
        }
        return currentEstablishment;
    }

    public void deleteEstabl(Integer id) throws  EstablishmentBaseException{
        try {
            this.dbFacade.connect(this.login, this.pass);
            Connection connection = this.dbFacade.getConnection();
            if (connection != null) {
                Statement statement = connection.createStatement();
                String sql = "delete from " + tableName + " where " + idField + " = " + id;
                statement.executeUpdate(sql);
                sql = "delete from " + tableNameChecks + " where " + idField + " = " + id;
                statement.executeUpdate(sql);
                sql = "delete from " + tableNameRatings + " where " + idField + " = " + id;
                statement.executeUpdate(sql);
                System.out.println("[DELETE ESTABLISHMENT]: Удаление выполнено успешно");
            }
        } catch (DatabaseException ex) {
            ex.printStackTrace();
        }
        catch (SQLException ex){
            throw new EstablishmentBaseException("Ошибка в работе с БД");
        }
        finally {
            dbFacade.disconnect();
        }
    }
}

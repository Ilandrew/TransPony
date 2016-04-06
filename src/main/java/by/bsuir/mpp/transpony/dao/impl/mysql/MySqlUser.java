package by.bsuir.mpp.transpony.dao.impl.mysql;

import by.bsuir.mpp.transpony.dao.DaoException;
import by.bsuir.mpp.transpony.dao.IUser;
import by.bsuir.mpp.transpony.entity.User;
import by.bsuir.mpp.transpony.entity.UserPosition;
import by.bsuir.mpp.transpony.entity.UserStatus;
import by.bsuir.mpp.transpony.util.DatabaseUtils;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlUser implements IUser {
    @Override
    public List<User> getAll() throws DaoException {
        Connection connection = null;
        Statement statement = null;
        User user;
        List<User> users = new ArrayList<>();
        try {
            connection = DatabaseUtils.getConnection();
            statement = connection.createStatement();

            ResultSet result = statement.executeQuery("SELECT ee.id_employee as id,\n" +
                    "        ee.first_name as first_name,\n" +
                    "        ee.second_name as second_name,\n" +
                    "        ee.middle_name as middle_name,\n" +
                    "        ee.initials as initials,\n" +
                    "        mp.id_user_position as id_position,\n" +
                    "        up.name as name_position,\n" +
                    "        us.id_empoyee_status as id_status,\n" +
                    "        us.name as name_status\n" +
                    "FROM\n" +
                    "        (SELECT max(ep.begin) bg, ep.id_employee FROM EMPLOYEE ee\n" +
                    "LEFT JOIN M2M_EMPLOYEE_POSITION ep ON ee.id_employee = ep.id_employee\n" +
                    "GROUP BY ep.id_employee) tm\n" +
                    "INNER JOIN M2M_EMPLOYEE_POSITION mp ON mp.id_employee = tm.id_employee AND tm.bg = mp.begin\n" +
                    "LEFT JOIN EMPLOYEE ee ON mp.id_employee = ee.id_employee\n" +
                    "LEFT JOIN USER_POSITION up ON mp.id_user_position = up.id_user_position\n" +
                    "INNER JOIN M2M_EMPLOYEE_STATUS es ON mp.id_employee = es.id_employee\n" +
                    "LEFT JOIN USER_STATUS us ON es.id_empoyee_status = us.id_empoyee_status\n"
            );

            while (result.next()) {
                user = new User();
                UserPosition userPosition = new UserPosition();
                UserStatus userStatus = new UserStatus();
                userStatus.setId(result.getInt("id_status"));
                userStatus.setName(result.getString("name_status"));
                user.setUserStatus(userStatus);
                userPosition.setId(result.getInt("id_position"));
                userPosition.setName(result.getString("name_position"));
                user.setUserPosition(userPosition);
                user.setId(result.getInt("id"));
                user.setFirstName(result.getString("first_name"));
                user.setSecondName(result.getString("second_name"));
                user.setMiddleName(result.getString("middle_name"));
                user.setInitials(result.getString("initials"));
                users.add(user);
            }
        } catch (SQLException|NamingException e) {
            throw new DaoException("can't get free driver", e);
        }

        return users;
    }

    @Override
    public List<User> getFreeDriver() throws DaoException {
        Connection connection = null;
        Statement statement = null;
        User user;
        List<User> users = new ArrayList<>();
        try {
            connection = DatabaseUtils.getConnection();
            statement = connection.createStatement();

            ResultSet result = statement.executeQuery("SELECT ee.id_employee as id,\n" +
                    "        ee.first_name as first_name,\n" +
                    "        ee.second_name as second_name,\n" +
                    "        ee.middle_name as middle_name,\n" +
                    "        ee.initials as initials,\n" +
                    "        mp.id_user_position as id_position,\n" +
                    "        up.name as name_position,\n" +
                    "        us.id_empoyee_status as id_status,\n" +
                    "        us.name as name_status\n" +
                    "FROM\n" +
                    "        (SELECT max(ep.begin) bg, ep.id_employee FROM EMPLOYEE ee\n" +
                    "LEFT JOIN M2M_EMPLOYEE_POSITION ep ON ee.id_employee = ep.id_employee\n" +
                    "GROUP BY ep.id_employee) tm\n" +
                    "INNER JOIN M2M_EMPLOYEE_POSITION mp ON mp.id_employee = tm.id_employee AND tm.bg = mp.begin\n" +
                    "LEFT JOIN EMPLOYEE ee ON mp.id_employee = ee.id_employee\n" +
                    "LEFT JOIN USER_POSITION up ON mp.id_user_position = up.id_user_position\n" +
                    "INNER JOIN M2M_EMPLOYEE_STATUS es ON mp.id_employee = es.id_employee\n" +
                    "LEFT JOIN USER_STATUS us ON es.id_empoyee_status = us.id_empoyee_status\n" +
                    "WHERE mp.id_user_position = 5 AND es.id_empoyee_status = 1 AND ee.id_employee NOT IN (\n" +
                    "        SELECT tr.id_employee FROM TRIP tr WHERE tr.id_trip_status = 2\n" +
                    ")");

            while (result.next()) {
                user = new User();
                UserPosition userPosition = new UserPosition();
                UserStatus userStatus = new UserStatus();
                userPosition.setId(result.getInt("id_position"));
                userPosition.setName(result.getString("name_position"));
                user.setUserPosition(userPosition);
                userStatus.setId(result.getInt("id_status"));
                userStatus.setName(result.getString("name_status"));
                user.setUserStatus(userStatus);
                user.setId(result.getInt("id"));
                user.setFirstName(result.getString("first_name"));
                user.setSecondName(result.getString("second_name"));
                user.setMiddleName(result.getString("middle_name"));
                user.setInitials(result.getString("initials"));
                users.add(user);
            }
        } catch (SQLException|NamingException e) {
            throw new DaoException("can't get free driver", e);
        }

        return users;
    }

    @Override
    public void delete(User user) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            /**/
            connection = DatabaseUtils.getConnection();
            statement = connection.prepareStatement("DELETE FROM USER_CREDANTIALS WHERE id_user_credantials = ?");
            statement.setInt(1, user.getId());
            statement.executeUpdate();
            statement = connection.prepareStatement("DELETE FROM EMPLOYEE WHERE id_employee = ?");
            statement.setInt(1, user.getId());
            DatabaseUtils.commit();
        } catch (NamingException|SQLException e) {
            throw new DaoException("Can't remove this user", e);
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }

    }

    @Override
    public void update(User user) throws DaoException {

        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseUtils.getConnection();
            statement = connection.prepareStatement("UPDATE EMPLOYEE SET\n" +
                    "        first_name = ?,\n" +
                    "        second_name = ?,\n" +
                    "        middle_name = ?,\n" +
                    "        initials = ?\n" +
                    "WHERE id_employee = ?");
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getSecondName());
            statement.setString(3, user.getMiddleName());
            statement.setString(4, user.getInitials());
            statement.setInt(5, user.getId());
            statement.executeUpdate();


            DatabaseUtils.commit();
        } catch (NamingException|SQLException e) {
            throw new DaoException("Can't update this user", e);
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
    }

    @Override
    public void add(User user) throws DaoException {

        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseUtils.getConnection();
            statement = connection.prepareStatement("INSERT INTO EMPLOYEE (first_name, second_name, middle_name, initials) VALUES (?, ?, ?, ?)");
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getSecondName());
            statement.setString(3, user.getMiddleName());
            statement.setString(4, user.getInitials());
            statement.executeUpdate();
            DatabaseUtils.commit();
        } catch (NamingException|SQLException e) {
            throw new DaoException("can't add this user", e);
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
    }
}

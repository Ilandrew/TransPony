package by.bsuir.mpp.transpony.dao.impl.mysql;

import by.bsuir.mpp.transpony.dao.DaoException;
import by.bsuir.mpp.transpony.dao.UserDao;
import by.bsuir.mpp.transpony.entity.User;
import by.bsuir.mpp.transpony.entity.UserPosition;
import by.bsuir.mpp.transpony.entity.UserStatus;
import by.bsuir.mpp.transpony.util.DatabaseUtils;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlUserDao implements UserDao {

    private static final MySqlUserDao instance = new MySqlUserDao();
    private MySqlUserDao() {}
    public static MySqlUserDao getInstance() {
        return instance;
    }

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
                    "        mp.begin as begin_position,\n" +
                    "        mp.end as end_position,\n" +
                    "        us.id_empoyee_status as id_status,\n" +
                    "        us.name as name_status,\n" +
                    "        es.begin as begin_status,\n" +
                    "        es.end as end_status\n" +
                    "FROM\n" +
                    "        (SELECT max(ep.begin) bg, ep.id_employee FROM EMPLOYEE ee\n" +
                    "LEFT JOIN M2M_EMPLOYEE_POSITION ep ON ee.id_employee = ep.id_employee\n" +
                    "GROUP BY ep.id_employee) tm\n" +
                    "INNER JOIN M2M_EMPLOYEE_POSITION mp ON mp.id_employee = tm.id_employee AND tm.bg = mp.begin\n" +
                    "LEFT JOIN EMPLOYEE ee ON mp.id_employee = ee.id_employee\n" +
                    "LEFT JOIN USER_POSITION up ON mp.id_user_position = up.id_user_position\n" +
                    "INNER JOIN M2M_EMPLOYEE_STATUS es ON mp.id_employee = es.id_employee\n" +
                    "LEFT JOIN USER_STATUS us ON es.id_empoyee_status = us.id_empoyee_status;"
            );

            while (result.next()) {
                user = new User();
                UserPosition userPosition = new UserPosition();
                UserStatus userStatus = new UserStatus();
                userStatus.setId(result.getInt("id_status"));
                userStatus.setName(result.getString("name_status"));
                userStatus.setDateBegin(result.getDate("begin_status"));
                userStatus.setDateEnd(result.getDate("end_status"));
                user.setUserStatus(userStatus);
                userPosition.setId(result.getInt("id_position"));
                userPosition.setName(result.getString("name_position"));
                userPosition.setDateBegin(result.getDate("begin_position"));
                userPosition.setDateEnd(result.getDate("end_position"));
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
                    "        mp.begin as begin_position,\n" +
                    "        mp.end as end_position,\n" +
                    "        us.id_empoyee_status as id_status,\n" +
                    "        us.name as name_status,\n" +
                    "        es.begin as begin_status,\n" +
                    "        es.end as end_status\n" +
                    "FROM\n" +
                    "(SELECT max(ep.begin) bg, ep.id_employee FROM EMPLOYEE ee\n" +
                    "        LEFT JOIN M2M_EMPLOYEE_POSITION ep ON ee.id_employee = ep.id_employee\n" +
                    "        GROUP BY ep.id_employee) tm\n" +
                    "INNER JOIN M2M_EMPLOYEE_POSITION mp ON mp.id_employee = tm.id_employee AND tm.bg = mp.begin\n" +
                    "LEFT JOIN EMPLOYEE ee ON mp.id_employee = ee.id_employee\n" +
                    "LEFT JOIN USER_POSITION up ON mp.id_user_position = up.id_user_position\n" +
                    "INNER JOIN M2M_EMPLOYEE_STATUS es ON mp.id_employee = es.id_employee\n" +
                    "LEFT JOIN USER_STATUS us ON es.id_empoyee_status = us.id_empoyee_status\n" +
                    "WHERE mp.id_user_position = 5 AND es.id_empoyee_status = 1 AND ee.id_employee NOT IN (\n" +
                    "                           SELECT tr.id_employee FROM TRIP tr WHERE tr.id_trip_status = 2\n" +
                    ")");

            while (result.next()) {
                user = new User();
                UserPosition userPosition = new UserPosition();
                UserStatus userStatus = new UserStatus();
                userPosition.setId(result.getInt("id_position"));
                userPosition.setName(result.getString("name_position"));
                userPosition.setDateBegin(result.getDate("begin_position"));
                userPosition.setDateEnd(result.getDate("end_position"));
                user.setUserPosition(userPosition);
                userStatus.setId(result.getInt("id_status"));
                userStatus.setName(result.getString("name_status"));
                userStatus.setDateBegin(result.getDate("begin_status"));
                userStatus.setDateEnd(result.getDate("end_status"));
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
    public User getForIndex(Integer index) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        User user = new User();
        try {
            connection = DatabaseUtils.getConnection();
            statement = connection.prepareStatement("SELECT ee.id_employee AS id,\n" +
                    "        ee.first_name AS first_name,\n" +
                    "        ee.second_name AS second_name,\n" +
                    "        ee.middle_name AS middle_name,\n" +
                    "        ee.initials AS initials,\n" +
                    "        mp.id_user_position AS id_position,\n" +
                    "        up.name AS name_position,\n" +
                    "        mp.begin AS begin_position,\n" +
                    "        mp.end AS end_position,\n" +
                    "        us.id_empoyee_status AS id_status,\n" +
                    "        us.name AS name_status,\n" +
                    "        es.begin AS begin_status,\n" +
                    "        es.end AS end_status\n" +
                    "FROM\n" +
                    "        (SELECT max(ep.begin) bg, ep.id_employee FROM EMPLOYEE ee\n" +
                    "                LEFT JOIN M2M_EMPLOYEE_POSITION ep ON ee.id_employee = ep.id_employee\n" +
                    "        GROUP BY ep.id_employee) tm\n" +
                    "        INNER JOIN M2M_EMPLOYEE_POSITION mp ON mp.id_employee = tm.id_employee AND tm.bg = mp.begin\n" +
                    "        LEFT JOIN EMPLOYEE ee ON mp.id_employee = ee.id_employee\n" +
                    "        LEFT JOIN USER_POSITION up ON mp.id_user_position = up.id_user_position\n" +
                    "        INNER JOIN M2M_EMPLOYEE_STATUS es ON mp.id_employee = es.id_employee\n" +
                    "        LEFT JOIN USER_STATUS us ON es.id_empoyee_status = us.id_empoyee_status\n" +
                    "WHERE ee.id_employee = ?");
            statement.setInt(1, index);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                UserStatus userStatus = new UserStatus();
                UserPosition userPosition = new UserPosition();
                userStatus.setId(result.getInt("id_status"));
                userStatus.setName(result.getString("name_status"));
                userStatus.setDateBegin(result.getDate("begin_status"));
                userStatus.setDateEnd(result.getDate("end_status"));
                user.setUserStatus(userStatus);
                userPosition.setId(result.getInt("id_position"));
                userPosition.setName(result.getString("name_position"));
                userPosition.setDateBegin(result.getDate("begin_position"));
                userPosition.setDateEnd(result.getDate("end_position"));
                user.setUserPosition(userPosition);
                user.setId(result.getInt("id"));
                user.setFirstName(result.getString("first_name"));
                user.setSecondName(result.getString("second_name"));
                user.setMiddleName(result.getString("middle_name"));
                user.setInitials(result.getString("initials"));
            }
        } catch (SQLException|NamingException e) {
            throw new DaoException("can't get this user", e);
        }

        return user;
    }

    @Override
    public void fire(User user) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseUtils.getConnection();
            statement = connection.prepareStatement("UPDATE M2M_EMPLOYEE_STATUS SET\n" +
                    "        id_empoyee_status = 4,\n" +
                    "        end = ?\n" +
                    "WHERE begin = ?\n" +
                    "      AND id_employee = ?");
            statement.setDate(1, new java.sql.Date(user.getUserPosition().getDateEnd().getTime()));
            statement.setDate(2, new java.sql.Date(user.getUserPosition().getDateBegin().getTime()));
            statement.setInt(3, user.getId());

            statement.executeUpdate();
            DatabaseUtils.commit();
        } catch (NamingException|SQLException e) {
            throw new DaoException("can't fire this user", e);
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
    }

    @Override
    public void delete(User user) throws DaoException {
        //!Не использовать
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseUtils.getConnection();
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
    public Integer add(User user) throws DaoException {

        Connection connection = null;
        PreparedStatement statement = null;
        Integer index = 0;
        try {
            connection = DatabaseUtils.getConnection();
            statement = connection.prepareStatement("INSERT INTO EMPLOYEE (first_name, second_name, middle_name, initials) VALUES (?, ?, ?, ?)");
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getSecondName());
            statement.setString(3, user.getMiddleName());
            statement.setString(4, user.getInitials());
            statement.executeUpdate();
            DatabaseUtils.commit();

            statement = connection.prepareStatement("SELECT max(id_employee) as id\n" +
                    "FROM EMPLOYEE\n" +
                    "WHERE first_name = ?\n" +
                    "      AND second_name = ?\n" +
                    "      AND middle_name = ?\n" +
                    "      AND initials = ?");
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getSecondName());
            statement.setString(3, user.getMiddleName());
            statement.setString(4, user.getInitials());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                index = resultSet.getInt("id");
            }
        } catch (NamingException|SQLException e) {
            throw new DaoException("can't add this user", e);
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }

        return index;
    }
}

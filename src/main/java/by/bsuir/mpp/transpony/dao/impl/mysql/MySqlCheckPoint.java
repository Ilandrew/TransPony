package by.bsuir.mpp.transpony.dao.impl.mysql;

import by.bsuir.mpp.transpony.dao.DaoException;
import by.bsuir.mpp.transpony.dao.ICheckPoint;
import by.bsuir.mpp.transpony.entity.CheckPoint;
import by.bsuir.mpp.transpony.util.DatabaseUtils;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlCheckPoint implements ICheckPoint {
    @Override
    public List<CheckPoint> getAll() throws DaoException {
        Connection connection = null;
        Statement statement = null;
        CheckPoint checkPoint;
        List<CheckPoint> collection = new ArrayList<>();
        try {
            connection = DatabaseUtils.getConnection();
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT cp.id_check_point as id_check_point,\n" +
                    "        cp.x as x,\n" +
                    "        cp.y as y,\n" +
                    "        cp.name as name,\n" +
                    "        ct.name as type\n" +
                    "FROM CHECK_POINT cp\n" +
                    "LEFT JOIN CHECK_POINT_TYPE ct ON cp.id_check_point_type = ct.id_check_point_type");

            while (result.next()) {
                checkPoint = new CheckPoint();
                checkPoint.setId(result.getInt("id_check_point"));
                checkPoint.setX(result.getFloat("x"));
                checkPoint.setY(result.getFloat("y"));
                checkPoint.setName(result.getString("name"));
                checkPoint.setPointType(result.getString("type"));
                collection.add(checkPoint);
            }
        } catch (SQLException|NamingException e) {
            throw new DaoException("can't get all check point", e);
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }

        return collection;
    }

    public List<CheckPoint> getForRoute() throws DaoException {
        Connection connection = null;
        Statement statement = null;
        CheckPoint checkPoint;
        List<CheckPoint> collection = new ArrayList<>();
        try {
            connection = DatabaseUtils.getConnection();
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT cp.id_check_point as id_check_point,\n" +
                    "        cp.x as x,\n" +
                    "        cp.y as y,\n" +
                    "        cp.name as name,\n" +
                    "        ct.name as type\n" +
                    "FROM CHECK_POINT cp\n" +
                    "LEFT JOIN CHECK_POINT_TYPE ct ON cp.id_check_point_type = ct.id_check_point_type\n" +
                    "LEFT JOIN M2M_CHECK_POINT_ROUTE cr ON cp.id_check_point = cr.id_check_point\n" +
                    "WHERE cr.id_route = ?\n" +
                    "ORDER BY cr.number");

            while (result.next()) {

                checkPoint = new CheckPoint();
                checkPoint.setId(result.getInt("id_check_point"));
                checkPoint.setY(result.getFloat("y"));
                checkPoint.setX(result.getFloat("x"));
                checkPoint.setName(result.getString("name"));
                checkPoint.setPointType(result.getString("type"));
                collection.add(checkPoint);
            }
        } catch (SQLException|NamingException e) {
            throw new DaoException("can't get check point for route", e);
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }

        return collection;
    }

    @Override
    public void updateForRoute(List<CheckPoint> checkPoints, Integer idRoute) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseUtils.getConnection();
            statement = connection.prepareStatement("DELETE FROM M2M_CHECK_POINT_ROUTE WHERE id_route = ?");
            statement.setInt(1, idRoute);
            statement.executeUpdate();
            DatabaseUtils.commit();

            int i = 1;
            for (CheckPoint checkPoint: checkPoints) {
                statement = connection.prepareStatement("INSERT INTO M2M_CHECK_POINT_ROUTE (id_route, id_check_point, number) VALUES (?, ?, ?)");
                statement.setInt(1, idRoute);
                statement.setInt(2, checkPoint.getId());
                statement.setInt(3, i);
                i++;
                statement.executeUpdate();
                DatabaseUtils.commit();
            }
        } catch (NamingException|SQLException e) {
            throw new DaoException("Can't update route", e);
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }


    }

    @Override
    public void addNew(CheckPoint checkPoint) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseUtils.getConnection();
            statement = connection.prepareStatement("INSERT INTO CHECK_POINT (x, y, name, id_check_point_type) VALUES (?, ?, ?, ?)");
            statement.setFloat(1, checkPoint.getX());
            statement.setFloat(2, checkPoint.getY());
            statement.setString(3, checkPoint.getName());
            statement.setInt(4, getIndexType(checkPoint.getPointType()));
            statement.executeUpdate();
            DatabaseUtils.commit();
        } catch (NamingException|SQLException e) {
            throw new DaoException("can't add this check point", e);
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
    }

    @Override
    public void delete(CheckPoint checkPoint) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseUtils.getConnection();
            statement = connection.prepareStatement("DELETE FROM CHECK_POINT WHERE id_check_point = ?");
            statement.setInt(1, checkPoint.getId());
            statement.executeUpdate();
            DatabaseUtils.commit();
        } catch (NamingException|SQLException e) {
            throw new DaoException("Can't remove this check point", e);
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
    }

    @Override
    public void update(CheckPoint checkPoint) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseUtils.getConnection();
            statement = connection.prepareStatement("UPDATE CHECK_POINT SET\n" +
                    "        x = ?,\n" +
                    "        y = ?,\n" +
                    "        name = ?,\n" +
                    "        id_check_point = ?\n" +
                    "WHERE id_check_point = ?");
            statement.setFloat(1, checkPoint.getX());
            statement.setFloat(2, checkPoint.getY());
            statement.setString(3, checkPoint.getName());
            statement.setInt(4, getIndexType(checkPoint.getPointType()));
            statement.setInt(5, checkPoint.getId());
            statement.executeUpdate();
            DatabaseUtils.commit();
        } catch (NamingException|SQLException e) {
            throw new DaoException("Can't update this check point", e);
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
    }


    private Integer getIndexType(String name) throws SQLException, NamingException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseUtils.getConnection();
            statement = connection.prepareStatement("SELECT id_check_point_type FROM CHECK_POINT_TYPE WHERE name = ?");
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id_check_point_type");
            }
            statement = connection.prepareStatement("INSERT INTO VENDOR_CAR (name) VALUES (?)");
            statement.setString(1, name);
            statement.executeUpdate();
            DatabaseUtils.commit();
            statement = connection.prepareStatement("SELECT id_check_point_type FROM CHECK_POINT_TYPE WHERE name = ?");
            statement.setString(1, name);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id_check_point_type");
            }
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
        return 0;
    }
}

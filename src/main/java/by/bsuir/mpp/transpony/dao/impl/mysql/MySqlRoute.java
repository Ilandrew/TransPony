package by.bsuir.mpp.transpony.dao.impl.mysql;

import by.bsuir.mpp.transpony.dao.DaoException;
import by.bsuir.mpp.transpony.dao.IRoute;
import by.bsuir.mpp.transpony.entity.CheckPoint;
import by.bsuir.mpp.transpony.entity.Route;
import by.bsuir.mpp.transpony.util.DatabaseUtils;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlRoute implements IRoute {

    private static final MySqlRoute instance = new MySqlRoute();
    private MySqlRoute() {};
    public static MySqlRoute getInstance() {
        return instance;
    }
    @Override
    public List<Route> getAll() throws DaoException {
        Connection connection = null;
        Statement statement = null;
        Route route;
        List<Route> collection = new ArrayList<>();
        try {
            connection = DatabaseUtils.getConnection();
            statement = connection.createStatement();

            ResultSet result = statement.executeQuery("SELECT id_route, id_employee, total_length FROM ROUTE");

            while (result.next()) {
                route = new Route();
                route.setId(result.getInt("id_route"));
                route.setOwner(MySqlUser.getInstance().getForIndex(result.getInt("id_employee")));
                route.setTotalLength(result.getBigDecimal("total_length"));
                route.setPoints(MySqlCheckPoint.getInstance().getForRoute(route.getId()));
                collection.add(route);
            }
        } catch (SQLException | NamingException e) {
            throw new DaoException("can't get all route", e);
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
        return collection;
    }

    @Override
    public void update(Route route) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseUtils.getConnection();
            statement = connection.prepareStatement("UPDATE ROUTE SET\n" +
                    "        total_length = ?,\n" +
                    "        id_employee = ?\n" +
                    "WHERE id_route = ?");
            statement.setBigDecimal(1, route.getTotalLength());
            statement.setInt(2, route.getOwner().getId());
            statement.setInt(3, route.getId());
            statement.executeUpdate();
            DatabaseUtils.commit();

            MySqlCheckPoint.getInstance().updateForRoute(route.getPoints(), route.getId());
        } catch (NamingException|SQLException e) {
            throw new DaoException("Can't update this route", e);
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
    }

    @Override
    public void add(Route route) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseUtils.getConnection();
            statement = connection.prepareStatement("INSERT INTO ROUTE (total_length, id_employee) VALUES (?, ?)");
            statement.setBigDecimal(1, route.getTotalLength());
            statement.setInt(2, route.getOwner().getId());
            statement.executeUpdate();
            DatabaseUtils.commit();

            MySqlCheckPoint.getInstance().updateForRoute(route.getPoints(), route.getId());

        } catch (NamingException|SQLException e) {
            throw new DaoException("can't add this route", e);
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
        return;
    }

    @Override
    public void delete(Route route) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseUtils.getConnection();
            statement = connection.prepareStatement("DELETE FROM ROUTE WHERE id_route = ?");
            statement.setInt(1, route.getId());
            statement.executeUpdate();
            DatabaseUtils.commit();
        } catch (NamingException |SQLException e) {
            throw new DaoException("Can't remove this route", e);
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }

    }

    @Override
    public Route getForIndex(Integer index) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        Route route = new Route();
        try {
            connection = DatabaseUtils.getConnection();
            statement = connection.prepareStatement("SELECT id_route, id_employee, total_length FROM ROUTE WHERE  id_route = ?");
            statement.setInt(1, index);

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                route.setId(result.getInt("id_route"));
                route.setOwner(MySqlUser.getInstance().getForIndex(result.getInt("id_employee")));
                route.setTotalLength(result.getBigDecimal("total_length"));
                route.setPoints(MySqlCheckPoint.getInstance().getForRoute(route.getId()));
            }
        } catch (SQLException | NamingException e) {
            throw new DaoException("can't get all route", e);
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
        return route;
    }

    @Override
    public List<CheckPoint> getCheckPointForRoute(Integer index) throws DaoException {

        return MySqlCheckPoint.getInstance().getForRoute(index);
    }
}

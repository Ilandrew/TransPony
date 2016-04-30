package by.bsuir.mpp.transpony.dao.impl.mysql;

import by.bsuir.mpp.transpony.dao.DaoException;
import by.bsuir.mpp.transpony.dao.RouteDao;
import by.bsuir.mpp.transpony.entity.CheckPoint;
import by.bsuir.mpp.transpony.entity.Route;
import by.bsuir.mpp.transpony.util.DatabaseUtils;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlRouteDao implements RouteDao {

    private static final MySqlRouteDao instance = new MySqlRouteDao();
    private MySqlRouteDao() {}
    public static MySqlRouteDao getInstance() {
        return instance;
    }

    private static final String SQL_GET_ALL = "SELECT id_route, id_employee, total_length FROM ROUTE";
	private static final String SQL_UPDATE = "UPDATE ROUTE SET\n" +
			"        total_length = ?,\n" +
			"        id_employee = ?\n" +
			"WHERE id_route = ?";
	private static final String SQL_ADD = "INSERT INTO ROUTE (total_length, id_employee) VALUES (?, ?)";
	private static final String SQL_DELETE = "DELETE FROM ROUTE WHERE id_route = ?";
	private static final String SQL_GET_BY_ID = "SELECT id_route, id_employee, total_length FROM ROUTE WHERE  id_route = ?";
	private static final String SQL_GET_MAX_ID = "SELECT max(id_route) as value FROM ROUTE";
    private static final String SQL_DELETE_POINTS = "DELETE FROM M2M_CHECK_POINT_ROUTE WHERE id_route = ?";

    @Override
    public List<Route> getAll() throws DaoException {
        Connection connection = null;
        Statement statement = null;
        Route route;
        List<Route> collection = new ArrayList<>();
        try {
            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.createStatement();

            ResultSet result = statement.executeQuery(SQL_GET_ALL);

            while (result.next()) {
                route = new Route();
                route.setId(result.getInt("id_route"));
                route.setOwner(MySqlUserDao.getInstance().getById(result.getInt("id_employee")));
                route.setTotalLength(result.getBigDecimal("total_length"));
                route.setPoints(MySqlCheckPointDao.getInstance().getByRouteId(route.getId()));
                collection.add(route);
            }
        } catch (SQLException | NamingException e) {
            throw new DaoException("Can't get all routes.", e);
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
            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_UPDATE);
            statement.setBigDecimal(1, route.getTotalLength());
            statement.setInt(2, route.getOwner().getId());
            statement.setInt(3, route.getId());
            statement.executeUpdate();
            MySqlCheckPointDao.getInstance().updateRouteCheckpoints(route.getPoints(), route.getId());
        } catch (NamingException|SQLException e) {
            DatabaseUtils.rollback(connection);
            throw new DaoException("Can't update this route.", e);
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
            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_ADD);
            statement.setBigDecimal(1, route.getTotalLength());
            statement.setInt(2, route.getOwner().getId());
            statement.executeUpdate();
            route.setId(getMaxId());
            MySqlCheckPointDao.getInstance().updateRouteCheckpoints(route.getPoints(), route.getId());
        } catch (NamingException|SQLException e) {
            DatabaseUtils.rollback(connection);
            throw new DaoException("Can't add this route.", e);
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
    }

    @Override
    public void delete(Route route) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_DELETE_POINTS);
            statement.setInt(1, route.getId());
            statement.executeUpdate();
            statement = connection.prepareStatement(SQL_DELETE);
            statement.setInt(1, route.getId());
            statement.executeUpdate();
        } catch (NamingException |SQLException e) {
            DatabaseUtils.rollback(connection);
            throw new DaoException("Can't remove this route.", e);
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }

    }

    @Override
    public Route getById(Integer id) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        Route route = new Route();
        try {
            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_GET_BY_ID);
            statement.setInt(1, id);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                route.setId(result.getInt("id_route"));
                route.setOwner(MySqlUserDao.getInstance().getById(result.getInt("id_employee")));
                route.setTotalLength(result.getBigDecimal("total_length"));
                route.setPoints(MySqlCheckPointDao.getInstance().getByRouteId(route.getId()));
            }
        } catch (SQLException | NamingException e) {
            throw new DaoException("Can't get all route.", e);
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
        return route;
    }

    @Override
    public List<CheckPoint> getByRouteId(Integer id) throws DaoException {
        return MySqlCheckPointDao.getInstance().getByRouteId(id);
    }

	private Integer getMaxId() throws DaoException {
       	Connection connection = null;
        Statement statement = null;
        Integer value = null;
        try {
            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.createStatement();

            ResultSet result = statement.executeQuery(SQL_GET_MAX_ID);
            if (result.next()) {
               value = result.getInt("value");
            }
        } catch (SQLException | NamingException e) {
            throw new DaoException("Can't get all route.", e);
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
        return value;
    }
}

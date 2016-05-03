package by.bsuir.mpp.transpony.dao.impl.mysql;

import by.bsuir.mpp.transpony.dao.DaoException;
import by.bsuir.mpp.transpony.dao.CheckPointDao;
import by.bsuir.mpp.transpony.entity.CheckPoint;
import by.bsuir.mpp.transpony.util.DatabaseUtils;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlCheckPointDao implements CheckPointDao {
    private static final MySqlCheckPointDao instance = new MySqlCheckPointDao();
    private MySqlCheckPointDao() {}
    public static MySqlCheckPointDao getInstance() {
        return instance;
    }

    private static final String SQL_GET_ALL = "SELECT cp.id_check_point as id_check_point,\n" +
			"        cp.x as x,\n" +
			"        cp.y as y,\n" +
			"        cp.name as name,\n" +
			"        ct.name as type\n" +
			"FROM CHECK_POINT cp\n" +
			"LEFT JOIN CHECK_POINT_TYPE ct ON cp.id_check_point_type = ct.id_check_point_type";
	private static final String SQL_GET_BY_ROUTE_ID = "SELECT cp.id_check_point as id_check_point,\n" +
			"        cp.x as x,\n" +
			"        cp.y as y,\n" +
			"        cp.name as name,\n" +
			"        ct.name as type\n" +
			"FROM CHECK_POINT cp\n" +
			"LEFT JOIN CHECK_POINT_TYPE ct ON cp.id_check_point_type = ct.id_check_point_type\n" +
			"LEFT JOIN M2M_CHECK_POINT_ROUTE cr ON cp.id_check_point = cr.id_check_point\n" +
			"WHERE cr.id_route = ?\n" +
			"ORDER BY cr.number";
	private static final String SQL_DELETE_CHECKPOINTS = "DELETE FROM M2M_CHECK_POINT_ROUTE WHERE id_route = ?";
	private static final String SQL_INSERT_CHECKPOINTS = "INSERT INTO M2M_CHECK_POINT_ROUTE (id_route, id_check_point, number) VALUES (?, ?, ?)";
	private static final String SQL_ADD_CHECKPOINT = "INSERT INTO CHECK_POINT (x, y, name, id_check_point_type) VALUES (?, ?, ?, ?)";
	private static final String SQL_GET_MAX_ID = "SELECT max(id_check_point) as id\n" +
			"FROM CHECK_POINT\n" +
			"WHERE name = ?\n" +
			"      AND x = ?\n" +
			"      AND y = ?\n" +
			"      AND id_check_point_type = ?";
	private static final String SQL_DELETE_CHECKPOINT = "DELETE FROM CHECK_POINT WHERE id_check_point = ?";
	private static final String UPDATE_CHECKPOINT = "UPDATE CHECK_POINT SET\n" +
			"        x = ?,\n" +
			"        y = ?,\n" +
			"        name = ?,\n" +
			"        id_check_point_type = ?\n" +
			"WHERE id_check_point = ?";
	private static final String SQL_GET_BY_ID = "SELECT cp.id_check_point as id_check_point,\n" +
			"        cp.x as x,\n" +
			"        cp.y as y,\n" +
			"        cp.name as name,\n" +
			"        ct.name as type\n" +
			"FROM CHECK_POINT cp\n" +
			"LEFT JOIN CHECK_POINT_TYPE ct ON cp.id_check_point_type = ct.id_check_point_type\n" +
			"WHERE cp.id_check_point = ?";
	private static final String SQL_GET_ALL_TYPES = "SELECT name FROM CHECK_POINT_TYPE";
	private static final String SQL_GET_TYPE_ID = "SELECT id_check_point_type FROM CHECK_POINT_TYPE WHERE name = ?";
	private static final String SQL_ADD_TYPE = "INSERT INTO CHECK_POINT_TYPE (name) VALUES (?)";

	@Override
    public List<CheckPoint> getAll() throws DaoException {
        Connection connection = null;
        Statement statement = null;
        CheckPoint checkPoint;
        List<CheckPoint> collection = new ArrayList<>();
        try {
            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery(SQL_GET_ALL);

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
            DatabaseUtils.rollback(connection);
            throw new DaoException("Can't get all checkpoints.", e);
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }

        return collection;
    }

    @Override
    public List<CheckPoint> getByRouteId(Integer routeId) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        CheckPoint checkPoint;
        List<CheckPoint> collection = new ArrayList<>();
        try {
            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_GET_BY_ROUTE_ID);
            statement.setInt(1, routeId);
            ResultSet result = statement.executeQuery();

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
            DatabaseUtils.rollback(connection);
            throw new DaoException("Can't get checkpoint for route.", e);
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }

        return collection;
    }

    @Override
    public void updateRouteCheckpoints(List<CheckPoint> checkPoints, Integer idRoute) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_DELETE_CHECKPOINTS);
            statement.setInt(1, idRoute);
            statement.executeUpdate();

            int i = 1;
            for (CheckPoint checkPoint: checkPoints) {
                statement = connection.prepareStatement(SQL_INSERT_CHECKPOINTS);
                statement.setInt(1, idRoute);
                statement.setInt(2, checkPoint.getId());
                statement.setInt(3, i);
                i++;
                statement.executeUpdate();
            }
        } catch (NamingException|SQLException e) {
            DatabaseUtils.rollback(connection);
            throw new DaoException("Can't update route.", e);
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }


    }

    @Override
    public Integer add(CheckPoint checkPoint) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        Integer index = 0;
        try {
            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_ADD_CHECKPOINT);
            statement.setFloat(1, checkPoint.getX());
            statement.setFloat(2, checkPoint.getY());
            statement.setString(3, checkPoint.getName());
            statement.setInt(4, getTypeId(checkPoint.getPointType()));
            statement.executeUpdate();

            statement = connection.prepareStatement(SQL_GET_MAX_ID);
            statement.setString(1, checkPoint.getName());
            statement.setFloat(2, checkPoint.getX());
            statement.setFloat(3, checkPoint.getY());
            statement.setInt(4, getTypeId(checkPoint.getPointType()));

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                index = resultSet.getInt("id");
            }
        } catch (NamingException|SQLException e) {
            DatabaseUtils.rollback(connection);
            throw new DaoException("can't add this check point", e);
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
        return index;
    }

    @Override
    public void delete(CheckPoint checkPoint) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_DELETE_CHECKPOINT);
            statement.setInt(1, checkPoint.getId());
            statement.executeUpdate();
        } catch (NamingException|SQLException e) {
            DatabaseUtils.rollback(connection);
            throw new DaoException("Can't remove this checkpoint.", e);
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
            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.prepareStatement(UPDATE_CHECKPOINT);
            statement.setFloat(1, checkPoint.getX());
            statement.setFloat(2, checkPoint.getY());
            statement.setString(3, checkPoint.getName());
            statement.setInt(4, getTypeId(checkPoint.getPointType()));
            statement.setInt(5, checkPoint.getId());
            statement.executeUpdate();
        } catch (NamingException|SQLException e) {
            DatabaseUtils.rollback(connection);
            throw new DaoException("Can't update this checkpoint.", e);
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
    }

    @Override
    public CheckPoint getById(Integer index) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        CheckPoint checkPoint = new CheckPoint();
        try {
            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_GET_BY_ID);
            statement.setInt(1, index);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                checkPoint.setId(result.getInt("id_check_point"));
                checkPoint.setX(result.getFloat("x"));
                checkPoint.setY(result.getFloat("y"));
                checkPoint.setName(result.getString("name"));
                checkPoint.setPointType(result.getString("type"));
            }
        } catch (SQLException|NamingException e) {
            DatabaseUtils.rollback(connection);
            throw new DaoException("Can't get all checkpoint.", e);
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }

        return checkPoint;
    }

    @Override
    public List<String> getAllTypes() throws DaoException {
        Connection connection = null;
        Statement statement = null;
        String name;
        List<String> collection = new ArrayList<>();
        try {
            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.createStatement();

            ResultSet result = statement.executeQuery(SQL_GET_ALL_TYPES);

            while (result.next()) {
                name = result.getString("name");
                collection.add(name);
            }
        } catch (SQLException | NamingException e) {
            throw new DaoException("Can't get all checkpoint type.", e);
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
        return collection;
    }


    private Integer getTypeId(String name) throws SQLException, NamingException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_GET_TYPE_ID);
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id_check_point_type");
            }
            statement = connection.prepareStatement(SQL_ADD_TYPE);
            statement.setString(1, name);
            statement.executeUpdate();
            statement = connection.prepareStatement(SQL_GET_TYPE_ID);
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

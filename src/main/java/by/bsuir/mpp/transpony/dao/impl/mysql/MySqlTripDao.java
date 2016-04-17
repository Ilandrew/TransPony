package by.bsuir.mpp.transpony.dao.impl.mysql;

import by.bsuir.mpp.transpony.dao.DaoException;
import by.bsuir.mpp.transpony.dao.TripDao;
import by.bsuir.mpp.transpony.entity.Trip;
import by.bsuir.mpp.transpony.util.DatabaseUtils;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlTripDao implements TripDao {

    private static final MySqlTripDao instance = new MySqlTripDao();
    private MySqlTripDao() {}
    public static MySqlTripDao getInstance() {
        return instance;
    }

    private static final String SQL_GET_ALL = "SELECT tp.id_trip AS id_trip,\n" +
			"        ts.name AS name_status,\n" +
			"        tp.start_date AS start_date,\n" +
			"        tp.expected_finish_date AS expected_finish_date,\n" +
			"        tp.real_finish_date AS real_finish_date,\n" +
			"        tp.real_fuel_consumption AS real_fuel_consumption,\n" +
			"        tp.expected_fuel_consuption AS expected_fuel_consuption,\n" +
			"        tp.driver_profit AS driver_profit,\n" +
			"        tp.expenses AS expenses,\n" +
			"        tp.id_waybill AS id_waybill,\n" +
			"        tp.id_route AS id_route,\n" +
			"        tp.id_car AS id_car,\n" +
			"        tp.id_employee AS id_driver\n" +
			"FROM TRIP tp\n" +
			"LEFT JOIN TRIP_STATUS  ts ON tp.id_trip_status = ts.id_trip_status";
	private static final String SQL_GET_BY_ID = "SELECT tp.id_trip AS id_trip,\n" +
			"        ts.name AS name_status,\n" +
			"        tp.start_date AS start_date,\n" +
			"        tp.expected_finish_date AS expected_finish_date,\n" +
			"        tp.real_finish_date AS real_finish_date,\n" +
			"        tp.real_fuel_consumption AS real_fuel_consumption,\n" +
			"        tp.expected_fuel_consuption AS expected_fuel_consuption,\n" +
			"        tp.driver_profit AS driver_profit,\n" +
			"        tp.expenses AS expenses,\n" +
			"        tp.id_waybill AS id_waybill,\n" +
			"        tp.id_route AS id_route,\n" +
			"        tp.id_car AS id_car,\n" +
			"        tp.id_employee AS id_driver\n" +
			"FROM TRIP tp\n" +
			"LEFT JOIN TRIP_STATUS  ts ON tp.id_trip_status = ts.id_trip_status\n" +
			"WHERE  tp.id_trip = ?";
	private static final String SQL_UPDATE_STATUS = "UPDATE TRIP SET\n" +
			"        id_trip_status = ?\n" +
			"WHERE id_trip = ?";
	private static final String SQL_BY_DRIVER_ID = "SELECT tp.id_trip AS id_trip,\n" +
			"        ts.name AS name_status,\n" +
			"        tp.start_date AS start_date,\n" +
			"        tp.expected_finish_date AS expected_finish_date,\n" +
			"        tp.real_finish_date AS real_finish_date,\n" +
			"        tp.real_fuel_consumption AS real_fuel_consumption,\n" +
			"        tp.expected_fuel_consuption AS expected_fuel_consuption,\n" +
			"        tp.driver_profit AS driver_profit,\n" +
			"        tp.expenses AS expenses,\n" +
			"        tp.id_waybill AS id_waybill,\n" +
			"        tp.id_route AS id_route,\n" +
			"        tp.id_car AS id_car,\n" +
			"        tp.id_employee AS id_driver\n" +
			"FROM TRIP tp\n" +
			"LEFT JOIN TRIP_STATUS  ts ON tp.id_trip_status = ts.id_trip_status\n" +
			"WHERE  tp.id_employee = ?";
	private static final String SQL_GET_TRIPS_NOT_SET_FUEL = "SELECT tp.id_trip AS id_trip,\n" +
			"        ts.name AS name_status,\n" +
			"        tp.start_date AS start_date,\n" +
			"        tp.expected_finish_date AS expected_finish_date,\n" +
			"        tp.real_finish_date AS real_finish_date,\n" +
			"        tp.real_fuel_consumption AS real_fuel_consumption,\n" +
			"        tp.expected_fuel_consuption AS expected_fuel_consuption,\n" +
			"        tp.driver_profit AS driver_profit,\n" +
			"        tp.expenses AS expenses,\n" +
			"        tp.id_waybill AS id_waybill,\n" +
			"        tp.id_route AS id_route,\n" +
			"        tp.id_car AS id_car,\n" +
			"        tp.id_employee AS id_driver\n" +
			"FROM TRIP tp\n" +
			"LEFT JOIN TRIP_STATUS  ts ON tp.id_trip_status = ts.id_trip_status\n" +
			"WHERE  tp.real_fuel_consumption IS NULL";
	private static final String SQL_UPDATE = "UPDATE TRIP SET\n" +
			"        id_trip_status = ?,\n" +
			"        start_date = ?,\n" +
			"        expected_finish_date = ?,\n" +
			"        real_finish_date = ?,\n" +
			"        real_fuel_consumption = ?,\n" +
			"        expected_fuel_consuption = ?,\n" +
			"        driver_profit = ?,\n" +
			"        expenses = ?,\n" +
			"        id_waybill = ?,\n" +
			"        id_route = ?,\n" +
			"        id_car = ?,\n" +
			"        id_employee = ?\n" +
			"WHERE id_trip = ?";
	private static final String SQL_DELETE = "DELETE FROM TRIP WHERE id_trip = ?";
	private static final String SQL_ADD = "INSERT INTO TRIP (id_trip_status, start_date, expected_finish_date, " +
		"expected_fuel_consuption, driver_profit, expenses, id_waybill, id_route, id_car, id_employee) VALUES " +
		"(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String SQL_GET_ALL_STATUSES = "SELECT name FROM TRIP_STATUS";
	private static final String SQL_GET_STATUS = "SELECT id_trip_status FROM TRIP_STATUS WHERE name = ?";
	private static final String SQL_ADD_STATUS = "INSERT INTO TRIP_STATUS (name) VALUES (?)";

	@Override
    public List<Trip> getAll() throws DaoException {

        Connection connection = null;
        Statement statement = null;
        Trip trip;
        List<Trip> collection = new ArrayList<>();
        try {
            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.createStatement();

            ResultSet result = statement.executeQuery(SQL_GET_ALL);

            while (result.next()) {
                trip = new Trip();
                trip.setId(result.getInt("id_trip"));
                trip.setTripStatus(result.getString("name_status"));
                trip.setStartDate(result.getDate("start_date"));
                trip.setExpectedFinishDate(result.getDate("expected_finish_date"));
                trip.setRealFinishDate(result.getDate("real_finish_date"));
                trip.setRealFuelConsumption(result.getBigDecimal("real_fuel_consumption"));
                trip.setExpectedFuelConsumption(result.getBigDecimal("expected_fuel_consuption"));
                trip.setDriverProfit(result.getBigDecimal("driver_profit"));
                trip.setExpenses(result.getBigDecimal("expenses"));
                trip.setWaybill(MySqlWaybillDao.getInstance().getById(result.getInt("id_waybill")));
                trip.setRoute(MySqlRouteDao.getInstance().getById(result.getInt("id_route")));
                trip.setCar(MySqlCarDao.getInstance().getById(result.getInt("id_car")));
                trip.setDriver(MySqlUserDao.getInstance().getById(result.getInt("id_driver")));

                collection.add(trip);
            }
        } catch (SQLException | NamingException e) {
            throw new DaoException("Can't get all trips.", e);
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
        return collection;

    }

    @Override
    public Trip getById(Integer index) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        Trip trip = new Trip();
        try {
            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_GET_BY_ID);
            statement.setInt(1, index);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                trip.setId(result.getInt("id_trip"));
                trip.setTripStatus(result.getString("name_status"));
                trip.setStartDate(result.getDate("start_date"));
                trip.setExpectedFinishDate(result.getDate("expected_finish_date"));
                trip.setRealFinishDate(result.getDate("real_finish_date"));
                trip.setExpectedFuelConsumption(result.getBigDecimal("expected_fuel_consuption"));
                trip.setDriverProfit(result.getBigDecimal("driver_profit"));
                trip.setExpenses(result.getBigDecimal("expenses"));
                trip.setWaybill(MySqlWaybillDao.getInstance().getById(result.getInt("id_waybill")));
                trip.setRoute(MySqlRouteDao.getInstance().getById(result.getInt("id_route")));
                trip.setCar(MySqlCarDao.getInstance().getById(result.getInt("id_car")));
                trip.setDriver(MySqlUserDao.getInstance().getById(result.getInt("id_driver")));
            }
        } catch (SQLException | NamingException e) {
            throw new DaoException("can't get this trip", e);
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
        return trip;
    }

    @Override
    public void updateStatus(Trip trip, String statusName) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_UPDATE_STATUS);
            statement.setInt(1, getIndexStatus(trip.getTripStatus()));
            statement.setInt(2, trip.getId());
            statement.executeUpdate();
        } catch (NamingException|SQLException e) {
            DatabaseUtils.rollback(connection);
            throw new DaoException("Can't change this trip status.", e);
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
    }

    @Override
    public List<Trip> getByDriverId(Integer driverId) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        Trip trip;
        List<Trip> collection = new ArrayList<>();
        try {
            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_BY_DRIVER_ID);
            statement.setInt(1, driverId);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                trip = new Trip();
                trip.setTripStatus(result.getString("name_status"));
                trip.setId(result.getInt("id_trip"));
                trip.setStartDate(result.getDate("start_date"));
                trip.setExpectedFinishDate(result.getDate("expected_finish_date"));
                trip.setRealFinishDate(result.getDate("real_finish_date"));
                trip.setExpectedFuelConsumption(result.getBigDecimal("expected_fuel_consuption"));
                trip.setDriverProfit(result.getBigDecimal("driver_profit"));
                trip.setExpenses(result.getBigDecimal("expenses"));
                trip.setWaybill(MySqlWaybillDao.getInstance().getById(result.getInt("id_waybill")));
                trip.setRoute(MySqlRouteDao.getInstance().getById(result.getInt("id_route")));
                trip.setCar(MySqlCarDao.getInstance().getById(result.getInt("id_car")));
                trip.setDriver(MySqlUserDao.getInstance().getById(result.getInt("id_driver")));
                collection.add(trip);
            }
        } catch (SQLException | NamingException e) {
            DatabaseUtils.rollback(connection);
            throw new DaoException("Can't get trip for driver.", e);
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
        return collection;
    }

    @Override
    public List<Trip> getWithoutFuel() throws DaoException {
        Connection connection = null;
        Statement statement = null;
        Trip trip;
        List<Trip> collection = new ArrayList<>();
        try {
            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery(SQL_GET_TRIPS_NOT_SET_FUEL);

            if (result.next()) {
                trip = new Trip();
                trip.setTripStatus(result.getString("name_status"));
                trip.setStartDate(result.getDate("start_date"));
                trip.setId(result.getInt("id_trip"));
                trip.setExpectedFinishDate(result.getDate("expected_finish_date"));
                trip.setRealFinishDate(result.getDate("real_finish_date"));
                trip.setExpectedFuelConsumption(result.getBigDecimal("expected_fuel_consuption"));
                trip.setDriverProfit(result.getBigDecimal("driver_profit"));
                trip.setExpenses(result.getBigDecimal("expenses"));
                trip.setWaybill(MySqlWaybillDao.getInstance().getById(result.getInt("id_waybill")));
                trip.setRoute(MySqlRouteDao.getInstance().getById(result.getInt("id_route")));
                trip.setCar(MySqlCarDao.getInstance().getById(result.getInt("id_car")));
                trip.setDriver(MySqlUserDao.getInstance().getById(result.getInt("id_driver")));
                collection.add(trip);
            }
        } catch (SQLException | NamingException e) {
            throw new DaoException("Can't get trips without fuel.", e);
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
        return collection;
    }

    @Override
    public void update(Trip trip) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_UPDATE);
            statement.setInt(1, getIndexStatus(trip.getTripStatus()));
            statement.setDate(2, new java.sql.Date(trip.getStartDate().getTime()));
            statement.setDate(3, new java.sql.Date(trip.getExpectedFinishDate().getTime()));
            statement.setDate(4, new java.sql.Date(trip.getRealFinishDate().getTime()));
            statement.setBigDecimal(5, trip.getRealFuelConsumption());
            statement.setBigDecimal(6, trip.getExpectedFuelConsumption());
            statement.setBigDecimal(7, trip.getDriverProfit());
            statement.setBigDecimal(8, trip.getExpenses());
            statement.setInt(9, trip.getWaybill().getId());
            statement.setInt(10, trip.getRoute().getId());
            statement.setInt(11, trip.getCar().getId());
            statement.setInt(12, trip.getDriver().getId());
            statement.setInt(13, trip.getId());
            statement.executeUpdate();
        } catch (NamingException|SQLException e) {
            DatabaseUtils.rollback(connection);
            throw new DaoException("Can't update this trip", e);
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
    }

    @Override
    public void delete(Trip trip) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_DELETE);
            statement.setInt(1, trip.getId());
            statement.executeUpdate();
        } catch (NamingException |SQLException e) {
            DatabaseUtils.rollback(connection);
            throw new DaoException("Can't remove this trip.", e);
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }

    }

    @Override
    public void add(Trip trip) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_ADD);

            statement.setInt(1, 1);
            statement.setDate(2, new java.sql.Date(trip.getStartDate().getTime()));
            statement.setDate(3, new java.sql.Date(trip.getExpectedFinishDate().getTime()));
            statement.setBigDecimal(4, trip.getExpectedFuelConsumption());
            statement.setBigDecimal(5, trip.getDriverProfit());
            statement.setBigDecimal(6, trip.getExpenses());
            statement.setInt(7, trip.getWaybill().getId());
            statement.setInt(8, trip.getRoute().getId());
            statement.setInt(9, trip.getCar().getId());
            statement.setInt(10, trip.getDriver().getId());
            statement.executeUpdate();

        } catch (NamingException|SQLException e) {
            DatabaseUtils.rollback(connection);
            throw new DaoException("can't add this trip", e);
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
    }

    @Override
    public List<String> getAllTripsStatuses() throws DaoException {
        Connection connection = null;
        Statement statement = null;
        String name;
        List<String> collection = new ArrayList<>();
        try {
            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.createStatement();

            ResultSet result = statement.executeQuery(SQL_GET_ALL_STATUSES);

            while (result.next()) {
                name = result.getString("name");
                collection.add(name);
            }
        } catch (SQLException | NamingException e) {
            throw new DaoException("Can't get all trip statuses.", e);
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
        return collection;
    }

    private Integer getIndexStatus(String name) throws SQLException, NamingException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_GET_STATUS);
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id_trip_status");
            }
            statement = connection.prepareStatement(SQL_ADD_STATUS);
            statement.setString(1, name);
            statement.executeUpdate();
            statement = connection.prepareStatement(SQL_GET_STATUS);
            statement.setString(1, name);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id_trip_status");
            }
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
        return 0;
    };
}


package by.bsuir.mpp.transpony.dao.impl.mysql;

import by.bsuir.mpp.transpony.dao.DaoException;
import by.bsuir.mpp.transpony.dao.CarDao;
import by.bsuir.mpp.transpony.entity.Car;
import by.bsuir.mpp.transpony.util.DatabaseUtils;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlCarDao implements CarDao {
    private static final MySqlCarDao instance = new MySqlCarDao();
    private MySqlCarDao() {}
    public static MySqlCarDao getInstance() {
        return instance;
    }

	private static final String SQL_GET_FREE_CARS = "SELECT  cr.id_car as id, cr.license_plate as license_plate,\n" +
		"vc.name as vendor, mc.name as model, cr.fuel_consumption as fuel_consumption, ct.name as car_type\n" +
		"FROM CAR cr\n" +
		"LEFT JOIN VENDOR_CAR vc ON cr.id_vendor_car = vc.id_vendor_car\n" +
		"LEFT JOIN MODEL_CAR mc ON cr.id_model_car = mc.id_model_car\n" +
		"LEFT JOIN CAR_TYPE ct ON cr.id_car_type = ct.id_car_type\n" +
		"WHERE cr.id_car not in (SELECT tr.id_car FROM TRIP tr WHERE tr.id_trip_status = 2)";
	private static final String SQL_ADD_CAR = "INSERT INTO CAR (license_plate, id_vendor_car, id_model_car, " +
		"id_car_type, fuel_consumption) VALUES (?, ?, ?, ?, ?)";
	private static final String SQL_DELETE_CAR = "DELETE FROM CAR WHERE id_car = ?";
	private static final String SQL_UPDATE_CAR = "UPDATE CAR SET license_plate = ?, id_vendor_car = ?,\n" +
		"id_model_car = ?, id_car_type = ?, fuel_consumption = ? WHERE id_car = ?";
	private static final String SQL_GET_CAR_BY_ID = "SELECT  cr.id_car as id, cr.license_plate as license_plate,\n" +
			"vc.name as vendor, mc.name as model, cr.fuel_consumption as fuel_consumption, ct.name as car_type\n" +
			"FROM CAR cr\n" +
			"LEFT JOIN VENDOR_CAR vc ON cr.id_vendor_car = vc.id_vendor_car\n" +
			"LEFT JOIN MODEL_CAR mc ON cr.id_model_car = mc.id_model_car\n" +
			"LEFT JOIN CAR_TYPE ct ON cr.id_car_type = ct.id_car_type\n" +
			"WHERE cr.id_car = ?";
	private static final String SQL_GET_CAR_TYPE = "SELECT id_car_type FROM CAR_TYPE WHERE name = ?";
	private static final String SQL_ADD_CAR_TYPE = "INSERT INTO CAR_TYPE (name) VALUES (?)";
	private static final String SQL_GET_MODEL = "SELECT id_model_car FROM MODEL_CAR WHERE name = ?";
	private static final String SQL_ADD_MODEL = "INSERT INTO MODEL_CAR (name) VALUES (?)";
	private static final String SQL_GET_VENDOR = "SELECT id_vendor_car FROM VENDOR_CAR WHERE name = ?";
	private static final String SQL_ADD_VENDOR = "INSERT INTO VENDOR_CAR (name) VALUES (?)";

	@Override
    public List<Car> getFreeCars() throws DaoException {
        Connection connection = null;
        Statement statement = null;
        Car car;
        List<Car> collection = new ArrayList<>();
        try {
            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery(SQL_GET_FREE_CARS);

            while (result.next()) {
                car = new Car();
                car.setId(result.getInt("id"));
                car.setLicensePlate(result.getString("license_plate"));
                car.setVendor(result.getString("vendor"));
                car.setModel(result.getString("model"));
                car.setType(result.getString("car_type"));
                car.setFuelConsumption(result.getBigDecimal("fuel_consumption"));
                collection.add(car);
            }
        } catch (SQLException | NamingException e) {
            DatabaseUtils.rollback(connection);
            throw new DaoException("Can't get all free cars.", e);
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
        return collection;
    }

    @Override
    public void add(Car car) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_ADD_CAR);
            statement.setString(1, car.getLicensePlate());
            statement.setInt(2, getVendorId(car.getVendor()));
            statement.setInt(3, getModelId(car.getModel()));
            statement.setInt(4, getTypeId(car.getType()));
            statement.setBigDecimal(5, car.getFuelConsumption());
            statement.executeUpdate();
        } catch (NamingException|SQLException e) {
            DatabaseUtils.rollback(connection);
            throw new DaoException("Can't add this car.", e);
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
    }

    @Override
    public void delete(Car car) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_DELETE_CAR);
            statement.setInt(1, car.getId());
            statement.executeUpdate();
        } catch (NamingException|SQLException e) {
            DatabaseUtils.rollback(connection);
            throw new DaoException("Can't remove this car.", e);
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
    }

    @Override
    public void update(Car car) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_UPDATE_CAR);
            statement.setString(1, car.getLicensePlate());
            statement.setInt(2, getVendorId(car.getVendor()));
            statement.setInt(3, getModelId(car.getModel()));
            statement.setInt(4, getTypeId(car.getType()));
            statement.setBigDecimal(5, car.getFuelConsumption());
            statement.setInt(6, car.getId());
            statement.executeUpdate();
        } catch (NamingException|SQLException e) {
            DatabaseUtils.rollback(connection);
            throw new DaoException("Can't update this car.", e);
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
    }

    @Override
    public Car getById(Integer carId) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        Car car = new Car();
        try {
            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_GET_CAR_BY_ID);
            statement.setInt(1, carId);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                car.setId(result.getInt("id"));
                car.setLicensePlate(result.getString("license_plate"));
                car.setVendor(result.getString("vendor"));
                car.setModel(result.getString("model"));
                car.setType(result.getString("car_type"));
                car.setFuelConsumption(result.getBigDecimal("fuel_consumption"));
            }
        } catch (SQLException | NamingException e) {
            DatabaseUtils.rollback(connection);
            throw new DaoException("Can't get this car.", e);
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
        return car;

    }

    private Integer getTypeId(String name) throws SQLException, NamingException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_GET_CAR_TYPE);
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id_car_type");
            }
            // if Car Type with such name does not exist it is created
            statement = connection.prepareStatement(SQL_ADD_CAR_TYPE);
            statement.setString(1, name);
            statement.executeUpdate();
            statement = connection.prepareStatement(SQL_GET_CAR_TYPE);
            statement.setString(1, name);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id_car_type");
            }
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
        return 0;
    }

    private Integer getModelId(String name) throws SQLException, NamingException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_GET_MODEL);
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id_model_car");
            }
			// if Car Model with such name does not exist it is created
            statement = connection.prepareStatement(SQL_ADD_MODEL);
            statement.setString(1, name);
            statement.executeUpdate();
            statement = connection.prepareStatement(SQL_GET_MODEL);
            statement.setString(1, name);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id_model_car");
            }
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
        return 0;
    }

    private Integer getVendorId(String name) throws SQLException, NamingException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_GET_VENDOR);
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id_vendor_car");
            }
			// if Car Vendor with such name does not exist it is created
            statement = connection.prepareStatement(SQL_ADD_VENDOR);
            statement.setString(1, name);
            statement.executeUpdate();
            statement = connection.prepareStatement(SQL_GET_VENDOR);
            statement.setString(1, name);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id_vendor_car");
            }
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
        return 0;
    }
}
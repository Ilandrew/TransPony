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

    @Override
    public List<Car> getFreeCars() throws DaoException {
        Connection connection = null;
        Statement statement = null;
        Car car;
        List<Car> collection = new ArrayList<>();
        try {
            connection = DatabaseUtils.getConnection();
            statement = connection.createStatement();

            ResultSet result = statement.executeQuery("SELECT  cr.id_car as id,\n" +
                    "        cr.license_plate as license_plate,\n" +
                    "        vc.name as vendor,\n" +
                    "        mc.name as model,\n" +
                    "        cr.fuel_consumption as fuel_consumption,\n" +
                    "        ct.name as car_type\n" +
                    "FROM CAR cr\n" +
                    "LEFT JOIN VENDOR_CAR vc ON cr.id_vendor_car = vc.id_vendor_car\n" +
                    "LEFT JOIN MODEL_CAR mc ON cr.id_model_car = mc.id_model_car\n" +
                    "LEFT JOIN CAR_TYPE ct ON cr.id_car_type = ct.id_car_type\n" +
                    "WHERE cr.id_car not in (SELECT tr.id_car FROM TRIP tr WHERE tr.id_trip_status = 2)");

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
            throw new DaoException("can't get all car", e);
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
        return collection;
    }

    @Override
    public void addNewCar(Car car) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseUtils.getConnection();
            statement = connection.prepareStatement("INSERT INTO CAR (license_plate, id_vendor_car, id_model_car, id_car_type, fuel_consumption) VALUES (?, ?, ?, ?, ?)");
            statement.setString(1, car.getLicensePlate());
            statement.setInt(2, getIndexVendor(car.getVendor()));
            statement.setInt(3, getIndexModel(car.getModel()));
            statement.setInt(4, getIndexType(car.getType()));
            statement.setBigDecimal(5, car.getFuelConsumption());
            statement.executeUpdate();
            DatabaseUtils.commit();
        } catch (NamingException|SQLException e) {
            throw new DaoException("can't add this car", e);
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
    }

    @Override
    public void deleteCar(Car car) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseUtils.getConnection();
            statement = connection.prepareStatement("DELETE FROM CAR WHERE id_car = ?");
            statement.setInt(1, car.getId());
            statement.executeUpdate();
            DatabaseUtils.commit();
        } catch (NamingException|SQLException e) {
            throw new DaoException("Can't remove this car", e);
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
    }

    @Override
    public void updateCar(Car car) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseUtils.getConnection();
            statement = connection.prepareStatement("UPDATE CAR SET\n" +
                    "        license_plate = ?,\n" +
                    "        id_vendor_car = ?,\n" +
                    "        id_model_car = ?,\n" +
                    "        id_car_type = ?,\n" +
                    "        fuel_consumption = ?\n" +
                    "WHERE id_car = ?");
            statement.setString(1, car.getLicensePlate());
            statement.setInt(2, getIndexVendor(car.getVendor()));
            statement.setInt(3, getIndexModel(car.getModel()));
            statement.setInt(4, getIndexType(car.getType()));
            statement.setBigDecimal(5, car.getFuelConsumption());
            statement.setInt(6, car.getId());
            statement.executeUpdate();
            DatabaseUtils.commit();
        } catch (NamingException|SQLException e) {
            throw new DaoException("Can't update this car", e);
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }

    }

    @Override
    public Car getForIndex(Integer cadId) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        Car car = new Car();
        try {
            connection = DatabaseUtils.getConnection();
            statement = connection.prepareStatement("SELECT  cr.id_car as id,\n" +
                    "        cr.license_plate as license_plate,\n" +
                    "        vc.name as vendor,\n" +
                    "        mc.name as model,\n" +
                    "        cr.fuel_consumption as fuel_consumption,\n" +
                    "        ct.name as car_type\n" +
                    "FROM CAR cr\n" +
                    "LEFT JOIN VENDOR_CAR vc ON cr.id_vendor_car = vc.id_vendor_car\n" +
                    "LEFT JOIN MODEL_CAR mc ON cr.id_model_car = mc.id_model_car\n" +
                    "LEFT JOIN CAR_TYPE ct ON cr.id_car_type = ct.id_car_type\n" +
                    "WHERE cr.id_car = ?");
            statement.setInt(1, cadId);

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
            throw new DaoException("can't get tris car", e);
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
        return car;

    }

    private Integer getIndexType(String name) throws SQLException, NamingException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseUtils.getConnection();
            statement = connection.prepareStatement("SELECT id_car_type FROM CAR_TYPE WHERE name = ?");
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id_car_type");
            }
            statement = connection.prepareStatement("INSERT INTO CAR_TYPE (name) VALUES (?)");
            statement.setString(1, name);
            statement.executeUpdate();
            DatabaseUtils.commit();
            statement = connection.prepareStatement("SELECT id_car_type FROM CAR_TYPE WHERE name = ?");
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

    private Integer getIndexModel(String name) throws SQLException, NamingException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseUtils.getConnection();
            statement = connection.prepareStatement("SELECT id_model_car FROM MODEL_CAR WHERE name = ?");
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id_model_car");
            }
            statement = connection.prepareStatement("INSERT INTO MODEL_CAR (name) VALUES (?)");
            statement.setString(1, name);
            statement.executeUpdate();
            DatabaseUtils.commit();
            statement = connection.prepareStatement("SELECT id_model_car FROM MODEL_CAR WHERE name = ?");
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

    private Integer getIndexVendor(String name) throws SQLException, NamingException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseUtils.getConnection();
            statement = connection.prepareStatement("SELECT id_vendor_car FROM VENDOR_CAR WHERE name = ?");
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id_vendor_car");
            }
            statement = connection.prepareStatement("INSERT INTO VENDOR_CAR (name) VALUES (?)");
            statement.setString(1, name);
            statement.executeUpdate();
            DatabaseUtils.commit();
            statement = connection.prepareStatement("SELECT id_vendor_car FROM VENDOR_CAR WHERE name = ?");
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
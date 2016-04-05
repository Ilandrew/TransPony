package by.bsuir.mpp.transpony.dao.impl.mysql;

import by.bsuir.mpp.transpony.dao.DaoExeption;
import by.bsuir.mpp.transpony.dao.ICar;
import by.bsuir.mpp.transpony.entity.Car;
import by.bsuir.mpp.transpony.util.DatabaseUtils;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlCar implements ICar {

    @Override
    public List<Car> getFreeCars() throws DaoExeption {
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
            throw new DaoExeption();
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }

        return collection;
    }

    @Override
    public void addNewCar(Car car) {

    }

    @Override
    public void deleteCar(Car car) {

    }

    @Override
    public void updateCar(Car car) {

    }

    private Integer getIndexType(String name) {
        Connection connection;
        PreparedStatement statement;
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
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }

        return 0;
    }

    private Integer getIndexModel(String name) {
        Connection connection;
        PreparedStatement statement;
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
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private Integer getIndexVendor(String name) {
        Connection connection;
        PreparedStatement statement;
        try {
            connection = DatabaseUtils.getConnection();
            statement = connection.prepareStatement("SELECT id_vendor_type FROM VENDOR_CAR WHERE name = ?");
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id_vendor_type");
            }
            statement = connection.prepareStatement("INSERT INTO VENDOR_CAR (name) VALUES (?)");
            statement.setString(1, name);
            statement.executeUpdate();
            DatabaseUtils.commit();
            statement = connection.prepareStatement("SELECT id_vendor_type FROM VENDOR_CAR WHERE name = ?");
            statement.setString(1, name);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id_vendor_type");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return 0;
    }

}

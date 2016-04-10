package by.bsuir.mpp.transpony.dao.impl.mysql;

import by.bsuir.mpp.transpony.dao.DaoException;
import by.bsuir.mpp.transpony.dao.IDeliveryPoint;
import by.bsuir.mpp.transpony.entity.DeliveryPoint;
import by.bsuir.mpp.transpony.entity.Receiver;
import by.bsuir.mpp.transpony.util.DatabaseUtils;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlDeliveryPoint implements IDeliveryPoint {

    private static final MySqlDeliveryPoint instance = new MySqlDeliveryPoint();
    private MySqlDeliveryPoint() {};
    public static MySqlDeliveryPoint getInstance() {
        return instance;
    }
    @Override
    public DeliveryPoint getForIndex(Integer index) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        DeliveryPoint deliveryPoint = new DeliveryPoint();
        try {
            connection = DatabaseUtils.getConnection();
            statement = connection.prepareStatement("SELECT address FROM DELIVERY_POINT WHERE id_provider = ?");
            statement.setInt(1, index);

            ResultSet result = statement.executeQuery();

            deliveryPoint.setId(index);
            deliveryPoint.setAddress(result.getString("address"));
        } catch (SQLException | NamingException e) {
            throw new DaoException("can't get provider for index", e);
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }

        return deliveryPoint;
    }

    @Override
    public List<DeliveryPoint> getAllForReceiver(Receiver receiver) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        DeliveryPoint deliveryPoint;
        List<DeliveryPoint> collection = new ArrayList<>();
        try {
            connection = DatabaseUtils.getConnection();
            statement = connection.prepareStatement("SELECT dp.id_delivery_point AS id, dp.address AS address\n" +
                    "FROM DELIVERY_POINT dp\n" +
                    "LEFT JOIN M2M_RECIEVER_DELIVERY_POINT rp ON dp.id_delivery_point = rp.id_delivery_point\n" +
                    "WHERE rp.id_reciever = ?");
            statement.setInt(1, receiver.getId());


            ResultSet result = statement.executeQuery();

            while (result.next()) {
                deliveryPoint = new DeliveryPoint();
                deliveryPoint.setId(result.getInt("id"));
                deliveryPoint.setAddress(result.getString("address"));
            }
        } catch (SQLException | NamingException e) {
            throw new DaoException("can't get for receiver", e);
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
        return collection;
    }

    @Override
    public Integer add(DeliveryPoint deliveryPoint) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        Integer index = 0;
        try {
            connection = DatabaseUtils.getConnection();
            statement = connection.prepareStatement("INSERT INTO DELIVERY_POINT (address) VALUES (?)");
            statement.setString(1, deliveryPoint.getAddress());
            statement.executeUpdate();
            DatabaseUtils.commit();

            statement = connection.prepareStatement("SELECT max(id_delivery_point) as id\n" +
                    "FROM DELIVERY_POINT\n" +
                    "WHERE address = ?\n");
            statement.setString(1, deliveryPoint.getAddress());
            ResultSet resultSet = statement.executeQuery();
            index = resultSet.getInt("id");
        } catch (NamingException|SQLException e) {
            throw new DaoException("can't add this provider", e);
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
        return index;
    }

    @Override
    public void update(DeliveryPoint deliveryPoint) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseUtils.getConnection();
            statement = connection.prepareStatement("UPDATE DELIVERY_POINT SET\n" +
                    "        address = ?\n" +
                    "WHERE id_delivery_point = ?");
            statement.setString(1, deliveryPoint.getAddress());
            statement.setInt(2, deliveryPoint.getId());
            statement.executeUpdate();
            DatabaseUtils.commit();
        } catch (NamingException |SQLException e) {
            throw new DaoException("Can't update this delivery point", e);
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
    }

    @Override
    public void delete(DeliveryPoint deliveryPoint) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseUtils.getConnection();
            statement = connection.prepareStatement("DELETE FROM DELIVERY_POINT WHERE id_delivery_point = ?");
            statement.setInt(1, deliveryPoint.getId());
            statement.executeUpdate();
            DatabaseUtils.commit();
        } catch (NamingException|SQLException e) {
            throw new DaoException("Can't remove this delivery point", e);
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
    }
}

package by.bsuir.mpp.transpony.dao.impl.mysql;

import by.bsuir.mpp.transpony.dao.DaoException;
import by.bsuir.mpp.transpony.dao.IReceiver;
import by.bsuir.mpp.transpony.entity.DeliveryPoint;
import by.bsuir.mpp.transpony.entity.Receiver;
import by.bsuir.mpp.transpony.util.DatabaseUtils;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlReceiver implements IReceiver {


    private static final MySqlReceiver instance = new MySqlReceiver();
    private MySqlReceiver() {}
    public static MySqlReceiver getInstance() {
        return instance;
    }

    @Override
    public List<Receiver> getAll() throws DaoException {
        Connection connection = null;
        Statement statement = null;
        Receiver receiver;
        List<Receiver> collection = new ArrayList<>();
        try {
            connection = DatabaseUtils.getConnection();
            statement = connection.createStatement();

            ResultSet result = statement.executeQuery("SELECT id_reciever, name, phone, address, email FROM RECIEVER");

            while (result.next()) {
                receiver = new Receiver();
                receiver.setId(result.getInt("id_reciever"));
                receiver.setName(result.getString("name"));
                receiver.setPhone(result.getString("phone"));
                receiver.setAddress(result.getString("address"));
                receiver.setEmail(result.getString("email"));
                collection.add(receiver);
            }
        } catch (SQLException | NamingException e) {
            throw new DaoException("can't get all receiver", e);
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
        return collection;
    }

    @Override
    public Integer add(Receiver receiver) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        Integer index = 0;
        try {
            connection = DatabaseUtils.getConnection();
            statement = connection.prepareStatement("INSERT INTO RECIEVER (name, phone, address, email) VALUES (?, ?, ?, ?)");
            statement.setString(1, receiver.getName());
            statement.setString(2, receiver.getPhone());
            statement.setString(3, receiver.getAddress());
            statement.setString(4, receiver.getEmail());
            statement.executeUpdate();
            DatabaseUtils.commit();

            statement = connection.prepareStatement("SELECT max(id_reciever) as id\n" +
                    "FROM RECIEVER\n" +
                    "WHERE name = ?\n" +
                    "      AND phone = ?\n" +
                    "      AND address = ?\n" +
                    "      AND email = ?");
            statement.setString(1, receiver.getName());
            statement.setString(2, receiver.getPhone());
            statement.setString(3, receiver.getAddress());
            statement.setString(4, receiver.getEmail());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                index = resultSet.getInt("id");
            }
        } catch (NamingException|SQLException e) {
            throw new DaoException("can't add this receiver", e);
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
        return index;
    }

    @Override
    public void delete(Receiver receiver) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseUtils.getConnection();
            statement = connection.prepareStatement("DELETE FROM RECIEVER WHERE id_reciever = ?");
            statement.setInt(1, receiver.getId());
            statement.executeUpdate();
            DatabaseUtils.commit();
        } catch (NamingException |SQLException e) {
            throw new DaoException("Can't remove this receiver", e);
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
    }

    @Override
    public void update(Receiver receiver) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseUtils.getConnection();
            statement = connection.prepareStatement("UPDATE RECIEVER SET\n" +
                    "        name = ?,\n" +
                    "        address = ?,\n" +
                    "        phone = ?,\n" +
                    "        email = ?\n" +
                    "WHERE id_reciever = ?");
            statement.setString(1, receiver.getName());
            statement.setString(2, receiver.getAddress());
            statement.setString(3, receiver.getPhone());
            statement.setString(4, receiver.getEmail());
            statement.setInt(5, receiver.getId());
            statement.executeUpdate();
            DatabaseUtils.commit();
        } catch (NamingException|SQLException e) {
            throw new DaoException("Can't update this receiver", e);
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }

    }

    @Override
    public List<DeliveryPoint> getDeliveryPointForReceiver(Integer index) throws DaoException {
        return MySqlDeliveryPoint.getInstance().getAllForReceiver(index);
    }

    @Override
    public Receiver getForIndex(Integer index) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        Receiver receiver = new Receiver();
        try {
            connection = DatabaseUtils.getConnection();
            statement = connection.prepareStatement("SELECT id_reciever, name, phone, address, email FROM RECIEVER WHERE id_reciever = ?");
            statement.setInt(1, index);

            ResultSet result = statement.executeQuery();
            if (result.next()) {
                receiver.setId(result.getInt("id_reciever"));
                receiver.setName(result.getString("name"));
                receiver.setPhone(result.getString("phone"));
                receiver.setAddress(result.getString("address"));
                receiver.setEmail(result.getString("email"));
            }

        } catch (SQLException | NamingException e) {
            throw new DaoException("can't get receiver for index", e);
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
        return receiver;
    }
}

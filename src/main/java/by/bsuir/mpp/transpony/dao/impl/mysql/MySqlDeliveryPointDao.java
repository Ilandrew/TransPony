package by.bsuir.mpp.transpony.dao.impl.mysql;

import by.bsuir.mpp.transpony.dao.DaoException;
import by.bsuir.mpp.transpony.dao.DeliveryPointDao;
import by.bsuir.mpp.transpony.entity.DeliveryPoint;
import by.bsuir.mpp.transpony.util.DatabaseUtils;
import org.omg.CORBA.PRIVATE_MEMBER;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlDeliveryPointDao implements DeliveryPointDao {

    private static final MySqlDeliveryPointDao instance = new MySqlDeliveryPointDao();
    private MySqlDeliveryPointDao() {}
    public static MySqlDeliveryPointDao getInstance() {
        return instance;
    }

    private static final String SQL_GET_BY_ID = "SELECT address FROM DELIVERY_POINT WHERE id_delivery_point = ?";
	private static final String SQL_GET_BY_RECEIVER_ID = "SELECT dp.id_delivery_point AS id, dp.address AS address\n" +
			"FROM DELIVERY_POINT dp\n" +
			"LEFT JOIN M2M_RECIEVER_DELIVERY_POINT rp ON dp.id_delivery_point = rp.id_delivery_point\n" +
			"WHERE rp.id_reciever = ?";
    private static final String SQL_ADD_DELIVERY_POINT = "INSERT INTO DELIVERY_POINT (address) VALUES (?)";
	private static final String SQL_MAX_DELIVERY_POINT_ID = "SELECT max(id_delivery_point) as id\n" +
			"FROM DELIVERY_POINT\n" +
			"WHERE address = ?\n";
	private static final String SQL_UPDATE = "UPDATE DELIVERY_POINT SET\n" +
			"        address = ?\n" +
			"WHERE id_delivery_point = ?";
	private static final String SQL_DELETE = "DELETE FROM DELIVERY_POINT WHERE id_delivery_point = ?";

    @Override
    public DeliveryPoint getById(Integer id) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        DeliveryPoint deliveryPoint = new DeliveryPoint();
        try {
            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_GET_BY_ID);
            statement.setInt(1, id);

            ResultSet result = statement.executeQuery();
            if (result.next()) {
                deliveryPoint.setId(id);
                deliveryPoint.setAddress(result.getString("address"));
            }
        } catch (SQLException | NamingException e) {
            throw new DaoException("Can't get delivery point id.", e);
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }

        return deliveryPoint;
    }

    @Override
    public List<DeliveryPoint> getByReceiverId(Integer receiverId) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        DeliveryPoint deliveryPoint;
        List<DeliveryPoint> collection = new ArrayList<>();
        try {
            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_GET_BY_RECEIVER_ID);
            statement.setInt(1, receiverId);

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                deliveryPoint = new DeliveryPoint();
                deliveryPoint.setId(result.getInt("id"));
                deliveryPoint.setAddress(result.getString("address"));
            }
        } catch (SQLException | NamingException e) {
            throw new DaoException("Can't get delivery points for this receiver.", e);
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
            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_ADD_DELIVERY_POINT);
            statement.setString(1, deliveryPoint.getAddress());
            statement.executeUpdate();
            statement = connection.prepareStatement(SQL_MAX_DELIVERY_POINT_ID);
            statement.setString(1, deliveryPoint.getAddress());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                index = resultSet.getInt("id");
            }
        } catch (NamingException|SQLException e) {
            DatabaseUtils.rollback(connection);
            throw new DaoException("Can't add this provider.", e);
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
            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_UPDATE);
            statement.setString(1, deliveryPoint.getAddress());
            statement.setInt(2, deliveryPoint.getId());
            statement.executeUpdate();

        } catch (NamingException |SQLException e) {
            DatabaseUtils.rollback(connection);
            throw new DaoException("Can't update this delivery point.", e);
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
            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_DELETE);
            statement.setInt(1, deliveryPoint.getId());
            statement.executeUpdate();
        } catch (NamingException|SQLException e) {
            DatabaseUtils.rollback(connection);
            throw new DaoException("Can't remove this delivery point.", e);
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
    }
}

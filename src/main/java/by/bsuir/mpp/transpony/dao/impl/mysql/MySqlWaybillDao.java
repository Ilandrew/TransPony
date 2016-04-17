package by.bsuir.mpp.transpony.dao.impl.mysql;

import by.bsuir.mpp.transpony.dao.DaoException;
import by.bsuir.mpp.transpony.dao.WaybillDao;
import by.bsuir.mpp.transpony.entity.Waybill;
import by.bsuir.mpp.transpony.util.DatabaseUtils;

import javax.naming.NamingException;
import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class MySqlWaybillDao implements WaybillDao {

    private static final MySqlWaybillDao instance = new MySqlWaybillDao();
    private MySqlWaybillDao() {}
    public static MySqlWaybillDao getInstance() {
        return instance;
    }

	private static final String SQL_GET_ALL = "SELECT id_waybill, id_cargo, profit, id_reciever, id_delivery_point FROM WAYBILL";
	private static final String SQL_GET_BY_ID = "SELECT id_waybill, id_cargo, profit, id_reciever, id_delivery_point FROM WAYBILL WHERE id_waybill = ?";
	private static final String SQL_DELETE = "DELETE FROM WAYBILL WHERE id_waybill = ?";
	private static final String SQL_UPDATE = "UPDATE WAYBILL SET\n" +
			"        id_cargo = ?,\n" +
			"        profit = ?,\n" +
			"        id_reciever = ?,\n" +
			"        id_delivery_point = ?\n" +
			"WHERE id_waybill = ?";
	private static final String SQL_ADD = "INSERT INTO WAYBILL (id_cargo, profit, id_reciever, id_delivery_point) VALUES (?, ?, ?, ?)";

    @Override
    public List<Waybill> getAll() throws DaoException {
        Connection connection = null;
        Statement statement = null;
        Waybill waybill;
        List<Waybill> collection = new ArrayList<>();
        try {
            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.createStatement();

            ResultSet result = statement.executeQuery(SQL_GET_ALL);

            while (result.next()) {
                waybill = new Waybill();
                waybill.setId(result.getInt("id_waybill"));
                waybill.setCargo(MySqlCargoDao.getInstance().getById(result.getInt("id_cargo")));
                waybill.setProfit(result.getBigDecimal("profit"));
                waybill.setReceiver(MySqlReceiverDao.getInstance().getById(result.getInt("id_reciever")));
                waybill.setDeliveryPoint(MySqlDeliveryPointDao.getInstance().getById(result.getInt("id_delivery_point")));
                collection.add(waybill);
            }
        } catch (SQLException | NamingException e) {
            throw new DaoException("Can't get all waybills.", e);
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
        return collection;
    }

    @Override
    public Waybill getById(Integer index) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        Waybill waybill = new Waybill();
        try {
            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_GET_BY_ID);
            statement.setInt(1, index);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                waybill.setId(result.getInt(index));
                waybill.setCargo(MySqlCargoDao.getInstance().getById(result.getInt("id_cargo")));
                waybill.setProfit(result.getBigDecimal("profit"));
                waybill.setReceiver(MySqlReceiverDao.getInstance().getById(result.getInt("id_reciever")));
                waybill.setDeliveryPoint(MySqlDeliveryPointDao.getInstance().getById(result.getInt("id_delivery_point")));
            }
        } catch (SQLException | NamingException e) {
            throw new DaoException("Can't get this waybill.", e);
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
        return waybill;
    }

    @Override
    public void delete(Waybill waybill) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_DELETE);
            statement.setInt(1, waybill.getId());
            statement.executeUpdate();
        } catch (NamingException |SQLException e) {
            DatabaseUtils.rollback(connection);
            throw new DaoException("Can't remove this waybill.", e);
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
    }

    @Override
    public void update(Waybill waybill) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_UPDATE);
            statement.setInt(1, waybill.getCargo().getId());
            statement.setBigDecimal(2, waybill.getProfit());
            statement.setInt(3, waybill.getReceiver().getId());
            statement.setInt(4, waybill.getDeliveryPoint().getId());
            statement.setInt(5, waybill.getId());
            statement.executeUpdate();
        } catch (NamingException|SQLException e) {
            DatabaseUtils.rollback(connection);
            throw new DaoException("Can't update this waybill.", e);
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
    }

    @Override
    public void add(Waybill waybill) throws DaoException {

        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_ADD);
            statement.setInt(1, waybill.getCargo().getId());
            statement.setBigDecimal(2, waybill.getProfit());
            statement.setInt(3, waybill.getReceiver().getId());
            statement.setInt(4, waybill.getDeliveryPoint().getId());
            statement.executeUpdate();

        } catch (NamingException|SQLException e) {
            DatabaseUtils.rollback(connection);
            throw new DaoException("Can't add this waybill.", e);
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
    }

}

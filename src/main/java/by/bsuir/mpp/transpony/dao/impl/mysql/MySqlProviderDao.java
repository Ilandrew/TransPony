package by.bsuir.mpp.transpony.dao.impl.mysql;

import by.bsuir.mpp.transpony.dao.DaoException;
import by.bsuir.mpp.transpony.dao.ProviderDao;
import by.bsuir.mpp.transpony.entity.Provider;
import by.bsuir.mpp.transpony.util.DatabaseUtils;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlProviderDao implements ProviderDao {

    private static final MySqlProviderDao instance = new MySqlProviderDao();
    private MySqlProviderDao() {}
    public static MySqlProviderDao getInstance() {
        return instance;
    }

    private static final String SQL_GET_BY_ID = "SELECT id_provider, name, phone, address, email FROM PROVIDER WHERE id_provider = ?";
	private static final String SQL_GET_ALL = "SELECT id_provider, name, phone, address, email FROM PROVIDER";
	private static final String SQL_ADD = "INSERT INTO PROVIDER (name, phone, address, email) VALUES (?, ?, ?, ?)";
	private static final String SQL_GET_MAX_ID = "SELECT max(id_provider) as id\n" +
			"FROM PROVIDER\n" +
			"WHERE name = ?\n" +
			"      AND phone = ?\n" +
			"      AND address = ?\n" +
			"      AND email = ?";
	private static final String SQL_DELETE = "DELETE FROM PROVIDER WHERE id_provider = ?";
	private static final String SQL_UDPATE = "UPDATE PROVIDER SET\n" +
			"        name = ?,\n" +
			"        address = ?,\n" +
			"        phone = ?,\n" +
			"        email = ?\n" +
			"WHERE id_provider = ?";

    @Override
    public Provider getById(Integer index) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        Provider provider = new Provider();
        try {
            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_GET_BY_ID);
            statement.setInt(1, index);

            ResultSet result = statement.executeQuery();
            if (result.next()) {
                provider.setId(index);
                provider.setName(result.getString("name"));
                provider.setPhone(result.getString("phone"));
                provider.setAddress(result.getString("address"));
                provider.setEmail(result.getString("email"));
            }
        } catch (SQLException | NamingException e) {
            DatabaseUtils.rollback(connection);
            throw new DaoException("Can't get provider for index.", e);
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
        return provider;
    }

    @Override
    public List<Provider> getAll() throws DaoException {
        Connection connection = null;
        Statement statement = null;
        Provider provider;
        List<Provider> collection = new ArrayList<>();
        try {
            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.createStatement();

            ResultSet result = statement.executeQuery(SQL_GET_ALL);

            while (result.next()) {
                provider = new Provider();
                provider.setId(result.getInt("id_provider"));
                provider.setName(result.getString("name"));
                provider.setPhone(result.getString("phone"));
                provider.setAddress(result.getString("address"));
                provider.setEmail(result.getString("email"));
                collection.add(provider);
            }
        } catch (SQLException | NamingException e) {
            DatabaseUtils.rollback(connection);
            throw new DaoException("Can't get all provider.", e);
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
        return collection;
    }

    @Override
    public Integer add(Provider provider) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        Integer index = 0;
        try {
            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_ADD);
            statement.setString(1, provider.getName());
            statement.setString(2, provider.getPhone());
            statement.setString(3, provider.getAddress());
            statement.setString(4, provider.getEmail());
            statement.executeUpdate();

            statement = connection.prepareStatement(SQL_GET_MAX_ID);
            statement.setString(1, provider.getName());
            statement.setString(2, provider.getPhone());
            statement.setString(3, provider.getAddress());
            statement.setString(4, provider.getEmail());
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
    public void delete(Provider provider) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_DELETE);
            statement.setInt(1, provider.getId());
            statement.executeUpdate();
        } catch (NamingException|SQLException e) {
            DatabaseUtils.rollback(connection);
            throw new DaoException("Can't remove this provider.", e);
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
    }

    @Override
    public void update(Provider provider) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_UDPATE);
            statement.setString(1, provider.getName());
            statement.setString(2, provider.getAddress());
            statement.setString(3, provider.getPhone());
            statement.setString(4, provider.getEmail());
            statement.setInt(5, provider.getId());
            statement.executeUpdate();
        } catch (NamingException|SQLException e) {
            DatabaseUtils.rollback(connection);
            throw new DaoException("Can't update this provider.", e);
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
    }
}

package by.bsuir.mpp.transpony.dao.impl.mysql;

import by.bsuir.mpp.transpony.dao.DaoException;
import by.bsuir.mpp.transpony.dao.CargoDao;
import by.bsuir.mpp.transpony.entity.Cargo;
import by.bsuir.mpp.transpony.util.DatabaseUtils;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlCargoDao implements CargoDao {

    private static final MySqlCargoDao instance = new MySqlCargoDao();
    private MySqlCargoDao() {}
    public static MySqlCargoDao getInstance() {
        return instance;
    }

    @Override
    public List<Cargo> getAll() throws DaoException {
        Connection connection = null;
        Statement statement = null;
        Cargo cargo;
        List<Cargo> collection = new ArrayList<>();
        try {
            connection = DatabaseUtils.getConnection();
            statement = connection.createStatement();

            ResultSet result = statement.executeQuery("SELECT co.id_cargo AS id_cargo,\n" +
                    "        co.name AS name_cargo,\n" +
                    "        co.price AS price_cargo,\n" +
                    "        co.weight AS weight_cargo,\n" +
                    "        co.volume AS volume_cargo,\n" +
                    "        ct.name AS name_type,\n" +
                    "        co.id_provider AS id_provider\n" +
                    "FROM CARGO co\n" +
                    "LEFT JOIN CARGO_TYPE ct ON co.id_cargo_type = ct.id_cargo_type");

            while (result.next()) {
                cargo = new Cargo();
                cargo.setId(result.getInt("id_cargo"));
                cargo.setName(result.getString("name_cargo"));
                cargo.setPrise(result.getBigDecimal("price_cargo"));
                cargo.setWeight(result.getInt("weight_cargo"));
                cargo.setVolume(result.getInt("volume_cargo"));
                cargo.setCargoType(result.getString("name_type"));
                cargo.setProvider(MySqlProviderDao.getInstance().getForIndex(result.getInt("id_provider")));

                collection.add(cargo);
            }
        } catch (SQLException | NamingException e) {
            throw new DaoException("can't get all cargo", e);
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
        return collection;
    }

    @Override
    public Cargo getForIndex(Integer index) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        Cargo cargo = new Cargo();
        try {
            connection = DatabaseUtils.getConnection();
            statement = connection.prepareStatement("SELECT co.id_cargo AS id_cargo,\n" +
                    "        co.name AS name_cargo,\n" +
                    "        co.price AS price_cargo,\n" +
                    "        co.weight AS weight_cargo,\n" +
                    "        co.volume AS volume_cargo,\n" +
                    "        ct.name AS name_type,\n" +
                    "        co.id_provider AS id_provider\n" +
                    "FROM CARGO co\n" +
                    "LEFT JOIN CARGO_TYPE ct ON co.id_cargo_type = ct.id_cargo_type\n" +
                    "WHERE id_cargo = ?");
            statement.setInt(1, index);

            ResultSet result = statement.executeQuery();
            if (result.next()) {
                cargo.setId(index);
                cargo.setName(result.getString("name_cargo"));
                cargo.setPrise(result.getBigDecimal("price_cargo"));
                cargo.setWeight(result.getInt("weight_cargo"));
                cargo.setVolume(result.getInt("volume_cargo"));
                cargo.setCargoType(result.getString("name_type"));
                cargo.setProvider(MySqlProviderDao.getInstance().getForIndex(result.getInt("id_provider")));
            }

        } catch (SQLException | NamingException e) {
            throw new DaoException("can't get all cargo", e);
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }

        return cargo;
    }

    @Override
    public Integer add(Cargo cargo) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        Integer index = 0;
        try {
            connection = DatabaseUtils.getConnection();
            statement = connection.prepareStatement("INSERT INTO CARGO (name, weight, volume, id_cargo_type, id_provider, price) VALUES (?, ?, ?, ?, ?, ?)");
            statement.setString(1, cargo.getName());
            statement.setInt(2, cargo.getWeight());
            statement.setInt(3, cargo.getVolume());
            statement.setInt(4, getIndexType(cargo.getCargoType()));
            statement.setInt(5, cargo.getProvider().getId());
            statement.setBigDecimal(6, cargo.getPrise());
            statement.executeUpdate();
            DatabaseUtils.commit();

            statement = connection.prepareStatement("SELECT max(id_cargo) as id\n" +
                    "FROM CARGO\n" +
                    "WHERE name = ?\n" +
                    "      AND weight = ?\n" +
                    "      AND volume = ?\n" +
                    "      AND id_cargo_type = ?\n" +
                    "      AND id_provider = ?\n" +
                    "      AND price = ?");
            statement.setString(1, cargo.getName());
            statement.setInt(2, cargo.getWeight());
            statement.setInt(3, cargo.getVolume());
            statement.setInt(4, getIndexType(cargo.getCargoType()));
            statement.setInt(5, cargo.getProvider().getId());
            statement.setBigDecimal(6, cargo.getPrise());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                index = resultSet.getInt("id");
            }
        } catch (NamingException|SQLException e) {
            throw new DaoException("can't add this cargo", e);
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
        return index;
    }

    @Override
    public void delete(Cargo cargo) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseUtils.getConnection();
            statement = connection.prepareStatement("DELETE FROM CARGO WHERE id_cargo = ?");
            statement.setInt(1, cargo.getId());
            statement.executeUpdate();
            DatabaseUtils.commit();
        } catch (NamingException|SQLException e) {
            throw new DaoException("Can't remove this cargo", e);
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
    }

    @Override
    public void update(Cargo cargo) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseUtils.getConnection();
            statement = connection.prepareStatement("UPDATE CARGO SET\n" +
                    "        name = ?,\n" +
                    "        weight = ?,\n" +
                    "        volume = ?,\n" +
                    "        id_cargo_type = ?,\n" +
                    "        id_provider = ?,\n" +
                    "        price = ?\n" +
                    "WHERE id_cargo = ?");
            statement.setString(1, cargo.getName());
            statement.setInt(2, cargo.getWeight());
            statement.setInt(3, cargo.getVolume());
            statement.setInt(4, getIndexType(cargo.getCargoType()));
            statement.setInt(5, cargo.getProvider().getId());
            statement.setBigDecimal(6, cargo.getPrise());
            statement.setInt(7, cargo.getId());

            statement.executeUpdate();
            DatabaseUtils.commit();
        } catch (NamingException|SQLException e) {
            throw new DaoException("Can't update this provider", e);
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
    }

    @Override
    public List<String> getAllType() throws DaoException {
        Connection connection = null;
        Statement statement = null;
        String name;
        List<String> collection = new ArrayList<>();
        try {
            connection = DatabaseUtils.getConnection();
            statement = connection.createStatement();

            ResultSet result = statement.executeQuery("SELECT name FROM CARGO_TYPE");

            while (result.next()) {
                name = result.getString("name");
                collection.add(name);
            }
        } catch (SQLException | NamingException e) {
            throw new DaoException("can't get all cargo type", e);
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
        return collection;
    }

    private Integer getIndexType(String name) throws SQLException, NamingException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseUtils.getConnection();
            statement = connection.prepareStatement("SELECT id_cargo_type FROM CARGO_TYPE WHERE name = ?");
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id_cargo_type");
            }
            statement = connection.prepareStatement("INSERT INTO CARGO_TYPE (name) VALUES (?)");
            statement.setString(1, name);
            statement.executeUpdate();
            DatabaseUtils.commit();
            statement = connection.prepareStatement("SELECT id_cargo_type FROM CARGO_TYPE WHERE name = ?");
            statement.setString(1, name);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id_cargo_type");
            }
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
        return 0;
    }

}

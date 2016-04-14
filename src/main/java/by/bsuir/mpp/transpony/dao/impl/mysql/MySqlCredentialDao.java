package by.bsuir.mpp.transpony.dao.impl.mysql;

import by.bsuir.mpp.transpony.dao.CredentialDao;
import by.bsuir.mpp.transpony.dao.DaoException;
import by.bsuir.mpp.transpony.util.DatabaseUtils;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySqlCredentialDao implements CredentialDao {
    private static final MySqlCredentialDao instance = new MySqlCredentialDao();
    private MySqlCredentialDao() {}
    public static MySqlCredentialDao getInstance() {
        return instance;
    }


    @Override
    public Integer getForCredential(String login, String pass) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.prepareStatement("SELECT ee.id_employee as id FROM EMPLOYEE ee\n" +
                    "WHERE ee.id_user_credantials in (SELECT id_user_credantials \n" +
                    "                                 FROM USER_CREDANTIALS uc \n" +
                    "                                 WHERE uc.login = ? \n" +
                    "                                 AND uc.password = ?)");
            statement.setString(1, login);
            statement.setString(2, pass);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                return result.getInt("id");
            }
        } catch (SQLException | NamingException e) {
            throw new DaoException("can't get user for credential", e);
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
        return 0;
    }
}

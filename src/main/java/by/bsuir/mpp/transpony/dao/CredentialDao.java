package by.bsuir.mpp.transpony.dao;

public interface CredentialDao {
    Integer getId(String login, String pass) throws DaoException;
}

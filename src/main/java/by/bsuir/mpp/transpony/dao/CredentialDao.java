package by.bsuir.mpp.transpony.dao;

public interface CredentialDao {

    Integer getForCredential(String login, String pass) throws DaoException;

}

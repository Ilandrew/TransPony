package by.bsuir.mpp.transpony.dao;

import by.bsuir.mpp.transpony.entity.Provider;

import java.util.List;

public interface ProviderDao {
    Provider getById(Integer index) throws DaoException;
    List<Provider> getAll() throws DaoException;
    Integer add(Provider provider) throws DaoException;
    void delete(Provider provider) throws DaoException;
    void update(Provider provider) throws DaoException;
}

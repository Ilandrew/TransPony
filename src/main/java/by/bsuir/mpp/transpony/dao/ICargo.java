package by.bsuir.mpp.transpony.dao;

import by.bsuir.mpp.transpony.entity.Cargo;

import java.util.List;

public interface ICargo {

    List<Cargo> getAll() throws DaoException;

    Integer add(Cargo cargo) throws DaoException;
    void delete(Cargo cargo) throws DaoException;
    void update(Cargo cargo) throws DaoException;

    Cargo getForIndex(Integer index) throws DaoException;

    List<String> getAllType() throws DaoException;
}

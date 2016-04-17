package by.bsuir.mpp.transpony.dao;

import by.bsuir.mpp.transpony.entity.Cargo;

import java.util.List;

public interface CargoDao {
    List<Cargo> getAll() throws DaoException;
    Integer add(Cargo cargo) throws DaoException;
    void delete(Cargo cargo) throws DaoException;
    void update(Cargo cargo) throws DaoException;
    Cargo getById(Integer id) throws DaoException;
    List<String> getAllTypes() throws DaoException;
}

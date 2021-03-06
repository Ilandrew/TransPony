package by.bsuir.mpp.transpony.dao;

import by.bsuir.mpp.transpony.entity.Waybill;

import java.util.List;

public interface WaybillDao {
    List<Waybill> getAll() throws DaoException;
    void delete(Waybill waybill) throws DaoException;
    void update(Waybill waybill) throws DaoException;
    void add(Waybill waybill) throws DaoException;
    Waybill getById(Integer index) throws DaoException;
}

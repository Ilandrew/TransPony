package by.bsuir.mpp.transpony.dao;

import by.bsuir.mpp.transpony.entity.Waybill;

import java.util.List;

public interface IWaybill {

    List<Waybill> getAll();

    void delete(Waybill waybill);
    void update(Waybill waybill);
    void add(Waybill waybill);
}

package by.bsuir.mpp.transpony.dao;


import by.bsuir.mpp.transpony.entity.CheckPoint;

import java.util.List;

public interface ICheckPoint {

    List<CheckPoint> getAll();

    void addNew(CheckPoint checkPoint);
    void delete(CheckPoint checkPoint);
    void update(CheckPoint checkPoint);



}

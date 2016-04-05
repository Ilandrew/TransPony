package by.bsuir.mpp.transpony.dao;

import by.bsuir.mpp.transpony.entity.Route;

import java.util.List;

public interface IRoute {

    List<Route> getAll();

    void update(Route route);
    void add(Route route);
    void delete(Route route);

}

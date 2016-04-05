package by.bsuir.mpp.transpony.dao;

import by.bsuir.mpp.transpony.entity.Trip;

import java.util.List;

public interface ITrip {

    List<Trip> getAll();

    void update(Trip trip);
    void delete(Trip trip);
    void add(Trip trip);

}

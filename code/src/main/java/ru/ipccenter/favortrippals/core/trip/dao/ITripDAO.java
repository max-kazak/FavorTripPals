package ru.ipccenter.favortrippals.core.trip.dao;

import java.util.List;

import ru.ipccenter.favortrippals.core.model.Trip;


public interface ITripDAO {


    public void addTrip(Trip trip);


    public void updateTrip(Trip trip);


    public void deleteTrip(Trip trip);


    public Trip getTripById(long id);

    public Trip getTripByTraveller(long traveller);

    public List<Trip> getTrips();
}

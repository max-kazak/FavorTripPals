package ru.ipccenter.favortrippals.core.trip.dao;

import java.util.List;

import ru.ipccenter.favortrippals.core.model.Trip;
import ru.ipccenter.favortrippals.core.model.User;


public interface ITripDAO {


    public void addTrip(Trip trip);


    public void updateTrip(Trip trip);


    public void deleteTrip(Trip trip);


    public Trip getTripById(long id);

    public List<Trip> getTripsByTraveller(User traveller);

    public List<Trip> getTrips();
}
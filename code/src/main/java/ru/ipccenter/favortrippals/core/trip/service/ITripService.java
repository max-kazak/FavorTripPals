package ru.ipccenter.favortrippals.core.trip.service;

import java.util.List;

import ru.ipccenter.favortrippals.core.model.Trip;

public interface ITripService {

    public void addTrip(Trip trip);


    public void updateTrip(Trip trip);


    public void deleteTrip(Trip trip);


    public Trip getTripById(long id);

    public Trip getTripByTraveller(long traveller);

    public List<Trip> getTrips();
}

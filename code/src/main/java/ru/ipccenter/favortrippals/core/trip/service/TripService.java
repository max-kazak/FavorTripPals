package ru.ipccenter.favortrippals.core.trip.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ru.ipccenter.favortrippals.core.model.Trip;
import ru.ipccenter.favortrippals.core.trip.dao.ITripDAO;


@Transactional(readOnly = true)
public class TripService implements ITripService{

    ITripDAO tripDAO;

    @Transactional(readOnly = false)
    @Override
    public void addTrip(Trip trip) {
        getTripDAO().addTrip(trip);
    }

    @Transactional(readOnly = false)
    @Override
    public void deleteTrip(Trip trip) {
        getTripDAO().deleteTrip(trip);
    }

    @Transactional(readOnly = false)
    @Override
    public void updateTrip(Trip trip) {
        getTripDAO().updateTrip(trip);
    }

    @Override
    public Trip getTripById(long id) {
        return getTripDAO().getTripById(id);
    }

    @Override
    public Trip getTripByTraveller(long traveller) {
        return getTripDAO().getTripByTraveller(traveller);
    }

    @Override
    public List<Trip> getTrips() {
        return getTripDAO().getTrips();
    }

    public ITripDAO getTripDAO() {
        return tripDAO;
    }

    public void setTripDAO(ITripDAO tripDAO) {
        this.tripDAO = tripDAO;
    }

}

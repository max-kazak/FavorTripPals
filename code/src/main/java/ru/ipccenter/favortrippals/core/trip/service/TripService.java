package ru.ipccenter.favortrippals.core.trip.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import ru.ipccenter.favortrippals.core.model.Trip;
import ru.ipccenter.favortrippals.core.model.User;
import ru.ipccenter.favortrippals.core.trip.dao.ITripDAO;
import ru.ipccenter.favortrippals.core.user.service.IUserService;

import javax.faces.bean.ManagedProperty;


@Transactional(readOnly = true)
public class TripService implements ITripService{

    private static final Logger log = Logger.getLogger(TripService.class.getName());

    ITripDAO tripDAO;
    IUserService userService;

    public TripService() {
        super();
        log.debug("TripService Created");
    }

    public IUserService getUserService() {
        return userService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    @Transactional(readOnly = false)
    @Override
    public void addTrip(Trip trip) {
        log.debug("TripService.addTrip called");
        log.debug("setting traveller");
        trip.setTraveller(userService.getCurrentUser());
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
    public List<Trip> getTripsByTraveller(User traveller) {
        return getTripDAO().getTripsByTraveller(traveller);
    }

    @Override
    public List<Trip> getTrips() {
        return getTripDAO().getTrips();
    }
    
    @Override
    public List<Trip> getUpcomingTrips(User user)
    {
        return getTripDAO().getUpcomingTrips(user);
    }

    public ITripDAO getTripDAO() {
        return tripDAO;
    }

    public void setTripDAO(ITripDAO tripDAO) {
        this.tripDAO = tripDAO;
    }

}

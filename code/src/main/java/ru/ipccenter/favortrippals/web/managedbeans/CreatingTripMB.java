package ru.ipccenter.favortrippals.web.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.springframework.dao.DataAccessException;

import ru.ipccenter.favortrippals.core.model.Trip;
import ru.ipccenter.favortrippals.core.trip.service.ITripService;

@ManagedBean(name="creatingTripMB")
@RequestScoped
public class CreatingTripMB implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final String SUCCESS = "success";
    private static final String ERROR   = "error";

    //Spring Trip Service is injected
    @ManagedProperty(value="#{TripService}")
    ITripService tripService;

    List<Trip> tripList;

    private long id;
    private String dest;
    private long traveler;
    private int capacity;
    private Calendar departure_date = Calendar.getInstance();
    private Calendar arrival_date = Calendar.getInstance();

    public String registerTrip() {
        try {
            Trip trip = new Trip();
            trip.setDest(getDest());
            trip.setDeparture_date(getDeparture_date());
            trip.setArrival_date(getArrival_date());
            trip.setCapacity(getCapacity());
            getTripService().addTrip(trip);
            return SUCCESS;
        }   catch (DataAccessException e) {
            System.out.println(e);
        }

        return ERROR;
    }

    public void reset() {
        this.setId(0);
        this.setDest("");
        this.setDeparture_date(Calendar.getInstance());
        this.setArrival_date(Calendar.getInstance());
        this.setCapacity(0);
    }

    public List<Trip> getTripList() {
        tripList = new ArrayList<Trip>();
        tripList.addAll(getTripService().getTrips());
        return tripList;
    }

    public void setTripList(List<Trip> tripList) {
        this.tripList = tripList;
    }

    public ITripService getTripService() {
        return tripService;
    }

    public void setTripService(ITripService tripService) {
        this.tripService = tripService;
    }

    public long getTraveler() {
        return traveler;
    }

    public void setTraveler(long traveler) {
        this.traveler = traveler;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Calendar getDeparture_date() {
        return departure_date;
    }

    public void setDeparture_date(Calendar departure_date) {
        this.departure_date = departure_date;
    }

    public Calendar getArrival_date() {
        return arrival_date;
    }

    public void setArrival_date(Calendar arrival_date) {
        this.arrival_date = arrival_date;
    }
}

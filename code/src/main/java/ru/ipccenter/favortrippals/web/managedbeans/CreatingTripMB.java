package ru.ipccenter.favortrippals.web.managedbeans;
/**
 * @author Vasili
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.apache.log4j.Logger;
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
    @ManagedProperty(value="#{tripService}")
    ITripService tripService;

    List<Trip> tripList;

    private long id;
    private String dest;
    private long traveler;
    private Integer capacity;
    private Calendar departure_date = Calendar.getInstance();
    private Calendar arrival_date = Calendar.getInstance();

    private static final Logger log = Logger.getLogger(CreatingTripMB.class.getName());

    public CreatingTripMB() {
        super();
        log.debug("CreatingTripMB created");
    }

    public String registerTrip() {
        try {
            Trip trip = new Trip();
            trip.setDest(getDest());
            trip.setDeparture_date(getDeparture_cal());
            trip.setArrival_date(getArrival_cal());
            trip.setCapacity(getCapacity());
            getTripService().addTrip(trip);

            log.debug("trip created!");

            return SUCCESS;
        }   catch (DataAccessException e) {
            System.out.println(e);
        }

        return ERROR;
    }

    public void reset() {
        this.setId(0);
        this.setDest("");
        this.setDeparture_date(new Date());
        this.setArrival_date(new Date());
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

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Date getDeparture_date() {
        return departure_date.getTime();
    }

    public void setDeparture_date(Date d_departure_date) {
        this.departure_date.setTime(d_departure_date);
    }

    public Calendar getDeparture_cal() {
        return departure_date;
    }

    public void setDeparture_cal(Calendar departure_date) {
        this.departure_date = departure_date;
    }

    public Date getArrival_date() {
        return arrival_date.getTime();
    }

    public void setArrival_date(Date d_arrival_date) {
        this.arrival_date.setTime(d_arrival_date);
    }

    public Calendar getArrival_cal() {
        return arrival_date;
    }

    public void setArrival_cal(Calendar arrival_date) {
        this.arrival_date = arrival_date;
    }
}

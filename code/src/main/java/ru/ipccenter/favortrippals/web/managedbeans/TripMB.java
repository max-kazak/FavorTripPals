package ru.ipccenter.favortrippals.web.managedbeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Calendar;

import ru.ipccenter.favortrippals.core.model.Trip;
import ru.ipccenter.favortrippals.core.model.User;
import ru.ipccenter.favortrippals.core.trip.service.ITripService;

@ManagedBean(name="tripMB")
@SessionScoped
public class TripMB {

    @ManagedProperty(value ="#(TripService")
    ITripService tripService;

    private Trip trip;

    public ITripService getTripService() {
        return tripService;
    }

    public void setTripService(ITripService tripService) {
        this.tripService = tripService;
    }

    private void checkActuality() {
        String actualId = SecurityContextHolder.getContext().getAuthentication().getName();
        if( (trip==null) || ( !actualId.equals(""+trip.getId())) )
        {
            trip = tripService.getTripById(Long.parseLong(actualId));
        }
    }

    public long getId() {
        checkActuality();
        return trip.getId();
    }

    public String getDest() {
        checkActuality();
        return trip.getDest();
    }

    public Integer getCapacity() {
        checkActuality();
        return trip.getCapacity();
    }

    public User getTraveller() {
        checkActuality();
        return trip.getTraveller();
    }

    public Calendar getDeparture_date() {
        checkActuality();
        return trip.getDeparture_date();
    }

    public Calendar getArrival_date() {
        checkActuality();
        return trip.getArrival_date();
    }

    @Override
    public String toString() {
        checkActuality();
        return trip.toString();
    }


}

package ru.ipccenter.favortrippals.web.managedbeans;

import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;

import ru.ipccenter.favortrippals.core.model.Trip;
import ru.ipccenter.favortrippals.core.model.User;
import ru.ipccenter.favortrippals.core.trip.service.ITripService;
import ru.ipccenter.favortrippals.core.user.service.IUserService;

@ManagedBean(name="tripMB")
@SessionScoped
public class TripMB {
 
    @ManagedProperty (value="#{userService}")
    IUserService userService;

    @ManagedProperty(value ="#{tripService}")
    ITripService tripService;

    private Trip trip;

    public ITripService getTripService() {
        return tripService;
    }

    public void setTripService(ITripService tripService) {
        this.tripService = tripService;
    }
    
    public IUserService getUserService() {
        return userService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    private void checkActuality() {
        if ((getUserService() == null)||(getUserService().getCurrentUser() == null))
        {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            String redirect = "index";
            NavigationHandler myNav = facesContext.getApplication().getNavigationHandler();
            myNav.handleNavigation(facesContext, null, redirect);
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
    
    public List<Trip> getTripsByCurrentUser() {
        checkActuality();
        List<Trip> list = getTripService().getTripsByTraveller(getUserService().getCurrentUser());
        return list;
    }
    
    public List<Trip> getTripsByUser() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> map = context.getExternalContext().getRequestParameterMap();
        if (!map.containsKey("userId"))
            return new ArrayList<>();
        long userId = Long.parseLong(map.get("userId"));
        checkActuality();
        List<Trip> list = getTripService().getTripsByTraveller(getUserService().getUserById(userId));
        return list;
    }

    @Override
    public String toString() {
        checkActuality();
        if (trip == null) return "no trip";
        return trip.toString();
    }
    
    public List<Trip> getUpcomingTrips()
    {
        return getTripService().getUpcomingTrips(getUserService().getCurrentUser());
    }
}

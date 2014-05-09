package ru.ipccenter.favortrippals.core.trip.dao;

import java.io.CharArrayWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import org.hibernate.SessionFactory;

import ru.ipccenter.favortrippals.core.model.Trip;
import ru.ipccenter.favortrippals.core.model.User;


public class TripDAO implements ITripDAO {

    private SessionFactory sessionFactory;


    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }


    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public void addTrip(Trip trip) {
        long id;
        do{
            id = UUID.randomUUID().getLeastSignificantBits();
            id *= Long.signum(id);
        } while (getTripById(id)!=null);
        trip.setId(id);
        getSessionFactory().getCurrentSession().save(trip);
    }


    @Override
    public void deleteTrip(Trip trip) {
        getSessionFactory().getCurrentSession().delete(trip);
    }


    @Override
    public void updateTrip(Trip trip) {
        getSessionFactory().getCurrentSession().update(trip);
    }


    @Override
    public Trip getTripById(long id) {
        String query = "from Trip where id=?";
        List list = getSessionFactory().getCurrentSession()
                .createQuery(query)
                .setParameter(0, id)
                .list();
        if(!list.isEmpty())
            return (Trip)list.get(0);
        else return null;
    }


    @Override
    public List<Trip> getTrips() {
        String query = "from Trip";
        List list = getSessionFactory().getCurrentSession()
                .createQuery(query)
                .list();
        return (List<Trip>)list;
    }


    @Override
    public List<Trip> getTripsByTraveller(User traveller) {
        Logger.getLogger(TripDAO.class.getName()).log(Level.INFO, "\n" + traveller.getId());
        String strQuery = "from Trip where traveller=?";
        Session session = getSessionFactory().getCurrentSession();
        Query query = session.createQuery(strQuery);
        query.setParameter(0, traveller);
        List list = query.list();
        return (List<Trip>)list;
    }
}

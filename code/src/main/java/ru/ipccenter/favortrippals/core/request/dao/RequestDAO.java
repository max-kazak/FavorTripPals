package ru.ipccenter.favortrippals.core.request.dao;
/**
 *
 * @author Anton
 */
import java.util.List;
import org.hibernate.SessionFactory;
import ru.ipccenter.favortrippals.core.model.Goods;
import ru.ipccenter.favortrippals.core.model.Request;
import ru.ipccenter.favortrippals.core.model.User;
import ru.ipccenter.favortrippals.core.model.Trip;

public class RequestDAO implements IRequestDAO
{
    private SessionFactory sessionFactory;
    
    public SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }
    
    @Override
    public void addRequest(Request request)
    {
        getSessionFactory().getCurrentSession().save(request);
    }
    
    @Override
    public void deleteRequest(Request request)
    {
        getSessionFactory().getCurrentSession().delete(request);
    }
    
    @Override
    public void updateRequest(Request request)
    {
        getSessionFactory().getCurrentSession().update(request);
    }
    
    @Override
    public List<Request> getAllRequestsByCustomer(User customer)
    {
        String query = "from Request where customer=?";
        List list = getSessionFactory().getCurrentSession().createQuery(query)
				.setParameter(0, customer.getId()).list();
        return (List<Request>)list;
    }
    
    @Override
    public List<Request> getAllRequestsByTrip(Trip trip)
    {
        String query = "from Request where trip=?";
        List list = getSessionFactory().getCurrentSession().createQuery(query)
				.setParameter(0, trip.getId()).list();
        return (List<Request>)list;
    }
    
    @Override
    public List<Request> getAllRequestsByGoods(Goods goods)
    {
        String query = "from Request where goods=?";
        List list = getSessionFactory().getCurrentSession().createQuery(query)
				.setParameter(0, goods.getId()).list();
        return (List<Request>)list;
    }
}

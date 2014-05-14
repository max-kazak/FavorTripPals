package ru.ipccenter.favortrippals.core.request.dao;
/**
 *
 * @author Anton
 */
import java.util.List;
import ru.ipccenter.favortrippals.core.model.Goods;
import ru.ipccenter.favortrippals.core.model.Request;
import ru.ipccenter.favortrippals.core.model.User;
import ru.ipccenter.favortrippals.core.model.Trip;

public interface IRequestDAO 
{
    public void addRequest(Request request);
    public void deleteRequest(Request request);
    public void updateRequest(Request request);
    public List<Request> getAllRequestsByCustomer(User customer);
    public List<Request> getAllRequestsByTrip(Trip trip);
    /**
     * Only one type of goods is meant
     * @param goods
     * @return
     */
    public List<Request> getAllRequestsByGoods(Goods goods);
    public Request getRequestByAllIds(User customer, Trip trip, Goods goods);
    public List<Request> getAllRequestsByUser(User user);
    public List<Request> getAllRequestsByTraveller(User traveller);
}

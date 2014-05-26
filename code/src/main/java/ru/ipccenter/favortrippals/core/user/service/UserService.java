package ru.ipccenter.favortrippals.core.user.service;

import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;
import org.springframework.orm.hibernate4.HibernateTemplate;

import org.springframework.transaction.annotation.Transactional;
import ru.ipccenter.favortrippals.core.friendship.dao.IFriendshipDAO;
import ru.ipccenter.favortrippals.core.model.Friendship;
import ru.ipccenter.favortrippals.core.model.Request;
import ru.ipccenter.favortrippals.core.model.SocialConnection;
import ru.ipccenter.favortrippals.core.model.Trip;

import ru.ipccenter.favortrippals.core.model.User;
import ru.ipccenter.favortrippals.core.request.dao.IRequestDAO;
import ru.ipccenter.favortrippals.core.socialconnection.dao.ISocialConnectionDAO;
import ru.ipccenter.favortrippals.core.trip.dao.ITripDAO;
import ru.ipccenter.favortrippals.core.user.dao.IUserDAO;



@Transactional(readOnly = true)
public class UserService implements IUserService
{
    User currentUser;
    IUserDAO userDAO;
    ISocialConnectionDAO socialConnectionDAO;
    IFriendshipDAO friendshipDAO;
    IRequestDAO requestDAO;
    ITripDAO tripDAO;

    public IFriendshipDAO getFriendshipDAO()
    {
        return friendshipDAO;
    }

    public void setFriendshipDAO(IFriendshipDAO friendshipDAO)
    {
        this.friendshipDAO = friendshipDAO;
    }

    public IRequestDAO getRequestDAO()
    {
        return requestDAO;
    }

    public void setRequestDAO(IRequestDAO requestDAO)
    {
        this.requestDAO = requestDAO;
    }

    public ITripDAO getTripDAO()
    {
        return tripDAO;
    }

    public void setTripDAO(ITripDAO tripDAO)
    {
        this.tripDAO = tripDAO;
    }

    public ISocialConnectionDAO getSocialConnectionDAO()
    {
        return socialConnectionDAO;
    }

    public void setSocialConnectionDAO(ISocialConnectionDAO socialConnectionDAO)
    {
        this.socialConnectionDAO = socialConnectionDAO;
    }
    
    public IUserDAO getUserDAO ()
    {
        return userDAO;
    }

    public void setUserDAO (IUserDAO userDAO)
    {
        this.userDAO = userDAO;
    }

    @Override
    public User getCurrentUser ()
    {
        return currentUser;
    }
    
    @Override
    public void setCurrentUser (User currentUser)
    {
        this.currentUser = currentUser;
    }
    
    @Transactional(readOnly = false)
    @Override
    public void addUser(User user)
    {
        getUserDAO().addUser(user);
    }

    @Transactional(readOnly = false)
    @Override
    public void deleteUserData(User user)
    {
        List<Friendship> allFriendshipsByUser = getFriendshipDAO().getAllFriendshipsByUser(user);
        for (Friendship friend : allFriendshipsByUser)
            getFriendshipDAO().deleteFriendship(friend);
        List<Request> allRequestsByUser = getRequestDAO().getAllRequestsByUser(user);
        for (Request request : allRequestsByUser)
            getRequestDAO().deleteRequest(request);
        List<SocialConnection> allConnectionsByUser = getSocialConnectionDAO().getAllConnectionsByUser(user);
        for (SocialConnection connection : allConnectionsByUser)
            getSocialConnectionDAO().deleteConnection(connection);
        List<Trip> tripsByTraveller = getTripDAO().getTripsByTraveller(user);
        for (Trip trip : tripsByTraveller)
        {
            List<Request> allRequestsByTrip = getRequestDAO().getAllRequestsByTrip(trip);
            for (Request request : allRequestsByTrip)
                getRequestDAO().deleteRequest(request);
            getTripDAO().deleteTrip(trip);
        }
        getSocialConnectionDAO().deleteUserconnections(user);
    }
    
    @Transactional(readOnly = false)
    @Override
    public void deleteUserOnly (User user)
    {
        getUserDAO().deleteUser(user);
    }

    @Transactional(readOnly = false)
    @Override
    public void updateUser(User user)
    {
        getUserDAO().updateUser(user);
    }
    
    @Override
    public User getUserById(long id)
    {
        return getUserDAO().getUserById(id);
    }
    
    @Override
    public User getUserByEmail(String email)
    {
        return getUserDAO().getUserByEmail(email);
    }

    @Override
    public List<User> getUsers()
    {
        return getUserDAO().getUsers();
    }
    
    /**
     * See ISocialConnectionDAO.getConnectionByProviderUserId
     */
    @Override
    public User getUserByProviderUserId(String provider, String providerUserId)
    {
        SocialConnection sConnection = getSocialConnectionDAO().getConnectionByProviderUserId(provider, providerUserId);
        if (sConnection == null)
            return null;
        User user = getUserDAO().getUserById(sConnection.getUser().getId());
        return user;
    }
    
    @Override
    public String getUrlOfSmallPicture(long id)
    {
        return getUserDAO().getUrlOfSmallPicture(id);
    }
    
    static public void removeCurrent ()
    {
        if (FacesContext.getCurrentInstance() != null)
        {
            Map m = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
            for (Object o : m.keySet())
                m.put(o,null);
        }
    }
}

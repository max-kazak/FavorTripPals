package ru.ipccenter.favortrippals.core.socialconnection.dao;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.ipccenter.favortrippals.core.model.SocialConnection;
import ru.ipccenter.favortrippals.core.model.User;

/**
 * @author Ddiimmaann
 */
public class SocialConnectionDAO implements ISocialConnectionDAO
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
    public void addConnection(SocialConnection connection)
    {
        getSessionFactory().getCurrentSession().save(connection);
    }

    @Override
    public void deleteConnection(SocialConnection connection)
    {
        getSessionFactory().getCurrentSession().delete(connection);
    }

    @Override
    public SocialConnection getConnectionByUserAndType(User user, int networkType)
    {
        String stringQuery = "from SocialConnection where user=:user and networkType=:type";
        Query query = getSessionFactory().getCurrentSession().createQuery(stringQuery);
        query.setParameter("user", user);
        query.setInteger("type", networkType);
        List list = query.list();
        if (list.isEmpty())
            return null;
        return (SocialConnection)list.get(0);
    }

    @Override
    public List<SocialConnection> getAllConnectionsByUser(User user)
    {
        String query = "from SocialConnection where user=?";
        List list = getSessionFactory().getCurrentSession().createQuery(query)
				.setParameter(0, user).list();
        return (List<SocialConnection>)list;
    }

    @Override
    public SocialConnection getConnectionByProviderUserId(String provider, String providerUserId)
    {
        String stringQuery = "from SocialConnection where providerUserId=:id"
                + " and networkType=:type";
        Session session = getSessionFactory().getCurrentSession();
        Query query = session.createQuery(stringQuery);
        query.setParameter("id", providerUserId);
        int networkType = SocialConnection.getNetworkTypeByString(provider);
        query.setInteger("type", networkType);
        List list = query.list();
        if (list.isEmpty())
            return null;
        return (SocialConnection)list.get(0);
    }

    @Override
    public void deleteUserconnections(User user)
    {
        String query = "delete from Userconnection where userid=" + user.getId();
        getSessionFactory().getCurrentSession().createSQLQuery(query).executeUpdate();
    }
}

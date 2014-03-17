package ru.ipccenter.favortrippals.core.user.dao;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import ru.ipccenter.favortrippals.core.model.SocialConnections;
import ru.ipccenter.favortrippals.core.model.User;

/**
 * @author Ddiimmaann
 */
public class SocialConnectionsDAO implements ISocialConnectionsDAO
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
    public void addConnection(SocialConnections connection)
    {
        if (connection == null)
            connection = new SocialConnections();
        if (connection.getUser() == null)                    /// Костыль!!!
        {
            User user = new User();
            user.setId(666);
            connection.setUser(user);
        }
        if (connection.getUserPage() == null)
            connection.setUserPage("I'm not cool(((");
        getSessionFactory().getCurrentSession().save(connection);
    }

    @Override
    public void deleteConnection(SocialConnections connection)
    {
        getSessionFactory().getCurrentSession().delete(connection);
    }

    @Override
    public SocialConnections getConnectionByUserAndType(User user, int networkType) {
        String stringQuery = "from SocialConnections where user=:user and networkType=:type";
        Query query = getSessionFactory().getCurrentSession().createQuery(stringQuery);
        query.setParameter("user", user);
        query.setInteger("type", networkType);
        List list = query.list();
        if (list.isEmpty())
            return null;
        return (SocialConnections)list.get(0);
    }

    @Override
    public List<SocialConnections> getAllConnectionsByUser(User user) {
        String query = "from SocialConnections where user=?";
        List list = getSessionFactory().getCurrentSession().createQuery(query)
				.setParameter(0, user.getId()).list();
        return (List<SocialConnections>)list;
    }
    
}

package ru.ipccenter.favortrippals.core.user.dao;

import java.io.CharArrayWriter;
import java.io.PrintWriter;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.ipccenter.favortrippals.core.model.SocialConnection;

import ru.ipccenter.favortrippals.core.model.User;


public class UserDAO implements IUserDAO
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
    public void addUser(User user)
    {
        if (user.getId() == null)
            user.setId(idGenerator());
        getSessionFactory().getCurrentSession().save(user);
    }
    
    @Override
    public void deleteUser(User user) 
    {
        getSessionFactory().getCurrentSession().delete(user);
    }


    @Override
    public void updateUser(User user)
    {
        getSessionFactory().getCurrentSession().update(user);
    }

    @Override
    public User getUserById(long id)
    {
        String query = "from User where id=?";
        Session session = getSessionFactory().getCurrentSession();
        List list = session.createQuery(query).setParameter(0, id).list();		
        if(!list.isEmpty())
            return (User)list.get(0);
        return null;
    }
    
    @Override
    public List<User> getUsers()
    {
        String query = "from User";
        List list = getSessionFactory().getCurrentSession()
                    .createQuery(query)
                    .list();
        return list;
    }
    
    @Override
    public User getUserByEmail(String email)
    {
        String query = "from User where email=?";
        List list = getSessionFactory().getCurrentSession()
				.createQuery(query)
				.setParameter(0, email)
				.list();
        if(!list.isEmpty())
            return (User)list.get(0);
        else return null;
    }

    private Long idGenerator ()
    {
        Long id;
        do
        {
            id = UUID.randomUUID().getLeastSignificantBits();
            id *= Long.signum(id);
        }
        while (getUserById(id)!=null);
        return id;
    }
}

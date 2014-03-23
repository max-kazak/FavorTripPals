package ru.ipccenter.favortrippals.core.user.dao;

import java.util.List;
import java.util.UUID;

import org.hibernate.SessionFactory;

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
        long id;
        do
        {
            id = UUID.randomUUID().getLeastSignificantBits();
            id *= Long.signum(id);
        }
        while (getUserById(id)!=null);
        user.setId(id);
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
        List list = getSessionFactory().getCurrentSession()
				.createQuery(query)
				.setParameter(0, id)
				.list();
        if(!list.isEmpty())
            return (User)list.get(0);
        else return null;
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
}

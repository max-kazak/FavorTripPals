package ru.ipccenter.favortrippals.core.friendship.dao;
/**
 *
 * @author Anton
 */
import java.util.ArrayList;
import java.util.List;
import org.hibernate.SessionFactory;
import ru.ipccenter.favortrippals.core.model.Friendship;
import ru.ipccenter.favortrippals.core.model.User;

public class FriendshipDAO implements IFriendshipDAO
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
    public void addFriendship(Friendship friendship)
    {
        if (isFriendshipExists(friendship))
            return;
        Friendship revers = new Friendship();
        revers.setUser1(friendship.getUser2());
        revers.setUser2(friendship.getUser1());
        getSessionFactory().getCurrentSession().save(friendship);
        getSessionFactory().getCurrentSession().save(revers);
    }
    
    @Override
    public void deleteFriendship(Friendship friendship)
    {
        getSessionFactory().getCurrentSession().delete(friendship);
    }
    
    @Override
    public void updateFriendship(Friendship friendship)
    {
        getSessionFactory().getCurrentSession().update(friendship);
    }
    
    @Override
    public List<Friendship> getAllFriendshipsByUser(User user)
    {
        String query1 = "from Friendship where user1=?";
        List list1 = getSessionFactory().getCurrentSession().createQuery(query1)
				.setParameter(0, user).list();
        String query2 = "from Friendship where user2=?";
        List list2 = getSessionFactory().getCurrentSession().createQuery(query2)
				.setParameter(0, user).list();
        List<Friendship> list = new ArrayList<>();
        list.addAll(list1);
        list.addAll(list2);
        return (List<Friendship>)list;
    }

    @Override
    public boolean isFriendshipExists(Friendship friendship)
    {
        String query = "from Friendship where user1=? and user2=?";
        List list = getSessionFactory().getCurrentSession().createQuery(query)
                .setParameter(0, friendship.getUser1()).setParameter(1, friendship.getUser2()).list();
        return !list.isEmpty();
    }
}

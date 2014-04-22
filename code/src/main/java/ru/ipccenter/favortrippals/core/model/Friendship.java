package ru.ipccenter.favortrippals.core.model;
/**
 *
 * @author Anton
 */
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import ru.ipccenter.favortrippals.core.model.Friendship.FriendshipPK;

@Entity
@IdClass(FriendshipPK.class)
@Table(name="FRIENDSHIPS")
public class Friendship implements Serializable
{
    public class FriendshipPK implements Serializable 
    {
        protected User user1;
        protected User user2;

        public FriendshipPK() {}
        
        public FriendshipPK(User user1, User user2) 
        {
            this.user1 = user1;
            this.user2 = user2;
        }
        
        public User getUser1()
        {
            return user1;
        }
        
        public User getUser2()
        {
            return user2;
        }
        
        @Override
        public boolean equals(Object o) 
        {
            if(o instanceof FriendshipPK)
            {
                FriendshipPK f = (FriendshipPK)o;
                if ((f.getUser1().getId() == user1.getId()) && 
                        (f.getUser2().getId() == user2.getId()))
                return true;
            }
        return false;
    }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 97 * hash + Objects.hashCode(this.user1);
            hash = 97 * hash + Objects.hashCode(this.user2);
            return hash;
        }
    }
    
    @Id
    @ManyToOne
    @JoinColumn(name = "USER1", unique = false, nullable = false)
    private User user1;
    @Id
    @ManyToOne
    @JoinColumn(name = "USER2", unique = false, nullable = false)
    private User user2;
    
    public void setUser1 (User user1)
    {
        this.user1 = user1;
    }
    
    public void setUser2 (User user2)
    {
        this.user2 = user2;
    }
    
    public User getUser1 ()
    {
        return user1;
    }
    
    public User getUser2 ()
    {
        return user2;
    }
}

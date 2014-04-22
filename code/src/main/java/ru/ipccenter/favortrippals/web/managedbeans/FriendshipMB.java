package ru.ipccenter.favortrippals.web.managedbeans;
/**
 *
 * @author Anton
 */
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import ru.ipccenter.favortrippals.core.friendship.service.IFriendshipService;
import ru.ipccenter.favortrippals.core.model.Friendship;
import ru.ipccenter.favortrippals.core.model.User;

@ManagedBean(name="friendshipMB")
@SessionScoped
public class FriendshipMB 
{
    @ManagedProperty(value ="#(FriendshipService")
    IFriendshipService friendshipService;
    
    private Friendship friendship;
    
    public IFriendshipService getFriendshipService() 
    {
        return friendshipService;
    }

    public void setFriendshipService(IFriendshipService tripService) 
    {
        this.friendshipService = tripService;
    }
    
    public User getUser1 ()
    {
        return friendship.getUser1();
    }
    
    public User getUser2 ()
    {
        return friendship.getUser2();
    }
}
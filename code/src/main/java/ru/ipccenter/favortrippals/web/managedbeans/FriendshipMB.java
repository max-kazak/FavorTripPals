package ru.ipccenter.favortrippals.web.managedbeans;
/**
 *
 * @author Anton
 */
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;
import ru.ipccenter.favortrippals.core.friendship.service.IFriendshipService;
import ru.ipccenter.favortrippals.core.model.Friendship;
import ru.ipccenter.favortrippals.core.model.User;
import ru.ipccenter.favortrippals.core.user.service.IUserService;

@ManagedBean(name="friendshipMB")
@SessionScoped
public class FriendshipMB 
{
    @ManagedProperty(value ="#{friendshipService}")
    IFriendshipService friendshipService;
    @ManagedProperty (value="#{userService}")
    IUserService userService;
    private Friendship friendship;
    private DualListModel<User> friends;

    public DualListModel<User> getFriends()
    {
        if (friends != null)
            return friends;
        User user = getUserService().getCurrentUser();
        List<Friendship> fs = getFriendshipService().getAllFriendshipsByUser(user);
        List<User> friends = new ArrayList<>();
        for (Friendship friendship : fs)
        {
            friends.add(friendship.getUser2());
        }
        this.friends = new DualListModel<>(friends, new ArrayList<User>());
        return this.friends;
    }

    public void setFriends(DualListModel<User> friends)
    {
        this.friends = friends;
    }

    public IUserService getUserService()
    {
        return userService;
    }

    public void setUserService(IUserService userService)
    {
        this.userService = userService;
    }
    
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
package ru.ipccenter.favortrippals.core.friendship.service;
/**
 *
 * @author Anton
 */
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import ru.ipccenter.favortrippals.core.friendship.dao.IFriendshipDAO;
import ru.ipccenter.favortrippals.core.model.Friendship;
import ru.ipccenter.favortrippals.core.model.User;

@Transactional(readOnly = true)
public class FriendshipService implements IFriendshipService
{
    IFriendshipDAO friendshipDAO;
    
    public IFriendshipDAO getFriendshipDAO ()
    {
        return friendshipDAO;
    }

    public void setFriendshipDAO (IFriendshipDAO friendshipDAO)
    {
        this.friendshipDAO = friendshipDAO;
    }
    
    @Transactional(readOnly = false)
    @Override
    public void addFriendship(Friendship friendship)
    {
        getFriendshipDAO().addFriendship(friendship);
    }
    
    @Transactional(readOnly = false)
    @Override
    public void deleteFriendship(Friendship friendship)
    {
        getFriendshipDAO().deleteFriendship(friendship);
    }
    
    @Transactional(readOnly = false)
    @Override
    public void updateFriendship(Friendship friendship)
    {
        getFriendshipDAO().updateFriendship(friendship);
    }
    
    @Override
    public List<Friendship> getAllFriendshipsByUser(User user)
    {
        return getFriendshipDAO().getAllFriendshipsByUser(user);
    }
}

package ru.ipccenter.favortrippals.core.friendship.dao;
/**
 *
 * @author Anton
 */
import java.util.List;
import ru.ipccenter.favortrippals.core.model.Friendship;
import ru.ipccenter.favortrippals.core.model.User;

public interface IFriendshipDAO 
{
    public void addFriendship(Friendship friendship);
    public void deleteFriendship(Friendship friendship);
    public void updateFriendship(Friendship friendship);
    public List<Friendship> getAllFriendshipsByUser(User user);
    public boolean isFriendshipExists (Friendship friendship);
}

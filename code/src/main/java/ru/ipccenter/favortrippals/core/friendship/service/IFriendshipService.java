package ru.ipccenter.favortrippals.core.friendship.service;
/**
 *
 * @author Anton
 */
import java.util.List;
import ru.ipccenter.favortrippals.core.model.Friendship;
import ru.ipccenter.favortrippals.core.model.User;

public interface IFriendshipService 
{
    public void addFriendship(Friendship friendship);
    public void deleteFriendship(Friendship friendship);
    public void updateFriendship(Friendship friendship);
    public List<Friendship> getAllFriendshipsByUser(User user);
    public void createFriendship(User user1, User user2);
}

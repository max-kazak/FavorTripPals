package ru.ipccenter.favortrippals.core.model;
/**
 * @autor Ddiimmaann
 * Social networks of user.
 * Class include special constants of type of networks.
 */
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="SNETWORKS")
public class SocialConnection implements Serializable
{
    public static final int VKONTAKTE = 0;
    public static final int FACEBOOK = 1;
    public static final int TWITTER = 2;
    
    @Id
    @ManyToOne
    @JoinColumn(name = "USER_ID", unique = false, nullable = false)
    private User user;
    @Column(name = "PAGE", unique = false, nullable = true)
    private String userPage;
    @Column(name = "PROVIDER_USER_ID", unique = false, nullable = false)
    private String providerUserId;
    @Id
    @Column(name = "NETWORK_TYPE", unique = false, nullable = false)
    private int networkType;
    
    public void setUser (User user)
    {
        this.user = user;
    }
    
    public void setUserPage (String userPage)
    {
        this.userPage = userPage;
    }
    
    public void setProviderUserId(String providerUserId)
    {
        this.providerUserId = providerUserId;
    }
    
    /**
     * @param networkType
     * Use constants for networkType.
     */
    public void setNetworkType (int networkType)
    {
        this.networkType = networkType;
    }

    public User getUser ()
    {
        return user;
    }
    
    public String getUserPage ()
    {
        return userPage;
    }
    
    public String getProviderUserId()
    {
        return providerUserId;
    }
    
    /**
     * @return networkType
     * See constants of networkType.
     */
    public int getNetworkType ()
    {
        return networkType;
    }
    
    public static int getNetworkTypeByString (String provider)
    {
        switch (provider.toLowerCase())
        {
            case "facebook":
                return FACEBOOK;
            case "vk": case "vkontakte":
                return VKONTAKTE;
            case "twitter":
                return TWITTER;
            default:
                return -1;
        }
    }
}

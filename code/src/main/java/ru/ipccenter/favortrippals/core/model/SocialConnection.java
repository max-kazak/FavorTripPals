package ru.ipccenter.favortrippals.core.model;
/**
 * @autor Ddiimmaann
 * Social networks of user.
 * Class include special constants of type of networks.
 */
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import ru.ipccenter.favortrippals.core.model.SocialConnection.SocialConnectionK;

@Entity
@IdClass(SocialConnectionK.class)
@Table(name="SNETWORKS")
public class SocialConnection implements Serializable
{
    public static class SocialConnectionK implements Serializable 
    {
        private User user;
        private int networkType;
        
        public SocialConnectionK() {}
        
        public SocialConnectionK(User user, int networkType) 
        {
            this.user = user;
            this.networkType = networkType;
        }
        
        public User getUser()
        {
            return user;
        }
        
        public int getNetworkType()
        {
            return networkType;
        }
        
        @Override
        public boolean equals(Object o)
        {
            if(o instanceof SocialConnectionK)
            {
                SocialConnectionK f = (SocialConnectionK)o;
                if ((f.getUser().getId() == user.getId()) && 
                        (f.getNetworkType() == networkType))
                return true;
            }
            return false;
        }

        @Override
        public int hashCode()
        {
            int hash = 7;
            hash = 97 * hash + Objects.hashCode(this.user);
            hash = 97 * hash + Objects.hashCode(this.networkType);
            return hash;
        }
    }

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
    
    public String getProviderNameByType ()
    {
        switch (networkType)
        {
            case FACEBOOK:
                return "facebook";
            case VKONTAKTE:
                return "vk";
            case TWITTER:
                return "twitter";
            default:
                return "";
        }
    }
}
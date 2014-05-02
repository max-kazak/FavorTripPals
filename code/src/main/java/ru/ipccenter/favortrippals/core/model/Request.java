package ru.ipccenter.favortrippals.core.model;
/**
 *
 * @author Anton
 */
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.IdClass;
//import ru.ipccenter.favortrippals.core.model.Request.RequestPK;

@Entity
//@IdClass(RequestPK.class)
@Table(name="REQUESTS")
public class Request implements Serializable
{
    /*
    public class RequestPK implements Serializable 
    {
        protected Trip trip;
        protected Goods goods;
        protected User customer;

        public RequestPK() {}
        
        public RequestPK(Trip trip, Goods goods, User customer) 
        {
            this.trip = trip;
            this.goods = goods;
            this.customer = customer;
        }
        
        public Trip getTrip()
        {
            return trip;
        }
        
        public Goods getGoods()
        {
            return goods;
        }
        
        public User getCustomer()
        {
            return customer;
        }
        
        @Override
        public boolean equals(Object o) 
        {
            if(o instanceof RequestPK)
            {
                RequestPK f = (RequestPK)o;
                if ((f.getTrip().getId() == trip.getId()) && 
                        (f.getGoods().getId() == goods.getId()) && 
                        f.getCustomer().getId() == customer.getId())
                return true;
            }
        return false;
    }

        @Override
        public int hashCode() 
        {
            int hash = 3;
            hash = 59 * hash + Objects.hashCode(this.trip);
            hash = 59 * hash + Objects.hashCode(this.goods);
            hash = 59 * hash + Objects.hashCode(this.customer);
            return hash;
        }
    }
    */
    
    @Id
    @ManyToOne
    @JoinColumn(name = "TRIP", unique = false, nullable = false)
    private Trip trip;
    @Id
    @ManyToOne
    @JoinColumn(name = "GOODS", unique = false, nullable = false)
    private Goods goods;
    @Id
    @ManyToOne
    @JoinColumn(name = "CUSTOMER", unique = false, nullable = false)
    private User customer;
    @Column(name = "COUNT", unique = false, nullable = true)
    private Integer count;
    
    public void setTrip (Trip trip)
    {
        this.trip = trip;
    }
    
    public void setGoods (Goods goods)
    {
        this.goods = goods;
    }
    
    public void setCustomer (User customer)
    {
        this.customer = customer;
    }
    
    public void setCount (Integer count)
    {
        this.count = count;
    }
    
    public Trip getTrip ()
    {
        return trip;
    }
    
    public Goods getGoods ()
    {
        return goods;
    }
    
    public User getCustomer ()
    {
        return customer;
    }
    
    public Integer getCount ()
    {
        return count;
    }    
}
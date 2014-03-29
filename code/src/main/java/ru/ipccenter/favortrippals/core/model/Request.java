package ru.ipccenter.favortrippals.core.model;
/**
 *
 * @author Anton
 */
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name="REQUESTS")
public class Request implements Serializable
{
    @Id
    @ManyToOne
    @JoinColumn(name = "TRIPS", unique = true, nullable = false)
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
    private int count;
    
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
    
    public void setCount (int count)
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
    
    public int getCount ()
    {
        return count;
    }    
}

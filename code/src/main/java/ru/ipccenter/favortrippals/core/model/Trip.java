package ru.ipccenter.favortrippals.core.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Calendar;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Table(name = "Trips" )
public class Trip implements Serializable {

    private long id;
    private String dest;
    private Integer capacity;

    private User traveller;

    private Calendar departure_date = Calendar.getInstance();
    private Calendar arrival_date = Calendar.getInstance();

    @Id
    @Column(name="ID", unique = true, nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name="DEST", unique = false, nullable = true)
    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    @Column(name="CAPACITY", unique = false, nullable = true)
    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    @ManyToOne
    @JoinColumn(name = "TRAVELLER", unique = false, nullable = false)
    public User getTraveller() {
        return traveller;
    }

    public void setTraveller(User traveller) {
        this.traveller = traveller;
    }

    @Column(name="DEPARTURE_DATE", unique = false, nullable = true)
    @Temporal(TemporalType.DATE)
    public Calendar getDeparture_date() {
        return departure_date;
    }

    public void setDeparture_date(Calendar departure_date) {
        this.departure_date = departure_date;
    }

    public void setDeparture_date(int year, int month, int day, int hour, int minute)
    {
        this.departure_date.set(year, month, day, hour, minute);
    }

    @Column(name="ARRIVAL_DATE", unique = false, nullable = true)
    @Temporal(TemporalType.DATE)
    public Calendar getArrival_date() {
        return arrival_date;
    }

    public void setArrival_date(Calendar arrival_date) {
        this.arrival_date = arrival_date;
    }

    public void setArrival_date(int year, int month, int day, int hour, int minute)
    {
        this.arrival_date.set(year, month, day, hour, minute);
    }

    @Override
    public String toString() {
        StringBuffer strBuff = new StringBuffer();
        strBuff.append("id : ").append(getId());
        strBuff.append(", destination : ").append(getDest());
        strBuff.append(", capacity : ").append(getCapacity());
        strBuff.append(", traveller : ").append(getTraveller());
        strBuff.append(", Arrival Date : ").append(getArrival_date());
        strBuff.append(", Departure Date : ").append(getDeparture_date());
        return strBuff.toString();
    }
}

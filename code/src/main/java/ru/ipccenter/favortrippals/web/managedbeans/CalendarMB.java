package ru.ipccenter.favortrippals.web.managedbeans;

import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * @author Ddiimmaann
 */
@ManagedBean(name="calendarMB")
@RequestScoped
public class CalendarMB
{
    private Date date;
    
    public void setDate (Date date)
    {
        this.date = date;
    }
    public Date getDate ()
    {
        return new Date();
    }
}

package ru.ipccenter.favortrippals.web.managedbeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import ru.ipccenter.favortrippals.core.model.Request;
import ru.ipccenter.favortrippals.core.request.service.IRequestService;

/**
 *
 * @author Anton
 */
@ManagedBean(name="statusChangeMB")
@RequestScoped
public class StatusChangeMB 
{
    @ManagedProperty(value="#{requestService}")
    IRequestService requestService;
    
    private String status;
    
    public IRequestService getRequestService() 
    {
        return requestService;
    }

    public void setRequestService(IRequestService requestService) 
    {
        this.requestService = requestService;
    }
    
    public String getStatus()
    {
        return status;
    }
    
    public void setStatus(String status)
    {
        this.status = status;
    }
    
    public void confirm(Request req) 
    {
        changeStatus("executed", req);
    }
     
    public void delete(Request req) 
    {
        changeStatus("deleted", req);
    }
     
    public void approve(Request req) 
    {
        changeStatus("approved", req);
    }
    
    public void disapprove(Request req) 
    {
        changeStatus("disapproved", req);
    }
    
    public void changeStatus(String newStatus, Request req)
    {
        req.setStatus(newStatus);
        getRequestService().updateRequest(req);        
    }
}

package ru.ipccenter.favortrippals.web.managedbeans;
/**
 *
 * @author Anton
 */
import java.util.List;
import java.util.UUID;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import ru.ipccenter.favortrippals.core.goods.service.IGoodsService;
import ru.ipccenter.favortrippals.core.model.Goods;
import ru.ipccenter.favortrippals.core.model.Request;
import ru.ipccenter.favortrippals.core.model.Trip;
import ru.ipccenter.favortrippals.core.model.User;
import ru.ipccenter.favortrippals.core.request.service.IRequestService;
import ru.ipccenter.favortrippals.core.user.service.IUserService;
import ru.ipccenter.favortrippals.core.trip.service.ITripService;

@ManagedBean(name="requestMB")
@RequestScoped
public class RequestMB 
{
    @ManagedProperty(value="#{requestService}")
    IRequestService requestService;
    @ManagedProperty(value="#{userService}")
    IUserService userService;
    @ManagedProperty(value="#{goodsService}")
    IGoodsService goodsService;
    @ManagedProperty(value="#{tripService}")
    ITripService tripService;
    
    private Request request;
    private User customer;
    private Trip trip;
    private Goods goods;
    private Integer count;
    private Integer max_cost;
    private String currency;
    private String status;
    
    public IRequestService getRequestService() 
    {
        return requestService;
    }

    public void setRequestService(IRequestService requestService) 
    {
        this.requestService = requestService;
    }
    
    public IUserService getUserService() 
    {
        return userService;
    }

    public void setUserService(IUserService userService) 
    {
        this.userService = userService;
    }
    
    public IGoodsService getGoodsService() 
    {
        return goodsService;
    }

    public void setGoodsService(IGoodsService goodsService) 
    {
        this.goodsService = goodsService;
    }
    
    public ITripService getTripService() 
    {
        return tripService;
    }

    public void setTripService(ITripService tripService) 
    {
        this.tripService = tripService;
    }
    
    public Integer getCount()
    {
        return count;
    }
    
    public void setCount(Integer count)
    {
        this.count = count;
    }
    
    public Integer getMaxCost()
    {
        return max_cost;
    }
    
    public void setMaxCost(Integer max_cost)
    {
        this.max_cost = max_cost;
    }
    
    public String getCurrency()
    {
        return currency;
    }
    
    public void setCurrency(String currency)
    {
        this.currency = currency;
    }
    
    public String getStatus()
    {
        return status;
    }
    
    public void setStatus(String status)
    {
        this.status = status;
    }
    
    public List<Request> getRequests()
    {
        return getRequestService().getAllRequestsByCustomer(
                getUserService().getCurrentUser());
    }
    
    public Request getRequest()
    {
        return request;
    }
    
    public User getCustomer()
    {
        return customer;
    }
    
    public void setCustomer(User customer)
    {
        this.customer = customer;
    }
    
    public Trip getTrip()
    {
        return trip;
    }
    
    public void setTrip(Trip trip)
    {
        this.trip = trip;
    }
    
    public Goods getGoods()
    {
        return goods;
    }
    
    public void setGoods(Goods goods)
    {
        this.goods = goods;
    }
    
    public String addRequest()
    {
        if (getGoodsService().getBooleanNewGoodsState())
        {
            Goods newGoods = new Goods();
            newGoods.setId(idGeneratorForGoods());
            newGoods.setName(getGoodsService().getNewGoodsName());
            getGoodsService().addGoods(newGoods);
            setGoods(newGoods);
        }
        else
        {
            setGoods(getGoodsService().getGoodsByName(getGoodsService().getNewGoodsName()));
        }
        
        if (getRequestService().getRequestByAllIds(
                            getUserService().getCurrentUser(),
                            getTrip(),
                            getGoods()) != null)
        {
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage("Request already exist."));
            return "error";
        }
        Request newRequest = new Request();
        newRequest.setCustomer(getUserService().getCurrentUser());
        newRequest.setTrip(getTrip());
        newRequest.setGoods(getGoods());
        newRequest.setCount(getCount());
        newRequest.setMaxCost(getMaxCost());
        newRequest.setCurrency(getCurrency());
        newRequest.setStatus("not checked");
        getRequestService().addRequest(newRequest);
        FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage("Success."));
        return "success";
    }
    
    private Long idGeneratorForGoods()
    {
        Long id;
        do
        {
            id = UUID.randomUUID().getLeastSignificantBits();
            id *= Long.signum(id);
        }
        while (getGoodsService().getGoodsById(id)!=null);
        return id;
    }
    
    public List<Request> getAllRequestsByTrip(Trip trip)
    {
        return getRequestService().getAllRequestsByTrip(trip);
    }
    
    public List<Request> getAllRequestsByUser()
    {
        return getRequestService().getAllRequestsByUser(getUserService().getCurrentUser());
    }
}

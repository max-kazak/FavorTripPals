package ru.ipccenter.favortrippals.web.managedbeans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import ru.ipccenter.favortrippals.core.goods.service.IGoodsService;

/**
 *
 * @author Anton
 */

@ManagedBean(name="pprMB")
@RequestScoped
public class PprMB implements Serializable
{
    private IGoodsService goodsService;
    private int NEW_GOODS = 1;
    private int NO_NEW_GOODS = 0;
    private String name;
    
    private String cost;  
  
    private String currency;  
      
    private Map<String,String> costs = new HashMap<String, String>();  
      
    private Map<String,String> currencies = new HashMap<String, String>();  
  
    public PprMB() 
    {  /*
        name = getGoodsService().getNewGoodsName();
        costs = getGoodsService().getMapOfCostsByName(getName());
        if (costs.isEmpty())
        {
            getGoodsService().setNewCostState(NEW_GOODS);
        }
        else
        {
            getGoodsService().setNewCostState(NO_NEW_GOODS);
        }*/
    }  
    
    public String getName() {  
        return name;  
    }  
  
    public void setName(String name) {  
        this.name = name;  
    } 
    
    public String getCost() {  
        return cost;  
    }  
  
    public void setCost(String cost) 
    {  
        this.cost = cost;  
        getGoodsService().setNewGoodsCost(cost);
    }  
  
    public String getCurrency() {  
        return currency;  
    }  
  
    public void setCurrency(String currency) 
    {  
        this.currency = currency;  
        getGoodsService().setNewGoodsCurrency(currency);
    }  
  
    public Map<String, String> getCosts() {  
        return costs;  
    }  
  
    public void setCosts(Map<String, String> costs) {  
        this.costs = costs;  
    }  
      
    public Map<String, String> getCurrencies() {  
        return currencies;  
    }  
  
    public void setCurrencies(Map<String, String> currencies) {  
        this.currencies = currencies;  
    }  
      
    public void handleCostChange() {  
        if(cost !=null && !cost.equals(""))
        {
            currencies = getGoodsService().getMapOfCurrenciesByNameAndCost(getName(), cost); 
            if (currencies.isEmpty())
            {
                getGoodsService().setNewCurrencyState(NEW_GOODS);
            }
            else
            {
                getGoodsService().setNewCurrencyState(NO_NEW_GOODS);
            }
        }
        else  
        {
            currencies = new HashMap<String, String>(); 
            getGoodsService().setNewCurrencyState(NEW_GOODS);
        }
    }  
    
    public IGoodsService getGoodsService() 
    {
        return goodsService;
    }

    public void setGoodsService(IGoodsService goodsService) 
    {
        this.goodsService = goodsService;
    }
}

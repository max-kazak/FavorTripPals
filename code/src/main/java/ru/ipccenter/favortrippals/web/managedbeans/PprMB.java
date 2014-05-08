package ru.ipccenter.favortrippals.web.managedbeans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Anton
 */

@ManagedBean(name="pprMB")
@RequestScoped
public class PprMB implements Serializable
{ 
    
    private Map<String,String> currencies = new HashMap<String, String>();  
  
    public PprMB() 
    {  
        currencies.put("USD", "USD");
        currencies.put("RUR", "RUR");
        currencies.put("EUR", "EUR");
        currencies.put("GBP", "GBP");
        currencies.put("CNY", "CNY");
        currencies.put("JPY", "JPY");
        currencies.put("UAH", "UAH");
    }  
    
    public Map<String, String> getCurrencies() {  
        return currencies;  
    }  
  
    public void setCurrencies(Map<String, String> currencies) {  
        this.currencies = currencies;  
    }  
}

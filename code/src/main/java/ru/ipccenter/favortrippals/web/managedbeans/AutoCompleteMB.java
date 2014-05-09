package ru.ipccenter.favortrippals.web.managedbeans;
/**
 *
 * @author Anton
 */
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import ru.ipccenter.favortrippals.core.goods.service.IGoodsService;

@ManagedBean(name="autoCompleteMB")
@RequestScoped
public class AutoCompleteMB 
{
    @ManagedProperty(value="#{goodsService}")
    private IGoodsService goodsService;
    
    public List<String> completeName(String query) 
    {  
        if (query == null) return null;
        List<String> results = new ArrayList<String>();  
        
        results = getGoodsService().findGoodsByNameBeginning(query);
          
        return results;  
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

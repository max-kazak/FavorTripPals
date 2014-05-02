package ru.ipccenter.favortrippals.web.managedbeans;
/**
 *
 * @author Anton
 */
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import ru.ipccenter.favortrippals.core.goods.service.IGoodsService;

@ManagedBean(name="autoCompleteMB")
@RequestScoped
public class AutoCompleteMB 
{
    private final int NEW_GOODS = 1;
    private final int NO_NEW_GOODS = 0;
    private String txtGoods; 
    private IGoodsService goodsService;
    
    public List<String> complete(String query) {  
        List<String> results = new ArrayList<String>();  
          
        results = getGoodsService().findGoodsByNameBeginning(query);
        
        if (results.isEmpty()) 
        {
            getGoodsService().setNewGoodsState(NEW_GOODS);
        }
        else 
        {
            getGoodsService().setNewGoodsState(NO_NEW_GOODS);
        }
          
        return results;  
    }  
  
    public String getTxtGoods() 
    {  
        return txtGoods;  
    }  
  
    public void setTxtGoods(String txtGoods) 
    {  
        this.txtGoods = txtGoods; 
        getGoodsService().setNewGoodsName(txtGoods);
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

package ru.ipccenter.favortrippals.web.managedbeans;
/**
 *
 * @author Anton
 */
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.ipccenter.favortrippals.core.goods.service.IGoodsService;
import ru.ipccenter.favortrippals.core.model.Goods;

@ManagedBean(name="goodsMB")
@SessionScoped
public class GoodsMB 
{
    @ManagedProperty(value="#{GoodsService}")
    IGoodsService goodsService;
    
    private Goods goods;
    
    public IGoodsService getGoodsService()
    {
        return goodsService;
    }
	
    public void setGoodsService(IGoodsService goodsService)
    {
        this.goodsService = goodsService;
    }
    
    private void checkActuality()
    {
        String actualId = SecurityContextHolder.getContext().getAuthentication().getName();
        if( (goods==null) || ( !actualId.equals(""+goods.getId())) )
        {
            goods = goodsService.getGoodsById(Long.parseLong(actualId));
        }
    }
    
    public long getId()
    {
        checkActuality();
        return goods.getId();
    }
    
    public String getName()
    {
        checkActuality();
        return goods.getName();
    }
    
    public String getDescription()
    {
        checkActuality();
        return goods.getDescription();
    }
    
    public String getLocation()
    {
        checkActuality();
        return goods.getLocation();
    }
    
    public int getCost()
    {
        checkActuality();
        return goods.getCost();
    }
    
    public String getCurrency()
    {
        checkActuality();
        return goods.getCurrency();
    }
    
    public Goods getGoods()
    {
        checkActuality();
        return goods;
    }
    
    @Override
    public String toString()
    {
        checkActuality();
        return goods.toString();
    }
}

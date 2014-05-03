package ru.ipccenter.favortrippals.core.goods.service;
/**
 *
 * @author Anton
 */
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import ru.ipccenter.favortrippals.core.goods.dao.IGoodsDAO;
import ru.ipccenter.favortrippals.core.model.Goods;

@Transactional(readOnly = true)
public class GoodsService implements IGoodsService
{
    IGoodsDAO goodsDAO;
    
    public IGoodsDAO getGoodsDAO ()
    {
        return goodsDAO;
    }

    public void setGoodsDAO (IGoodsDAO goodsDAO)
    {
        this.goodsDAO = goodsDAO;
    }
    
    @Transactional(readOnly = false)
    @Override
    public void addGoods(Goods goods)
    {
        getGoodsDAO().addGoods(goods);
    }
    
    @Transactional(readOnly = false)
    @Override
    public void deleteGoods(Goods goods)
    {
        getGoodsDAO().deleteGoods(goods);
    }
    
    @Transactional(readOnly = false)
    @Override
    public void updateGoods(Goods goods)
    {
        getGoodsDAO().updateGoods(goods);
    }
    
    @Override
    public Goods getGoodsById(long id)
    {
        return getGoodsDAO().getGoodsById(id);
    }
    
    @Override
    public List<Goods> getAllGoods()
    {
        return getGoodsDAO().getAllGoods();
    }
    
    @Override
    public List<String> findGoodsByNameBeginning(String query)
    {
        return getGoodsDAO().findGoodsByNameBeginning(query);
    }
    
    @Override
    public void setNewGoodsState(int state)
    {
        getGoodsDAO().setNewGoodsState(state);
    }
    
    @Override
    public void setNewCostState(int stateCost)
    {
        getGoodsDAO().setNewCostState(stateCost);
    }
    
    @Override
    public void setNewCurrencyState(int stateCurrency)
    {
        getGoodsDAO().setNewCurrencyState(stateCurrency);
    }
    
    @Override
    public boolean getBooleanNewGoodsState()
    {
        return getGoodsDAO().getBooleanNewGoodsState();
    }
    
    @Override
    public boolean getBooleanNewCostState()
    {
        return getGoodsDAO().getBooleanNewCostState();
    }
    
    @Override
    public boolean getBooleanNewCurrencyState()
    {
        return getGoodsDAO().getBooleanNewCurrencyState();
    }
    
    @Override
    public void setNewGoodsName(String name)
    {
        getGoodsDAO().setNewGoodsName(name);
    }
    
    @Override
    public String getNewGoodsName()
    {
        return getGoodsDAO().getNewGoodsName();
    }
    
    @Override
    public void setNewGoodsCost(String newGoodsCost)
    {
        getGoodsDAO().setNewGoodsName(newGoodsCost);
    }
    
    @Override
    public Integer getNewGoodsCost()
    {
        return getGoodsDAO().getNewGoodsCost();
    }
    
    @Override
    public void setNewGoodsCurrency(String newGoodsCurrency)
    {
        getGoodsDAO().setNewGoodsName(newGoodsCurrency);
    }
    
    @Override
    public String getNewGoodsCurrency()
    {
        return getGoodsDAO().getNewGoodsCurrency();
    }
    
    @Override
    public Goods getGoodsByParameters(String name, Integer cost, String currency)
    {
        return getGoodsDAO().getGoodsByParameters(name, cost, currency);
    }
    
    @Override
    public Map<String, String> getMapOfCostsByName(String name)
    {
        return getGoodsDAO().getMapOfCostsByName(name);
    }
    
    @Override
    public Map<String, String> getMapOfCurrenciesByNameAndCost(String name, String cost)
    {
        return getGoodsDAO().getMapOfCurrenciesByNameAndCost(name, cost);
    }
}

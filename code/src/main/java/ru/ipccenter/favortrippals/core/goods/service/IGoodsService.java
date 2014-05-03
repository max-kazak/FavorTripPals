package ru.ipccenter.favortrippals.core.goods.service;
/**
 *
 * @author Anton
 */
import java.util.List;
import java.util.Map;
import ru.ipccenter.favortrippals.core.model.Goods;

public interface IGoodsService 
{
    public void addGoods(Goods goods);
    public void deleteGoods(Goods goods);
    public void updateGoods(Goods goods);
    public Goods getGoodsById(long id);
    public List<Goods> getAllGoods();
    public List<String> findGoodsByNameBeginning(String query);
    public void setNewGoodsState(int state);
    public void setNewCostState(int stateCost);
    public void setNewCurrencyState(int stateCurrency);
    public boolean getBooleanNewGoodsState();
    public boolean getBooleanNewCostState();
    public boolean getBooleanNewCurrencyState();
    public void setNewGoodsName(String name);
    public String getNewGoodsName();
    public void setNewGoodsCost(String newGoodsCost);
    public Integer getNewGoodsCost();
    public void setNewGoodsCurrency(String newGoodsCurrency);
    public String getNewGoodsCurrency();
    public Goods getGoodsByParameters(String name, Integer cost, String currency);
    public Map<String, String> getMapOfCostsByName(String name);
    public Map<String, String> getMapOfCurrenciesByNameAndCost(String name, String cost);
}

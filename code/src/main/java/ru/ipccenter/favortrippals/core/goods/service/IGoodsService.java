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
    public Goods getGoodsByName(String name);
    public Map<String, String> getMapOfCostsByName(String name);
    public Map<String, String> getMapOfCurrenciesByNameAndCost(String name, String cost);
}

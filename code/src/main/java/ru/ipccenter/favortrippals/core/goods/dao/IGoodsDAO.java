package ru.ipccenter.favortrippals.core.goods.dao;
/**
 *
 * @author Anton
 */
import java.util.List;
import ru.ipccenter.favortrippals.core.model.Goods;

public interface IGoodsDAO 
{
    public void addGoods(Goods goods);
    public void deleteGoods(Goods goods);
    public void updateGoods(Goods goods);
    public Goods getGoodsById(long id);
    public List<Goods> getAllGoods();
}
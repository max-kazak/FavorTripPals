package ru.ipccenter.favortrippals.core.goods.service;
/**
 *
 * @author Anton
 */
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import ru.ipccenter.favortrippals.core.goods.dao.IGoodsDAO;
import ru.ipccenter.favortrippals.core.model.Goods;

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
    
    @Transactional
    @Override
    public void addGoods(Goods goods)
    {
        getGoodsDAO().addGoods(goods);
    }
    
    @Transactional
    @Override
    public void deleteGoods(Goods goods)
    {
        getGoodsDAO().deleteGoods(goods);
    }
    
    @Transactional
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
    public boolean getBooleanNewGoodsState()
    {
        return getGoodsDAO().getBooleanNewGoodsState();
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
    public Goods getGoodsByName(String name)
    {
        return getGoodsDAO().getGoodsByName(name);
    }
}

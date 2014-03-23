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
    Goods currentGoods;
    IGoodsDAO goodsDAO;
    
    public IGoodsDAO getGoodsDAO ()
    {
        return goodsDAO;
    }

    public void setUserDAO (IGoodsDAO goodsDAO)
    {
        this.goodsDAO = goodsDAO;
    }
        
    @Override
    public Goods getCurrentGoods()
    {
        return currentGoods;
    }
    
    @Override
    public void setCurrentGoods(Goods currentGoods)
    {
        this.currentGoods = currentGoods;
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
        currentGoods = getGoodsDAO().getGoodsById(id);
        return currentGoods;
    }
    
    @Override
    public List<Goods> getAllGoods()
    {
        return getGoodsDAO().getAllGoods();
    }
}

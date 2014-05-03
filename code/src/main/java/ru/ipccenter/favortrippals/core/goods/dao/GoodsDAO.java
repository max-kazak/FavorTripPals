package ru.ipccenter.favortrippals.core.goods.dao;
/**
 *
 * @author Anton
 */
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import ru.ipccenter.favortrippals.core.model.Goods;

public class GoodsDAO implements IGoodsDAO
{
    private final int NEW_GOODS = 1;
    private int state;
    private int stateCost;
    private int stateCurrency;
    private String newGoodsName;
    private String newGoodsCost;
    private String newGoodsCurrency;
    private SessionFactory sessionFactory;
    
    public SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }
    
    @Override
    public void addGoods(Goods goods)
    {
        long id;
        do
        {
            id = UUID.randomUUID().getLeastSignificantBits();
            id *= Long.signum(id);
        }
        while (getGoodsById(id)!=null);
        goods.setId(id);
        getSessionFactory().getCurrentSession().save(goods);
    }
    
    @Override
    public void deleteGoods(Goods goods)
    {
        getSessionFactory().getCurrentSession().delete(goods);
    }
    
    @Override
    public void updateGoods(Goods goods)
    {
        getSessionFactory().getCurrentSession().update(goods);
    }
    
    @Override
    public Goods getGoodsById(long id)
    {
        String query = "from Goods where id=?";
        List list = getSessionFactory().getCurrentSession()
				.createQuery(query)
				.setParameter(0, id)
				.list();
        if(!list.isEmpty())
            return (Goods)list.get(0);
        else return null;
    }
    
    @Override
    public List<Goods> getAllGoods()
    {
        String query = "from Goods";
        List list = getSessionFactory().getCurrentSession()
                    .createQuery(query)
                    .list();
        return list;
    }
    
    @Override
    public List<String> findGoodsByNameBeginning(String query)
    {
        String stringQuery = "select name from Goods where name like ':query%'";
        List list = getSessionFactory().getCurrentSession()
                    .createQuery(stringQuery).setParameter("query", query)
                    .list();
        return list;
    }
    
    @Override
    public void setNewGoodsState(int state)
    {
        this.state = state;
    }
    
    @Override
    public void setNewCostState(int stateCost)
    {
        this.stateCost = stateCost;
    }
    
    @Override
    public void setNewCurrencyState(int stateCurrency)
    {
        this.stateCurrency = stateCurrency;
    }
    
    @Override
    public boolean getBooleanNewGoodsState()
    {
        if (state == NEW_GOODS) return true;
        else return false;
    }
    
    @Override
    public boolean getBooleanNewCostState()
    {
        if (stateCost == NEW_GOODS) return true;
        else return false;
    }
    
    @Override
    public boolean getBooleanNewCurrencyState()
    {
        if (stateCurrency == NEW_GOODS) return true;
        else return false;
    }
    
    @Override
    public void setNewGoodsName(String name)
    {
        this.newGoodsName = name;
    }
    
    @Override
    public String getNewGoodsName()
    {
        return newGoodsName;
    }
    
    @Override
    public void setNewGoodsCost(String newGoodsCost)
    {
        this.newGoodsCost = newGoodsCost;
    }
    
    @Override
    public Integer getNewGoodsCost()
    {
        return Integer.parseInt(newGoodsCost);
    }
    
    @Override
    public void setNewGoodsCurrency(String newGoodsCurrency)
    {
        this.newGoodsCurrency = newGoodsCurrency;
    }
    
    @Override
    public String getNewGoodsCurrency()
    {
        return newGoodsCurrency;
    }
    
    @Override
    public Goods getGoodsByParameters(String name, Integer cost, String currency)
    {
        String stringQuery = "from Goods where name=:name and cost=:cost and currency=:currency";
        Query query = getSessionFactory().getCurrentSession().createQuery(stringQuery);
        query.setParameter("name", name);
        query.setParameter("cost", cost);
        query.setParameter("currency", currency);
        List list = query.list();
        return (Goods)list.get(0);
    }
    
    @Override
    public Map<String, String> getMapOfCostsByName(String name)
    {
        String query = "select cost from Goods where name=?";
        List list = getSessionFactory().getCurrentSession().createQuery(query)
				.setParameter(0, name).list();
        Map<String, String> map = new HashMap<>();
        if (list.isEmpty()) return map;
        for (Object x : list)
            map.put(x.toString(), x.toString());
        return map;
    }
    
    @Override
    public Map<String, String> getMapOfCurrenciesByNameAndCost(String name, String cost)
    {
        String stringQuery = "select currency from Goods where name=:name and cost:=cost";
        Query query = getSessionFactory().getCurrentSession().createQuery(stringQuery);
        query.setParameter("name", name);
        query.setParameter("cost", Integer.parseInt(cost));
        List list = query.list();
        Map<String, String> map = new HashMap<>();
        if (list.isEmpty()) return map;
        for (Object x : list)
            map.put(x.toString(), x.toString());
        return map;
    }
}

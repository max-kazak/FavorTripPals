package ru.ipccenter.favortrippals.core.goods.dao;
/**
 *
 * @author Anton
 */
import java.util.List;
import java.util.UUID;
import org.hibernate.SessionFactory;
import ru.ipccenter.favortrippals.core.model.Goods;

public class GoodsDAO implements IGoodsDAO
{
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
}

package ru.ipccenter.favortrippals.core.user.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import ru.ipccenter.favortrippals.core.model.User;


public class UserDAO implements IUserDAO {

	private SessionFactory sessionFactory;


	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}


	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	@Override
	public void addUser(User user) {
		getSessionFactory().getCurrentSession().save(user);
	}


	@Override
	public void deleteUser(User user) {
		getSessionFactory().getCurrentSession().delete(user);
	}


	@Override
	public void updateUser(User user) {
		getSessionFactory().getCurrentSession().update(user);
	}


	@Override
	public User getUserById(long id) {
		List list = getSessionFactory().getCurrentSession()
				.createQuery("from User where id=?")
				.setParameter(0, id)
				.list();
		return (User)list.get(0);
	}


	@Override
	public List<User> getUsers() {
		List list = getSessionFactory().getCurrentSession()
				.createQuery("from User")
				.list();
		return list;
	}


	@Override
	public User getUserByLogin(String login) {
		List list = getSessionFactory().getCurrentSession()
				.createQuery("from User where login=?")
				.setParameter(0, login)
				.list();
		return (User)list.get(0);
	}

}

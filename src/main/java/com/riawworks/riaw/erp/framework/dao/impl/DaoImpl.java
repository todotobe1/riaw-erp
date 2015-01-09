package com.riawworks.riaw.erp.framework.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;

import com.riawworks.riaw.erp.framework.dao.Dao;
import com.riawworks.riaw.erp.framework.exception.DaoException;

public class DaoImpl<T extends Serializable, PK extends Serializable> implements Dao<T, PK> {

	private HibernateTemplate hibernateTemplate;
	private Class<T> poClass;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public Class<T> getPoClass() {
		return poClass;
	}

	public void setPoClass(Class<T> poClass) {
		this.poClass = poClass;
	}

	@SuppressWarnings("unchecked")
	public DaoImpl() {
		setPoClass((Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0]);
	}

	@SuppressWarnings("unchecked")
	@Override
	public PK create(T t) throws DaoException {
		return (PK) getHibernateTemplate().save(t);
	}

	@Override
	public T read(PK pk) throws DaoException {
		return getHibernateTemplate().get(getPoClass(), pk);
	}

	@Override
	public List<T> readAll() throws DaoException {
		return getHibernateTemplate().loadAll(getPoClass());
	}

	@Override
	public void update(T t) throws DaoException {
		getHibernateTemplate().update(t);
	}

	@Override
	public void delete(T t) throws DaoException {
		getHibernateTemplate().delete(t);
	}

	@Override
	public Integer count(String hql) throws DaoException {
		hql = "select count(*) " + hql;
		return queryForInt(hql);
	}

	@Override
	public Integer count(String hql, Object... args) throws DaoException {
		hql = "select count(*) " + hql;
		return queryForInt(hql, args);
	}

	@Override
	public Integer count() throws DaoException {
		return queryForInt("select count(*) from " + poClass.getName());
	}

	@Override
	public Integer queryForInt(String hql) throws DaoException {
		return Integer.valueOf(getHibernateTemplate().find(hql).listIterator().next().toString());
	}

	@Override
	public Integer queryForInt(String hql, Object... args) throws DaoException {
		return Integer.valueOf(getHibernateTemplate().find(hql, args).listIterator().next()
				.toString());
	}

	@Override
	public List<T> paging(final String hql, int page, final int rows) throws DaoException {
		final int offset = (page - 1) * rows;
		return getHibernateTemplate().execute(new HibernateCallback<List<T>>() {
			@SuppressWarnings("unchecked")
			@Override
			public List<T> doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery(hql);
				List<T> result = query.setFirstResult(offset).setMaxResults(rows).list();
				return result;
			}
		});
	}

	@Override
	public List<T> paging(final String hql, int page, final int rows, final Object... args)
			throws DaoException {
		final int offset = (page - 1) * rows;
		return getHibernateTemplate().execute(new HibernateCallback<List<T>>() {
			@SuppressWarnings("unchecked")
			@Override
			public List<T> doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery(hql);
				for (int i = 0; i < args.length; i++) {
					query.setParameter(i, args[i]);
				}
				List<T> result = query.setFirstResult(offset).setMaxResults(rows).list();
				return result;
			}
		});
	}

	@Override
	public List<T> paging(int page, final int rows) throws DaoException {
		final int offset = (page - 1) * rows;
		return getHibernateTemplate().execute(new HibernateCallback<List<T>>() {
			@SuppressWarnings("unchecked")
			@Override
			public List<T> doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery("from " + poClass.getName());
				List<T> result = query.setFirstResult(offset).setMaxResults(rows).list();
				return result;
			}
		});
	}

	@Override
	public void createOrUpdate(T t) throws DaoException {
		getHibernateTemplate().saveOrUpdate(t);
	}

}
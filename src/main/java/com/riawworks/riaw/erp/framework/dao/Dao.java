package com.riawworks.riaw.erp.framework.dao;

import java.io.Serializable;
import java.util.List;

import com.riawworks.riaw.erp.framework.exception.DaoException;

public interface Dao<T extends Serializable, PK extends Serializable> {

	public PK create(T t) throws DaoException;

	public void createOrUpdate(T t) throws DaoException;

	public T read(PK pk) throws DaoException;

	public List<T> readAll() throws DaoException;

	public void update(T t) throws DaoException;

	public void delete(T t) throws DaoException;

	public Integer queryForInt(String hql) throws DaoException;

	public Integer queryForInt(String hql, Object... args) throws DaoException;

	public Integer count(String hql) throws DaoException;

	public Integer count(String hql, Object... args) throws DaoException;

	public Integer count() throws DaoException;

	public List<T> paging(String hql, int page, int rows) throws DaoException;

	public List<T> paging(String hql, int page, int rows, Object... args) throws DaoException;

	public List<T> paging(int page, int rows) throws DaoException;

}

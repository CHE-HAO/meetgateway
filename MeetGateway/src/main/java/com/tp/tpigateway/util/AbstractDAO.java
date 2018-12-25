package com.tp.tpigateway.util;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractDAO<PK extends Serializable, T> {

	private final Class<T> persistentClass;

	@SuppressWarnings("unchecked")
	public AbstractDAO() {
		this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
	}

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public T getByKey(PK key) {
		return (T) getSession().get(persistentClass, key);
	}

	public void persist(T entity) {
		getSession().persist(entity);
	}

	public void bulkInsert(List<T> entitys) {
		if (entitys == null || entitys.isEmpty()) {
			return;
		}

		for (int i = 0; i < entitys.size(); i++) {
			getSession().save(entitys.get(i));
			if (i % NumberUtils.toInt(PropUtils.getProperty("hibernate.jdbc.batch_size"), 1) == 0) { // same as the JDBC batch size
				// flush a batch of inserts and release memory:
				getSession().flush();
				getSession().clear();
			}
		}
	}

	public void bulkUpdate(List<T> entitys) {
		if (entitys == null || entitys.isEmpty()) {
			return;
		}

		for (int i = 0; i < entitys.size(); i++) {
			getSession().saveOrUpdate(entitys.get(i));
			if (i % NumberUtils.toInt(PropUtils.getProperty("hibernate.jdbc.batch_size"), 1) == 0) { // same as the JDBC batch size
				// flush a batch of inserts and release memory:
				getSession().flush();
				getSession().clear();
			}
		}
	}

	public void delete(T entity) {
		getSession().delete(entity);
	}

	public void truncate(String tableName) {
		getSession().createSQLQuery("TRUNCATE TABLE " + tableName).executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll(Class<T> clazz) {
		return getSession().createCriteria(clazz).list();
	}

	protected Criteria createEntityCriteria() {
		return getSession().createCriteria(persistentClass);
	}
}

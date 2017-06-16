package cn.itcast.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.itcast.dao.BaseDao;
import cn.itcast.entity.Customer;
import cn.itcast.entity.LinkMan;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {

	private Class<T> classEntity;
	
	public BaseDaoImpl() {
//		对classEntity赋值
//		1、获取当前运行的类,BaseDaoImpl的子类
//		System.out.println(this.getClass());
//		2、获取当前运行类的父类中的  参数化类型
		Type type = this.getClass().getGenericSuperclass();  //BaseDaoImpl<T>
//		3、获取真实的类型
		Type[] types = ((ParameterizedType)type).getActualTypeArguments();
//		4、对classEntity赋值
		classEntity = (Class<T>) types[0];
	}
	
	
	@Override
	public void save(T t) {
		this.getHibernateTemplate().save(t);
	}

	@Override
	public Integer findTotalCount(DetachedCriteria dc) {
		
		//设置投影
		dc.setProjection(Projections.rowCount());
		List<Long> list = (List<Long>) this.getHibernateTemplate().findByCriteria(dc);
		return list.get(0).intValue();
	}

	@Override
	public List<T> findByPage(int i, Integer pageSize, DetachedCriteria dc) {
		
		//取消投影
		dc.setProjection(null);
		dc.setResultTransformer(dc.ROOT_ENTITY);
		List<T> list = (List<T>) this.getHibernateTemplate().findByCriteria(dc, i, pageSize);
		
		return list;
	}

	@Override
	public T findOne(Integer lkmId) {
		return this.getHibernateTemplate().get(classEntity, lkmId);
	}

	@Override
	public void update(T t) {

		this.getHibernateTemplate().update(t);
	}

	@Override
	public void delete(T t) {
		this.getHibernateTemplate().delete(t);
	}

	@Override
	public List<T> findAll() {
		List<T> list = (List<T>) this.getHibernateTemplate().find("from "+classEntity.getSimpleName());
		 
		return list;
	}
	@Override
	public List<T> findAll(DetachedCriteria dc) {
		List<T> list = (List<T>) this.getHibernateTemplate().findByCriteria(dc);
		
		return list;
	}

	public List<Customer> findby(DetachedCriteria dc) {
		
		return (List<Customer>) this.getHibernateTemplate().findByCriteria(dc);
	}
	
}

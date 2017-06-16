package cn.itcast.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.itcast.entity.Customer;
import cn.itcast.entity.LinkMan;

public interface BaseDao<T> {

	public void save(T t);

	Integer findTotalCount(DetachedCriteria dc);

	List<T> findByPage(int i, Integer pageSize, DetachedCriteria dc);

	T findOne(Integer lkmId);

	public void update(T t);

	public void delete(T t);
	
	List<T> findAll();
	List<T> findAll(DetachedCriteria dc);
	List<Customer> findby(DetachedCriteria dc);
}

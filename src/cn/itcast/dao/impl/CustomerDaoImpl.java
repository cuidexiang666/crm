package cn.itcast.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.itcast.dao.CustomerDao;
import cn.itcast.entity.BaseDict;
import cn.itcast.entity.Customer;

public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements CustomerDao {

	@Override
	public List<BaseDict> findBaseDictByTypeCode(String typeCode) {
		List<BaseDict> list = (List<BaseDict>) this.getHibernateTemplate().find("from BaseDict where dict_type_code  = ?", typeCode);
		return list;
	}
	/*
	@Override
	public void save(Customer customer) {

		this.getHibernateTemplate().save(customer);
	}

	@Override
	public int findTotalCount(DetachedCriteria dc) {
		
		dc.setProjection(Projections.rowCount());
		List<Long> list = (List<Long>) this.getHibernateTemplate().findByCriteria(dc);
		return list.get(0).intValue();
	}

	@Override
	public List<Customer> findByPage(int i, int pageSize, DetachedCriteria dc) {
		dc.setProjection(null);
		List<Customer> list = (List<Customer>) this.getHibernateTemplate().findByCriteria(dc, i, pageSize);
		return list;
	}

	@Override
	public Customer findOne(Integer custId) {
		Customer customer = this.getHibernateTemplate().get(Customer.class, custId);
		return customer;
	}

	@Override
	public void update(Customer customer) {
		this.getHibernateTemplate().update(customer);
		
	}

	@Override
	public void delete(Customer existCustomer) {

		this.getHibernateTemplate().delete(existCustomer);
	}

	@Override
	public List<Customer> findAll() {
		List<Customer> list = (List<Customer>) this.getHibernateTemplate().find("from Customer");
		 
		return list;
	}
*/

	
	
	
}

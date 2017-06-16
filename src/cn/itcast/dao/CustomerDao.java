package cn.itcast.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.itcast.entity.BaseDict;
import cn.itcast.entity.Customer;

public interface CustomerDao extends BaseDao<Customer> {

	List<BaseDict> findBaseDictByTypeCode(String typeCode);
//
//	void save(Customer customer);
//
//	int findTotalCount(DetachedCriteria dc);
//
//	List<Customer> findByPage(int i, int pageSize, DetachedCriteria dc);
//
//	Customer findOne(Integer custId);
//
//	void update(Customer customer);
//
//	void delete(Customer existCustomer);
//
//	List<Customer> findAll();

	


}

package cn.itcast.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.itcast.entity.BaseDict;
import cn.itcast.entity.Customer;
import cn.itcast.utils.PageBean;

public interface CustomerService {

	List<BaseDict> findBaseDictByTypeCode(String typeCode);

	void save(Customer customer);

	PageBean findByList(Integer currentPage, DetachedCriteria dc);

	Customer findOne(Integer custId);

	void update(Customer customer);

	void delete(Customer existCustomer);

	List<Customer> findAll();
	


	List<Customer> findByPage(int i, Integer rows, DetachedCriteria dc);

	Integer findTotal(DetachedCriteria dc);


}

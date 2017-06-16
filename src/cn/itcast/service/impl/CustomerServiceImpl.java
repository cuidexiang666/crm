package cn.itcast.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.itcast.dao.CustomerDao;
import cn.itcast.dao.UserDao;
import cn.itcast.entity.BaseDict;
import cn.itcast.entity.Customer;
import cn.itcast.service.CustomerService;
import cn.itcast.utils.PageBean;

public class CustomerServiceImpl implements CustomerService{

	private CustomerDao customerDao ;

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	@Override
	public List<BaseDict> findBaseDictByTypeCode(String typeCode) {
		return customerDao.findBaseDictByTypeCode(typeCode);
	}

	@Override
	public void save(Customer customer) {
		customerDao.save(customer);
	}

	@Override
	public PageBean findByList(Integer currentPage, DetachedCriteria dc) {
		
		PageBean pageBean = new PageBean();
		pageBean.setCurrentPage(currentPage);
		
		int pageSize = 3;
		pageBean.setPageSize(pageSize);
		
		int totalCount = customerDao.findTotalCount(dc);
		pageBean.setTotalCount(totalCount);
		int totalPage = (int) Math.ceil(1.0*totalCount/pageBean.getPageSize());
		/*int totalPage = 0;
		if (totalCount%pageSize==0) {
			totalPage = totalCount/pageSize;
		}else {
			totalPage = totalCount/pageSize+1;
		}*/
		pageBean.setTotalPage(totalPage);
		List<Customer> list = customerDao.findByPage((currentPage-1)*pageSize,pageSize,dc);
		pageBean.setList(list);
		
		return pageBean;
	}

	@Override
	public Customer findOne(Integer custId) {
		Customer existCustomer = customerDao.findOne(custId);
		return existCustomer;
	}

	@Override
	public void update(Customer customer) {
		customerDao.update(customer);
		
	}

	@Override
	public void delete(Customer existCustomer) {
		customerDao.delete(existCustomer);
		
	}

	@Override
	public List<Customer> findAll() {
		return customerDao.findAll();
	}

	@Override
	public List<Customer> findByPage(int i, Integer rows, DetachedCriteria dc) {
		return customerDao.findByPage(i,rows,dc);
	}

	@Override
	public Integer findTotal(DetachedCriteria dc) {
		return customerDao.findTotalCount(dc);
	}


	


	
	
}

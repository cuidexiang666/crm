package cn.itcast.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.entity.BaseDict;
import cn.itcast.entity.Customer;
import cn.itcast.service.CustomerService;

public class EasyUIAction extends ActionSupport implements ModelDriven<Customer> {

	private CustomerService customerService;
	private Customer customer = new Customer();
	
	@Override
	public Customer getModel() {
		return customer;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	private Integer page;
	private Integer rows;
	
	public void setPage(Integer page) {
		this.page = page;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public void list() throws Exception {
		DetachedCriteria dc = DetachedCriteria.forClass(Customer.class);
		
		if (StringUtils.isNotBlank(customer.getCustName())) {
			dc.add(Restrictions.like("custName", customer.getCustName(),MatchMode.ANYWHERE));
		}
		
		Integer total = customerService.findTotal(dc);
		List<Customer> list = customerService.findByPage((page-1)*rows,rows,dc);	
		
		String jsonString = JSON.toJSONString(list,SerializerFeature.DisableCircularReferenceDetect);
		write(jsonString);
	}

	public void write(String jsonString){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("aplication/json;charset=utf-8");
		try {
			response.getWriter().write(jsonString);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
}

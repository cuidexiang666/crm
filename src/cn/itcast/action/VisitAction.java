package cn.itcast.action;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;

import cn.itcast.entity.Customer;
import cn.itcast.entity.LinkMan;
import cn.itcast.entity.User;
import cn.itcast.entity.Visit;
import cn.itcast.service.CustomerService;
import cn.itcast.service.UserService;
import cn.itcast.service.VisitService;
import cn.itcast.utils.PageBean;

public class VisitAction extends ActionSupport implements ModelDriven<Visit> {

	private VisitService visitService;
	private CustomerService customerService;
	private UserService userService ;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	public void setVisitService(VisitService visitService) {
		this.visitService = visitService;
	}

	private Visit visit = new Visit();
	@Override
	public Visit getModel() {
		return visit;
	}
	
	public String toadd() throws Exception {
		
		List<Customer> list = customerService.findAll();
		ServletActionContext.getRequest().setAttribute("list", list);
		return "add";
	}
	
	public void findAllUsers() throws Exception {
		
		List<User> list = userService.findAllUsers();
		String jsonString = JSON.toJSONString(list);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().write(jsonString);
	}
	
	public String save() throws Exception {
		
		visitService.save(visit); 
		return SUCCESS;
	}
	
	private Integer currentPage = 1;
	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public String list() throws Exception {
		
		DetachedCriteria dc = DetachedCriteria.forClass(Visit.class);
		
		//筛选客户名称
		if (visit.getCustomer()!=null&&StringUtils.isNotBlank(visit.getCustomer().getCustName())) {
			
			// 给customer.custName的customer起别名，目的是告诉hibernate customer现在是一个对象
			dc.createAlias("customer", "test");
			
			dc.add(Restrictions.like("test.custName", visit.getCustomer().getCustName(), MatchMode.ANYWHERE));
		}
		
		
		//筛选联系人
		if (StringUtils.isNoneBlank(visit.getVisitContent())) {
			dc.add(Restrictions.like("visitContent", visit.getVisitContent(), MatchMode.ANYWHERE));
		}
		
		
		
		PageBean pageBean = visitService.findByList(currentPage,dc);
		ValueStack vs = ServletActionContext.getContext().getValueStack();
		vs.set("pageBean", pageBean);
		
		return "list";
	}
}




















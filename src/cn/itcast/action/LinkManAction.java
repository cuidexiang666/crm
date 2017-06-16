package cn.itcast.action;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;
import com.sun.org.apache.bcel.internal.generic.DCMPG;

import cn.itcast.entity.Customer;
import cn.itcast.entity.LinkMan;
import cn.itcast.service.CustomerService;
import cn.itcast.service.LinkManService;
import cn.itcast.utils.PageBean;

public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan> {

	private LinkManService linkManService;
	private CustomerService customerService;
	
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	public void setLinkManService(LinkManService linkManService) {
		this.linkManService = linkManService;
	}

	private LinkMan linkman = new LinkMan();
	public LinkMan getModel() {
		return linkman;
	}

	public String toadd() throws Exception {
		List<Customer> list = customerService.findAll();
		ValueStack vs = ServletActionContext.getContext().getValueStack();
		vs.set("list", list);
		return "add";
	}
	
	public String save() throws Exception {
		
		linkManService.save(linkman);
		
		return SUCCESS;
	}
	
	Integer currentPage = 1;
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	
	private String custName;
	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String list() throws Exception {
		
		DetachedCriteria dc = DetachedCriteria.forClass(LinkMan.class);
		
		//筛选客户名称
		if (StringUtils.isNotBlank(custName)) {
			
			// 给customer.custName的customer起别名，目的是告诉hibernate customer现在是一个对象
			dc.createAlias("customer", "test");
			
			dc.add(Restrictions.like("test.custName", custName, MatchMode.ANYWHERE));
		}
		
		
		//筛选联系人
		if (StringUtils.isNoneBlank(linkman.getLkmName())) {
			dc.add(Restrictions.like("lkmName", linkman.getLkmName(), MatchMode.ANYWHERE));
		}
		
		
		
		PageBean pageBean = linkManService.findByList(currentPage,dc);
		ValueStack vs = ServletActionContext.getContext().getValueStack();
		vs.set("pageBean", pageBean);
		
		return "list";
	}
	
	public String toupdate() throws Exception {
		
		LinkMan linkMan = linkManService.findOne(linkman.getLkmId());
		ValueStack vs = ServletActionContext.getContext().getValueStack();
		vs.set("linkman", linkMan);
		
		List<Customer> list = customerService.findAll();
		ServletActionContext.getRequest().setAttribute("list", list);
		
		return "update";
	}
	
	public String update() throws Exception {
		
		LinkMan man = linkManService.findOne(linkman.getLkmId());
		if (man!=null) {
			man.setLkmGender(linkman.getLkmGender());
			man.setLkmMobile(linkman.getLkmMobile());
			man.setLkmName(linkman.getLkmName());
			man.setLkmPhone(linkman.getLkmPhone());
			man.setCustomer(linkman.getCustomer());
			
			linkManService.update(man);
			
			return "tolist";
		}else {
			return "error";
		}
	}
	
	public String delete() throws Exception {
		
		LinkMan linkmanExist = linkManService.findOne(linkman.getLkmId());
		if (linkmanExist!=null) {
			linkManService.delete(linkmanExist);
			return "tolist";
		}else {
			return "error";
		}
		
	}
	
}
















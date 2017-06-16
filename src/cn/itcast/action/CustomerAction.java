package cn.itcast.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.aspectj.util.FileUtil;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.entity.BaseDict;
import cn.itcast.entity.Customer;
import cn.itcast.entity.User;
import cn.itcast.service.BaseDictService;
import cn.itcast.service.CustomerService;
import cn.itcast.service.UserService;
import cn.itcast.utils.PageBean;

public class CustomerAction extends ActionSupport implements ModelDriven<Customer>  {

	private CustomerService customerService;
	
	private BaseDictService baseDictService;

	public void setBaseDictService(BaseDictService baseDictService) {
		this.baseDictService = baseDictService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	private String typeCode;
	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String toadd() throws Exception {
		return "toadd";
	}
	
	private Customer customer = new Customer();

	public Customer getModel() {
		return customer;
	}
	
	private File upload;
	private String uploadFileName;
	private String uploadContextType;
	
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public String getUploadContextType() {
		return uploadContextType;
	}

	public void setUploadContextType(String uploadContextType) {
		this.uploadContextType = uploadContextType;
	}

	public File getUpload() {
		return upload;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}

	/**
	 *获取数据字典
	 * @param typeCode
	 * @return
	 * @throws Exception
	 */           
	public String findBaseDictByTypeCode() throws Exception {
		
		List<BaseDict> list = baseDictService.findBaseDictByTypeCode(typeCode);
		String jsonString = JSON.toJSONString(list);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().write(jsonString);
		return NONE;
	}
	
	public String save() throws Exception {
		
			/**
			 * 文件上传
			 */
			File saveFile = new File("F:\\uploadFile\\"+uploadFileName);
			
			FileUtil.copyFile(upload, saveFile);
		
			//把存放文件的路径保存到数据库中
			customer.setFilePath("F:\\uploadFile\\"+uploadFileName);
			
		customerService.save(customer);
		
		return "success";
	}

	private Integer currentPage = 1;
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public String list() throws Exception {
		
		DetachedCriteria dc = DetachedCriteria.forClass(Customer.class);
		
		if (StringUtils.isNotBlank(customer.getCustName())) {
			dc.add(Restrictions.like("custName", customer.getCustName(), MatchMode.ANYWHERE));
		}
		
		PageBean pagebean = customerService.findByList(currentPage,dc);
		ServletActionContext.getRequest().getSession().setAttribute("pagebean", pagebean);
		
		return "list";
	}
	
	
	public String toupdate() throws Exception {
		
		Customer existCustomer = customerService.findOne(customer.getCustId());
		ServletActionContext.getRequest().setAttribute("existCustomer", existCustomer);
		
		return "toupdate";
	}
	
	public String update() throws Exception {
		Customer existCustomer = customerService.findOne(customer.getCustId());
		if (existCustomer!=null) {
			existCustomer.setCustLinkman(customer.getCustLinkman());
			existCustomer.setCustMobile(customer.getCustMobile());
			existCustomer.setCustName(customer.getCustName());
			existCustomer.setCustPhone(customer.getCustPhone());
			existCustomer.setLevelBaseDict(customer.getLevelBaseDict());
			existCustomer.setSourceBaseDict(customer.getSourceBaseDict());
		customerService.update(existCustomer);
		return "tolist";
		}else {
			return "error";
		}
	}
	
	public String delete() throws Exception {
		Customer existCustomer = customerService.findOne(customer.getCustId());
		
		if (existCustomer!=null) {
			customerService.delete(existCustomer);
			return "tolist";
		}else {
			return "error";
		}
	}
	
	/**
	 * 文件下载
	 */
	
	private String filePath;
	
	
	/**
	 * 获得文件类型
	 */
	public String getContentType(){
		String type = ServletActionContext.getServletContext().getMimeType(filePath);
		return type;
	}
	
	/**
	 * 打开类型+文件
	 * @return
	 * @throws Exception
	 */
	public String getContentDisposition(){
		
		String fileName = filePath.substring(filePath.lastIndexOf("\\")+1);
		try {
			return "attachment;fileName="+(new String(fileName.getBytes(),"iso-8859-1"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 获取文件输入流
	 * @return
	 * @throws Exception
	 */
	
	public InputStream getInputStream(){
		
		try {
			return new FileInputStream(filePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public String download() throws Exception {
		Customer customerExist = customerService.findOne(customer.getCustId());
		if (customerExist!=null&&customerExist.getFilePath()!=null) {
			filePath = customerExist.getFilePath();
			return "download";
		}
		return "error";
	}
	
}

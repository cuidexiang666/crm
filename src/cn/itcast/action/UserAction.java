package cn.itcast.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.entity.User;
import cn.itcast.service.UserService;
import cn.itcast.utils.Md5Util;

public class UserAction extends ActionSupport implements ModelDriven<User>{

	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	private User user = new User();
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	private String repassword;
	
	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}
	
	@Override
	public User getModel() {
		return user;
	}
	
	public String toregister() throws Exception {
		
		return "toregister";
	}
	
	public String save() throws Exception {
		
		if (!user.getPassword().equals(repassword)) {
			ServletActionContext.getRequest().setAttribute("msg", "输入的密码不相同");
			ServletActionContext.getRequest().setAttribute("user", user);
			return "toregister";
		}
		
		Boolean b = userService.findUserByUsercode(user);
		if (b) {
			ServletActionContext.getRequest().setAttribute("msg", "用户名已存在");
			ServletActionContext.getRequest().setAttribute("user", user);
			
			return "toregister";
		}
		String encode = Md5Util.encode(user.getPassword());
		user.setPassword(encode);
		userService.save(user);
		
		return "tologin";
	}

	public String login() throws Exception {
		
		User existUser = userService.findUser(user);
		if (user==null) {
			ServletActionContext.getRequest().setAttribute("msg", "用户名或密码错误");
			return "tologin";
		}else {
			ServletActionContext.getRequest().getSession().setAttribute("user", existUser);
			return "index";
		}
	}
	
}

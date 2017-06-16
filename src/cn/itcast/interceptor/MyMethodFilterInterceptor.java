package cn.itcast.interceptor;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class MyMethodFilterInterceptor extends MethodFilterInterceptor {

	@Override
	protected String doIntercept(ActionInvocation Invocation) throws Exception {
		
		Object object = ServletActionContext.getRequest().getSession().getAttribute("user");
		if (object!=null) {
			Invocation.invoke();
		}
		return "tologin";
	}

}

package com.pgis.interceptor;

import java.util.Map;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
/*
 * 用户自定义拦截器
 */
public class AuthInterceptor extends MethodFilterInterceptor {
	/* 创建的AuthInterceptor继承 MethodFilterInterceptor类，可以实现在struts.xml中对action中的方法不执行拦截 :
	<param name="excludeMethods">方法，如:login</param>  */
	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		ActionContext ac=ActionContext.getContext();
		if(ac.getSession().get("existEmployee")!=null){
			String result=invocation.invoke();//invocation.invoke()返回的值是方法名，如login,saveUI,findAll
			System.out.println("拦截器中invocation.invoke()返回的值是方法名："+result);
			return result;
		}else{
			System.out.println("AuthInterceptor拦截器拦截了您的操作，未登入或账号密码有误");
			return "login";
		}
	}

}

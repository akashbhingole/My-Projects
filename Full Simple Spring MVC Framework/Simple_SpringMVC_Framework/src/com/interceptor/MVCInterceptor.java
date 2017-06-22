
package com.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.services.factory.Factory;
import com.services.persistence.HibernateTemplate;
import com.services.session.HttpSessionHandler;

public class MVCInterceptor implements HandlerInterceptor {

	/**
	 * @param request,response,object,exception
	 * @this void method override from HandlerInterceptor interface for getting lisner of afterCompletion request
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,	HttpServletResponse response, Object ob, Exception ex)	throws Exception {
		HibernateTemplate hibernateTemplate = Factory.getInstance().getInstanceProxyService("hibernateTemplate");
		hibernateTemplate.closeSession(true);
	}

	/**
	 * @param request,response,object,model
	 * @this void method override from HandlerInterceptor interface for getting lisner of postHandle request
	 */
	@Override
	public void postHandle(HttpServletRequest request,HttpServletResponse response, Object obj, ModelAndView model)
			throws Exception {

	}

	/**
	 * @param request ,response,object
	 * @description this boolean method override from HandlerInterceptor  interface for getting lisner of preHandle request
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object obj) throws Exception {
		HttpSessionHandler httpSessionHendler = Factory.getInstance().getInstanceProxyService("httpSessionHandler");
		httpSessionHendler.setHttpSession(request);
		return true;
	}

	/**
	 * @param request  ,response,object
	 * @description this method for getting lisner of handle all Exception  request
	 * @return particular jsp if any error/exception occur in application
	 */
	@ExceptionHandler(Throwable.class)
	public ModelAndView handleAllException(Throwable ex) {
		HibernateTemplate hibernateTemplate = Factory.getInstance().getInstanceProxyService("hibernateTemplate");
		hibernateTemplate.closeSession(false);
		ModelAndView model = new ModelAndView("error/generic_error");
		model.addObject("globalError", ex.getMessage());
		return model;
	}

}
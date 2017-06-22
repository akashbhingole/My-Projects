package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bo.login.LoginBO;
import com.model.login.Login;
import com.services.factory.Factory;
import com.services.session.HttpSessionHandler;

@Controller
public class LoginController {

	/*
	 * Below code for initiallize log4j for this class
	 */
	private static final Logger logger = Logger.getLogger(LoginController.class);

	/**
	 * @param user
	 * @param result
	 * @return login view
	 * @description this method for response to Login view
	 */

	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public ModelAndView loginPage(@ModelAttribute("employee") Login login, BindingResult result) {
		logger.info("Inside loginPage method in LoginController class");
		logger.info("Exit from loginPage method in LoginController class");
		return new ModelAndView("login", "command", new Login());
	}

	/**
	 * @param request
	 * @param response
	 * @param user
	 * @param result
	 * @return login view
	 * @description this method for remove all session and response to Login view
	 */
	@RequestMapping(value = "/logout.do", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response,
							   @ModelAttribute("login") Login login, BindingResult result) {
		logger.info("Inside logout method in LoginController class");

		HttpSessionHandler session=Factory.getInstance().getInstanceProxyService("httpSessionHandler");
		if (request.getSession().getAttribute("loginUser") != null) {
			session.removeAttribute("loginUserId");
		}
		logger.info("Exit from logout method in LoginController class");
		return new ModelAndView("login", "command", new Login());
	}

	/**
	 * @param request
	 * @param response
	 * @param user
	 * @param result
	 * @return next view after login or login view
	 * @description this method for check user login and return next view or login view
	 */
	@RequestMapping(value = "/logon.do", method = RequestMethod.POST)
	public ModelAndView logon(HttpServletRequest request, HttpServletResponse response,
							  @ModelAttribute("user") Login login, BindingResult result) {
		logger.info("Inside logon method in LoginController class");

		LoginBO loginbo=Factory.getInstance().getInstanceService(LoginBO.class);
		HttpSessionHandler session=Factory.getInstance().getInstanceProxyService("httpSessionHandler");
		Login u = loginbo.getLoginDetails(login.getEmailId(), login.getPassword());
		if (u != null) {
			session.setAttribute("loginUserId",u.getDoctorId());
			return new ModelAndView("home");
		} else {
			request.setAttribute("authontication", "fail");
		}
		logger.info("Exit from logon method in LoginController class");
		return new ModelAndView("login", "command", new Login());
	}

}

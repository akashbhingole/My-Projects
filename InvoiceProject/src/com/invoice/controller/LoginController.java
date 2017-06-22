package com.invoice.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.invoice.service.InvoiceService;

@Controller
@RequestMapping("/login")
public class LoginController {

	private static final Logger LOGGER = Logger.getLogger(LoginController.class);
	
	@Autowired
	private InvoiceService employeeService;
	
	
	@RequestMapping(value="/verify",method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> verifyLogin(HttpServletRequest request) {
		LOGGER.info("Inside verifyLogin method in LoginController");
		String result = "";
		try {
			String userName = request.getParameter("userName");
			String password = request.getParameter("password");
			
			boolean isValidUser = false;
			if(userName!=null && userName.isEmpty()==false && password!=null && password.isEmpty()==false) {
				isValidUser = employeeService.isValidUser(userName,password);
			}
			 
			JSONObject responseObj = new JSONObject();
			if(isValidUser) {
				responseObj.put("flag", "success");
				responseObj.put("userName", userName);
				request.getSession(true).setAttribute("userName", userName);
			}
			else {
				responseObj.put("flag", "fail");
			}
			result = responseObj.toString();
		}
		catch(Exception e) {
			LOGGER.error("Exception in verifyLogin method in LoginController ", e);
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOGGER.info("Exit from verifyLogin method in LoginController");
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/logout",method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> logout(HttpServletRequest request) {
		LOGGER.info("Inside logout method in LoginController");
		String response = "";
		JSONObject responsObj =new JSONObject();
		try {
		    if(request.getSession()!=null) {
		    	request.getSession(false).invalidate();  
			  	responsObj.put("flag", "success");
			  	response = responsObj.toString();
		    }
		    else {
		    	responsObj.put("flag", "fail");
			  	response = responsObj.toString();
		    }
		}
		catch(Exception e) {
			LOGGER.error("Exception in logout method in LoginController ", e);
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}	
		LOGGER.info("Exit from logout method in LoginController");
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
}

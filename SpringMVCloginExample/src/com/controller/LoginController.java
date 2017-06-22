package com.controller;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bo.LoginBO;
import com.model.UserDetails;


@Controller
/**
 * This class is used to handle all the rest services
 * */
public class LoginController {

	@Autowired
	private LoginBO loginBO;

	/**
	 * This is used to get the default login page
	 * */
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public ModelAndView displayLogin(UserDetails userDetails) {
		ModelAndView model = new ModelAndView("login");
		model.addObject("userDetails", userDetails);
		return model;
	}
		
		
	/**
	 * This method is used to check username and password for the validation
	 * @param request
	 * @return response
	 * @throws Exception 
	 * */
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public @ResponseBody String executeLogin(HttpServletRequest request, @ModelAttribute("userDetails")UserDetails userDetails)  {
		JSONObject responseObj = new JSONObject();
		try {
			boolean isValidUser = loginBO.isValidUser(userDetails.getUsername(), userDetails.getPassword());
			if(isValidUser) {
				responseObj.put("message", "Welcome " + userDetails.getUsername());
				responseObj.put("name", userDetails.getUsername());
				responseObj.put("address", userDetails.getAddress());
				responseObj.put("email", userDetails.getEmailId());
				responseObj.put("mobileNumber", userDetails.getMobileNumber());
				responseObj.put("flag", true);
			}
			else {
				responseObj.put("message", "Invalid credentials!!");
				responseObj.put("flag", false);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			try {
				responseObj.put("message", "System under maintainance");
				responseObj.put("flag", false);
			}
			catch (JSONException e1) {
				e1.printStackTrace();
			}
		}
		return responseObj.toString();
	}
}

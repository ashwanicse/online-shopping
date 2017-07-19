package com.niit.onlineshopping.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.niit.onlineshopping.model.UserModel;
import com.niit.shoppingbackend.dto.User;
import com.nit.shoppingbackend.dao.UserDAO;

@ControllerAdvice
public class GlobalController {

	@Autowired
	private HttpSession session;
	
	@Autowired
	private UserDAO userDAO;
	
	private UserModel userModel;
	
	@ModelAttribute("userModel")
	public UserModel getUserModel(){
		
		if(session.getAttribute("userModel") == null){
			//add the user model
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User user = userDAO.getByEmail(auth.getName());  // user name is email in spring security
			if(user != null){
				//create the user model object to pass the details
				userModel = new UserModel();
				userModel.setId(user.getId());
				userModel.setEmail(user.getEmail());
				userModel.setRole(user.getRole());
				userModel.setFullName(user.getFirstName()+" "+user.getLastName());
				
				if(userModel.getRole().equals("USER")){
					//set the cart if user is buyer
					userModel.setCart(user.getCart());
				}
				
				//set the USERMODEL in the session
				session.setAttribute("userModel", userModel);
			}
		}
		return (UserModel)session.getAttribute("userModel");
	}
}

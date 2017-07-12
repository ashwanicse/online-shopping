package com.niit.onlineshopping.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.stereotype.Component;

import com.niit.onlineshopping.model.RegisterModel;
import com.niit.shoppingbackend.dto.Address;
import com.niit.shoppingbackend.dto.Cart;
import com.niit.shoppingbackend.dto.User;
import com.nit.shoppingbackend.dao.UserDAO;

@Component
public class RegisterHandler {
	
	@Autowired
	private UserDAO userDAO;
   
	public RegisterModel init(){
		return new RegisterModel();
	}
	
	public void addUser(RegisterModel registerModel,User user){
		registerModel.setUser(user);
	}
	
	public void addBilling(RegisterModel registerModel,Address billing){
		registerModel.setBilling(billing);
	}
	
	public String saveAll(RegisterModel registerModel){
		String transitionValue = "success";
		
		/*---------Fetch the User--------*/
		User user = registerModel.getUser();
		if(user.getRole().equals("USER")){
			Cart cart = new Cart();
			cart.setUser(user);
			user.setCart(cart);
		}
		//save the user
		userDAO.addUser(user);
		
		
		/*---------Fetch the address--------*/
		
		Address billing = registerModel.getBilling();		
		billing.setUser(user);
		billing.setBilling(true);
		
		userDAO.addAddress(billing);
		
		
		return transitionValue;
	}
	
	public String validateUser(User user, MessageContext messageContext){
		
		String transitionValue="success";
		
		if(! user.getPassword().equals(user.getConfirmPassword())){
			messageContext.addMessage(
					                    new MessageBuilder()
					                    .error()
					                    .source("confirmPassword")
					                    .defaultText("Password does not match the confirm password !")
					                    .build());
			transitionValue = "failure";
		}
		
		// check the uniqueness for the email
		if(userDAO.getByEmail(user.getEmail()) !=null){
			messageContext.addMessage(
                    new MessageBuilder()
                    .error()
                    .source("email")
                    .defaultText("Email address is already used !")
                    .build());
		}
		
		return transitionValue;
	}
}

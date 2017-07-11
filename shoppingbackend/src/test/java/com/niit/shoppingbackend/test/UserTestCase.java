package com.niit.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.shoppingbackend.dto.Address;
import com.niit.shoppingbackend.dto.Cart;
import com.niit.shoppingbackend.dto.User;
import com.nit.shoppingbackend.dao.UserDAO;

public class UserTestCase {
	
	private static AnnotationConfigApplicationContext context;
	private static UserDAO userDAO;
	private User user = null;
	private Cart cart = null;
	private Address address = null;
	
	@BeforeClass
	public static void init(){
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit.shoppingbackend");
		context.refresh();
		userDAO = (UserDAO)context.getBean("userDAO");
	}
	
/*	@Test
	public void testAddUser(){
		
		user = new User();
		user.setFirstName("Ashwani");
		user.setLastName("kumar");
		user.setEmail("ashwani@gmail.com");
		user.setContactNumber("9971421629");
		user.setRole("USER");
		user.setPassword("ash123");
		
		assertEquals("Successfully added the user",true, userDAO.addUser(user));
		
		address = new Address();
		address.setAddressLineOne("c56,sector 62");
		address.setAddressLineTwo("D5, sector 63");
		address.setCity("Noida");
		address.setState("UP");
		address.setCountry("India");
		address.setPostalCode("382345");
		address.setBilling(true);
		
		address.setUserId(user.getId());
		
		assertEquals("Address is added", true, userDAO.addAddress(address));
		
		if(user.getRole().equals("USER")){
			cart = new Cart();
			cart.setUser(user);
			
			assertEquals("Cart added",true,userDAO.addCart(cart));
			
			//add a shipping address for this user
			address = new Address();
			address.setAddressLineOne("c56,Gurgaon");
			address.setAddressLineTwo("D5, gurgaon");
			address.setCity("Gurgaon");
			address.setState("Haryana");
			address.setCountry("India");
			address.setPostalCode("283111");
			address.setShipping(true); 
			
			address.setUserId(user.getId());
			
			assertEquals("ShippingAddress is added", true, userDAO.addAddress(address));
		}
	}*/
	
	/*@Test
	public void testAddUser(){
		
		user = new User();
		user.setFirstName("Ashwani");
		user.setLastName("kumar");
		user.setEmail("ashwani@gmail.com");
		user.setContactNumber("9971421629");
		user.setRole("USER");
		user.setPassword("ash123");
				
		if(user.getRole().equals("USER")){
			cart = new Cart();
			cart.setUser(user);		
			//attach cart with the user
			user.setCart(cart);
		}
		
		assertEquals("Successfully added the user",true, userDAO.addUser(user));
	}*/
	
	/*@Test
	public void testUpdateCart(){
		
		//fetch the user by email
		user = userDAO.getByEmail("ashwani@gmail.com");
		
		//get the cart of this user
		cart = user.getCart(); // this is possible only if we are using bidirectional mapping 
		cart.setGrandTotal(3333);
		cart.setCartLines(2);
		
		assertEquals("Successfully updates the cart",true, userDAO.updateCart(cart));
		
	}*/
	
	/*@Test
	public void testAddAddress(){
		
		//We need an user
		user = new User();
		user.setFirstName("Ashwani");
		user.setLastName("kumar");
		user.setEmail("ashwani@gmail.com");
		user.setContactNumber("9971421629");
		user.setRole("USER");
		user.setPassword("ash123");
		
		assertEquals("Successfully added the user",true, userDAO.addUser(user));
       
		//add address
		
		address = new Address();
		address.setAddressLineOne("c56,sector 62");
		address.setAddressLineTwo("D5, sector 63");
		address.setCity("Noida");
		address.setState("UP");
		address.setCountry("India");
		address.setPostalCode("382345");
		address.setBilling(true);
	
		//attached the user to the address
		address.setUser(user);
		
		assertEquals("Address is added", true, userDAO.addAddress(address));
		
		
		//add shipping address
		
		address = new Address();
		address.setAddressLineOne("c56,Gurgaon");
		address.setAddressLineTwo("D5, gurgaon");
		address.setCity("Gurgaon");
		address.setState("Haryana");
		address.setCountry("India");
		address.setPostalCode("283111");
		address.setShipping(true); 
		
		address.setUser(user);
		
		assertEquals("ShippingAddress is added", true, userDAO.addAddress(address));
		
	}*/
	
	@Test
	public void testAddAddress(){
		
	}

}

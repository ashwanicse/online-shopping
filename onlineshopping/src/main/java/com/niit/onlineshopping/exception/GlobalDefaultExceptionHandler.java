package com.niit.onlineshopping.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ModelAndView handlerNoHandlerFoundException(){
		ModelAndView mv=new ModelAndView("error");
		mv.addObject("errorTitle","The page is not constructed.");
		mv.addObject("errorDescription","The page you are looking for is not available now !");
		mv.addObject("title","404 Error Page");
		return mv;
	}
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ModelAndView handleProductNotFoundException(){
		ModelAndView mv=new ModelAndView("error");
		mv.addObject("errorTitle","Product not available.");
		mv.addObject("errorDescription","The Product you are looking for is not available right now !");
		mv.addObject("title","Product Not Found !");
		return mv;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(Exception ex){
		ModelAndView mv=new ModelAndView("error");
		mv.addObject("errorTitle","Looking for something?!");
		mv.addObject("errorDescription","We're sorry. The Web address you entered is not a functioning page on our site. !<br/><hr/>"+ex.toString());
		mv.addObject("title","Error !");
		return mv;
	}


}

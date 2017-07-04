package com.niit.onlineshopping.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.niit.shoppingbackend.dto.Category;
import com.niit.shoppingbackend.dto.Product;
import com.nit.shoppingbackend.dao.CategoryDAO;
import com.nit.shoppingbackend.dao.ProductDAO;

@Controller
public class PageController {
	
	private static final Logger logger = LoggerFactory.getLogger(PageController.class);

	@Autowired
	private CategoryDAO categoryDAO;

	@Autowired
	private ProductDAO productDAO;

	@RequestMapping(value = { "/", "/home", "/index" })
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Home");

		logger.info("Inside PageController index Method - INFO");
		logger.debug("Inside PageController index Method - DEBUG");
		// passing the list of categories
		mv.addObject("categories", categoryDAO.list());
		mv.addObject("userClickHome", true);
		return mv;
	}

	@RequestMapping(value = "about")
	public ModelAndView about() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "About Us");
		mv.addObject("userClickAbout", true);
		return mv;
	}

	@RequestMapping(value = "contact")
	public ModelAndView contact() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Contact Us");
		mv.addObject("userClickContact", true);
		return mv;
	}
	/*
	 * Method to load all products and based on category
	 */

	@RequestMapping(value = "/show/all/products")
	public ModelAndView showAllProducts() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "All products");

		// passing the list of categories
		mv.addObject("categories", categoryDAO.list());
		mv.addObject("userClickAllProducts", true);
		return mv;
	}

	@RequestMapping(value = "/show/category/{id}/products")
	public ModelAndView showCategoryProducts(@PathVariable("id") int id) {
		ModelAndView mv = new ModelAndView("page");

		// CategoryDAO to fetch single category
		Category category = null;
		category = categoryDAO.get(id);
		mv.addObject("title", category.getName());

		// passing the single category
		mv.addObject("category", category);

		// passing the list of categories
		mv.addObject("categories", categoryDAO.list());
		mv.addObject("userClickCategoryProducts", true);
		return mv;
	}

	/*
	 * Viewing single product
	 */
	@RequestMapping(value = "/show/{id}/product")
	public ModelAndView showSingleProduct(@PathVariable("id") int id) {
		ModelAndView mv = new ModelAndView("page");

		Product product = productDAO.get(id);

		// update the view count
		product.setViews(product.getViews() + 1);
		productDAO.update(product);

		mv.addObject("title", product.getName());
		// passing the single product
		mv.addObject("product", product);

		mv.addObject("userClickShowProduct", true);
		return mv;
	}

	// http://localhost:8081/onlineshopping/test
	// http://localhost:8081/onlineshopping/test?greeting=Welcome to spring
	// webmvc RequestParam example
	// @RequestMapping(value = "/test")
	// public ModelAndView test(@RequestParam("greeting") String greeting) {
	/*
	 * public ModelAndView test(@RequestParam(value="greeting",required=false)
	 * String greeting) { if(greeting==null)
	 * greeting="Hey, it is default message"; ModelAndView mv = new
	 * ModelAndView("page"); mv.addObject("greeting", greeting); return mv; }
	 */

	@RequestMapping(value = "/test/{greeting}")
	public ModelAndView test(@PathVariable(value = "greeting") String greeting) {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("greeting", greeting);
		return mv;
	}

}

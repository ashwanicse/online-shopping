package com.niit.onlineshopping.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.niit.onlineshopping.utility.FileUploadUtility;
import com.niit.onlineshopping.validator.ProductValidator;
import com.niit.shoppingbackend.dto.Category;
import com.niit.shoppingbackend.dto.Product;
import com.nit.shoppingbackend.dao.CategoryDAO;
import com.nit.shoppingbackend.dao.ProductDAO;

@Controller
@RequestMapping("/manage")
public class ManagementController {
	
	private static final Logger logger = LoggerFactory.getLogger(ManagementController.class);
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private ProductDAO productDAO;

	@RequestMapping(value="/products",method=RequestMethod.GET)
	public ModelAndView showManageProducts(@RequestParam(name="operation",required=false) String operation){
		
		ModelAndView mv = new ModelAndView("page");
		
		mv.addObject("userClickManageProducts",true);
		mv.addObject("title","Manage Products");
		
		Product newProduct = new Product();
		newProduct.setSupplierId(1);
		newProduct.setActive(true);
		
		mv.addObject("product",newProduct);
		
		if(operation != null){
			if(operation.equals("product")){
				mv.addObject("message","Product Submitted Successfully");
			}
			else if(operation.equals("category")){
				mv.addObject("message","Category Submitted Successfully");
			}
		}
		
		return mv;
		
	}
	@RequestMapping(value="/{id}/product",method=RequestMethod.GET)
	public ModelAndView showEditProduct(@PathVariable int id){
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("userClickManageProducts",true);
		mv.addObject("title","Manage Products");
		
		Product newProduct = productDAO.get(id);
		//set the product fetch from database
		mv.addObject("product",newProduct);
		return mv;
	}
	
	//handling product submission
	@RequestMapping(value="/products",method=RequestMethod.POST)
	public String handleProductSubmission(@Valid @ModelAttribute("product") Product modifyProduct, BindingResult results, Model model, HttpServletRequest request){
		
		//handle image validation for new products
		if(modifyProduct.getId()==0){
		     new ProductValidator().validate(modifyProduct, results);
		}
		else{
			if(! modifyProduct.getFile().getOriginalFilename().equals("")){
				 new ProductValidator().validate(modifyProduct, results);
			}
		}
		//check if there are any error
		if(results.hasErrors()){
			model.addAttribute("userClickManageProducts", true);
			model.addAttribute("title", "Manage Products");
			model.addAttribute("message", "Validation failed for Product Submission!");
			return "page"; // if we use redirect here then error message would not display
		}
		
		logger.info(modifyProduct.toString());
		
		if(modifyProduct.getId()==0){
		//create a new product record
		productDAO.add(modifyProduct);
		}
		else{
			//update a existing product record
			productDAO.update(modifyProduct);
		}
		
		if(!modifyProduct.getFile().getOriginalFilename().equals("")){
			FileUploadUtility.uploadFile(request,modifyProduct.getFile(),modifyProduct.getCode());
		}
		
		return "redirect:/manage/products?operation=product";		
	}
	
	
	//to handle category submission
	@RequestMapping(value="/category",method=RequestMethod.POST)
	public String handleCategorySubmission(@ModelAttribute Category category){
	
		//add new category
		categoryDAO.add(category);
		return "redirect:/manage/products?operation=category";
	}
	
	
	@RequestMapping(value="/product/{id}/activation",method=RequestMethod.POST)
	@ResponseBody
	public String handleProductActivation(@PathVariable int id){
		Product product = productDAO.get(id);
		boolean isActive = product.isActive();
		product.setActive( ! product.isActive());
		productDAO.update(product);
		
		return (isActive)?"You have successfully deactivated the product with id "+product.getId() :
			              "You have successfully activated the product with id "+product.getId();
	}
	
	
	//returning category for all the request
	@ModelAttribute("categories")
	public List<Category> getCategories(){
		return categoryDAO.list();
	}
	
	@ModelAttribute("category")
	public Category getCategory(){
		return new Category();
	}
}

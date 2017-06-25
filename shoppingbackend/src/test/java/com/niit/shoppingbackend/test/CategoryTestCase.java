package com.niit.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.shoppingbackend.dto.Category;
import com.nit.shoppingbackend.dao.CategoryDAO;

public class CategoryTestCase {

	private static AnnotationConfigApplicationContext context;

	private static CategoryDAO categoryDAO;

	private static Category category;

	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit.shoppingbackend");
		context.refresh();

		categoryDAO = (CategoryDAO) context.getBean("categoryDAO");
	}

	
	 @Test public void testAddCategory(){
	 
	 category=new Category();
	 
	 category.setName("Laptop and desktop");
	 category.setDescription("Laptop is a machine.");
	 category.setImageUrl("CAT_2.png");
	 
	assertEquals("Category successfully added in table.",true,categoryDAO.add
	 (category));
	 
	 }
	 

	/*
	 * @Test public void testGetCategory(){
	 * 
	 * category=categoryDAO.get(1);
	 * assertEquals("Succesfully fetched a single category ","Computer",category
	 * .getName());
	 * 
	 * }
	 */

	/*
	 * @Test public void testUpdateCategory(){
	 * 
	 * category=categoryDAO.get(1); category.setName("PC");
	 * assertEquals("Succesfully updated a single category ",true,categoryDAO.
	 * update(category));
	 * 
	 * }
	 */

	/*
	 * @Test public void testDeleteCategory(){
	 * 
	 * category=categoryDAO.get(1);
	 * assertEquals("Succesfully deleted a single category ",true,categoryDAO.
	 * delete(category));
	 * 
	 * }
	 */

	/*
	 * @Test public void testListCategory(){
	 * 
	 * assertEquals("Succesfully fetched a list of active categories ",2,
	 * categoryDAO.list().size());
	 * 
	 * }
	 */

	/*@Test
	public void testCRUDCategory() {

		*//****************Add***************//*
		category = new Category();

		category.setName("Laptop");
		category.setDescription("Laptop is a machine.");
		category.setImageUrl("CAT_1.png");

		assertEquals("Category successfully added in table.", true, categoryDAO.add(category));

		category = new Category();

		category.setName("Mobile");
		category.setDescription("Laptop is a mobile.");
		category.setImageUrl("CAT_2.png");
		
		assertEquals("Category successfully added in table.", true, categoryDAO.add(category));
		
		
		*//*******************Update************************//*
		
		category=categoryDAO.get(1); category.setName("PC");
		 assertEquals("Succesfully updated a single category ",true,categoryDAO.update(category));
		
		 
		 *//**********delete*********************//*
		 
		 category=categoryDAO.get(1);
		  assertEquals("Succesfully deleted a single category ",true,categoryDAO.delete(category));
		  
		  *//*****************get list***************//*
		  assertEquals("Succesfully fetched a list of active categories ",1,categoryDAO.list().size());

		

	}*/
}

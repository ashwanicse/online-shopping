package com.niit.shoppingbackend.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.niit.shoppingbackend.dto.Category;
import com.nit.shoppingbackend.dao.CategoryDAO;

@Repository("categoryDAO")
public class CategoryDAOImpl implements CategoryDAO {

	private static List<Category> categories = new ArrayList<>();

	static {
		Category category = new Category();
		category.setId(1);
		category.setName("Computer");
		category.setDescription("Computer is a machine.");
		category.setImageUrl("CAT_1.png");
		categories.add(category);
		
		category = new Category();
		category.setId(2);
		category.setName("Mobile");
		category.setDescription("Mobile is a phome.");
		category.setImageUrl("CAT_2.png");
		categories.add(category);
	}

	@Override
	public List<Category> list() {
		
		return categories;
	}

}

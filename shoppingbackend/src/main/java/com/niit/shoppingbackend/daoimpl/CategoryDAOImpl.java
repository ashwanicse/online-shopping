package com.niit.shoppingbackend.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.shoppingbackend.dto.Category;
import com.nit.shoppingbackend.dao.CategoryDAO;

@Repository("categoryDAO")
@Transactional
public class CategoryDAOImpl implements CategoryDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	private static List<Category> categories ;

	

	@Override
	public List<Category> list() {
		String selectActiveCategory = "from Category where active = :active";
		Query query=getSession().createQuery(selectActiveCategory);
		query.setParameter("active", true);
		return query.getResultList();
	}

	/*
	 * Getting single category based on id
	 * */
	@Override
	public Category get(int id) {
		
		return getSession().get(Category.class, Integer.valueOf(id));		
	}

	@Override	
	public boolean add(Category category) {
		try{
			//add the category to the database
			getSession().persist(category);
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
		    return false;
		}
	}

	/*
	 * Updating a single category based on id
	 * */
	@Override
	public boolean update(Category category) {
		try{
			//add the category to the database
			getSession().update(category);
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
		    return false;
		}
	}

	@Override
	public boolean delete(Category category) {
		try{
			//add the category to the database
			getSession().delete(category);
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
		    return false;
		}
	}

}

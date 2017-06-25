package com.niit.shoppingbackend.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.shoppingbackend.dto.Product;
import com.nit.shoppingbackend.dao.ProductDAO;

@Repository("productDAO")
@Transactional
public class ProductDAOImpl implements ProductDAO {

	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	/*
	 * Getting List of  Product 
	 * */
	@Override
	public List<Product> list() {
		
		return getSession().createQuery("from Product", Product.class).getResultList();
	}

	/*
	 * Getting single Product based on id
	 * */
	@Override
	public Product get(int id) {
		return getSession().get(Product.class, Integer.valueOf(id));	
	}

	@Override
	public boolean add(Product product) {
		try{
			
			getSession().persist(product);
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
		    return false;
		}
	}

	@Override
	public boolean update(Product product) {
		try{
			getSession().update(product);
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
		    return false;
		}
	}

	/*
	 *  Soft delete , set active to false
	 * */
	@Override
	public boolean delete(Product product) {
		try{
			product.setActive(false);
			getSession().update(product);
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
		    return false;
		}
	}

	@Override
	public List<Product> listActiveProducts() {
		String selectActiveProducts = "FROM Product WHERE active = :active";
		return getSession()
				   .createQuery(selectActiveProducts, Product.class)
						.setParameter("active", true)
							.getResultList();
	}

	@Override
	public List<Product> listActiveProductsByCategory(int categoryId) {
		String selectActiveProductsByCategory = "FROM Product WHERE active = :active AND categoryId = :categoryId";
		return getSession()
					.createQuery(selectActiveProductsByCategory, Product.class)
						.setParameter("active", true)
						.setParameter("categoryId",categoryId)
							.getResultList();
	}

	@Override
	public List<Product> getLatestActiveProducts(int count) {
		return sessionFactory
				.getCurrentSession()
					.createQuery("FROM Product WHERE active = :active ORDER BY id", Product.class)
						.setParameter("active", true)
							.setFirstResult(0)
							.setMaxResults(count)
								.getResultList();	
	}

}

package com.niit.shoppingbackend.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.shoppingbackend.dto.Address;
import com.niit.shoppingbackend.dto.Cart;
import com.niit.shoppingbackend.dto.User;
import com.nit.shoppingbackend.dao.UserDAO;

@Repository("userDAO")
@Transactional
public class UserDAOImpl implements UserDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	@Override
	public boolean addUser(User user) {
		try{
			//add the category to the database
			getSession().persist(user);
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
		    return false;
		}
	}

	@Override
	public boolean addAddress(Address address) {
		try{
			//add the category to the database
			getSession().persist(address);
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
		    return false;
		}
	}

	@Override
	public boolean updateCart(Cart cart) {
		try{
			//add the category to the database
			getSession().update(cart);
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
		    return false;
		}
	}

	@Override
	public User getByEmail(String email) {
		String selectQuery = "FROM User WHERE email = :email";
		
		try{
			return getSession()
					.createQuery(selectQuery, User.class)
					     .setParameter("email", email)
					          .getSingleResult();
			
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
			}
		}

	@Override
	public Address getBillingAddress(User user) {
		String selectQuery = "FROM Address user = :user AND billing = :billing";
		try{
			return getSession()
					.createQuery(selectQuery,Address.class)
					.setParameter("user", user)
					.setParameter("billing", true)
					.getSingleResult();
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
			}
		
	}

	@Override
	public List<Address> getShippingAddress(User user) {
		String selectQuery = "FROM Address user = :user AND shipping = :shipping";
		try{
			return getSession()
					.createQuery(selectQuery,Address.class)
					.setParameter("user", user)
					.setParameter("shipping", true)
					.getResultList();
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
			}
	}

	

}

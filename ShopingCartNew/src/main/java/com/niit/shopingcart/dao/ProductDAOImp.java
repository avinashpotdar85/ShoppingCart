package com.niit.shopingcart.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



import com.niit.shopingcart.model.Product;

@Repository("productDAO")
public class ProductDAOImp implements ProductDAO {
	

	@Autowired
	private SessionFactory sessionFactory;


	public ProductDAOImp(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public List<Product> list() {
		@SuppressWarnings("unchecked")
		List<Product> listProduct = (List<Product>) sessionFactory.getCurrentSession()
				.createCriteria(Product.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

		return listProduct;
	}

	@Transactional
	public void saveOrUpdate(Product product) {
	sessionFactory.getCurrentSession().saveOrUpdate(product);
}

	@Transactional
	public void delete(String id) {
		Product ProductToDelete = new Product();
		ProductToDelete.setId(id);
		sessionFactory.getCurrentSession().delete(ProductToDelete);
		return;
	}

	@Transactional
	public Product get(String id) {
		String hql = "from Product where id='" + id+"'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		System.out.println("Inside productDAO get");
		@SuppressWarnings("unchecked")
		List<Product> listProduct = (List<Product>) query.list();
		
		if (listProduct != null && !listProduct.isEmpty()) {
			return listProduct.get(0);
		}
		
		return null;
	}
	
	
	@Transactional
	public Product getByName(String name) {
		String hql = "from Product where name='" + name+"'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		System.out.println("Inside productDAO get");
		@SuppressWarnings("unchecked")
		List<Product> listProduct = (List<Product>) query.list();
		
		if (listProduct != null && !listProduct.isEmpty()) {
			return listProduct.get(0);
		}
		
		return null;
	}


	public boolean delete(Product product) {
		try{
			sessionFactory.getCurrentSession().delete(product);
			
			return true;
		}catch(Exception e){
		// TODO Auto-generated method stub
			e.printStackTrace();
			
			
			
			
		return false;
		}
	}

	public boolean add(Product product) {
		try{
			sessionFactory.getCurrentSession();
			
			return true;
		}catch(Exception e){
		// TODO Auto-generated method stub
			e.printStackTrace();
			
			
			
			
		return false;
		}
	}

	
}

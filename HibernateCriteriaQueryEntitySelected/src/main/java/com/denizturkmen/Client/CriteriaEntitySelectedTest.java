package com.denizturkmen.Client;


import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.query.Query;
import org.hibernate.Session;

import com.denizturkmen.Entity.Employee;
import com.denizturkmen.Util.HibernateUtil;

public class CriteriaEntitySelectedTest {

	public static void main(String[] args) {
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Employee> criteriaQuery = builder.createQuery(Employee.class);
			Root<Employee> root = criteriaQuery.from(Employee.class);
			criteriaQuery.select(root);
			
			//bu olmazsa bütün employee listeler
			//equal eşitliği kontrol et
			criteriaQuery.where(builder.equal(root.get("employeeId"), 2));
			
			Query<Employee> query = session.createQuery(criteriaQuery);
			List<Employee> employeeList = query.getResultList();
			employeeList.forEach(System.out::println);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

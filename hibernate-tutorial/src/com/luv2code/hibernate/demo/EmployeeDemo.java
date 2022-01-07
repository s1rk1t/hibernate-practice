package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Employee;

public class EmployeeDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Employee.class)
				.buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {

			// create a Employee objects to save
			System.out.println("Creating new Employee objects");

			Employee emp1 = new Employee("Paul", "Wall", "Microsoft");
			Employee emp2 = new Employee("James", "Aggeles", "Google");
			Employee emp3 = new Employee("Ethan", "Eagle", "Innovatrium");
			Employee emp4 = new Employee("Ramon", "Parson", "Spotify");

			// start a database transaction
			session.beginTransaction();

			// save the student to database
			System.out.println("Saving the employees");
			session.save(emp1);
			session.save(emp2);
			session.save(emp3);
			session.save(emp4);

			// commit transaction
			session.getTransaction().commit();
			System.out.println("Transaction committed");

			// retrieve Employee from database using primary key (id)
			int empID = 1;

			session = factory.getCurrentSession();
			session.beginTransaction();

			Employee tempEmp = session.get(Employee.class, empID);
			System.out.println("Retrieved employee: " + tempEmp);

			// commit retrieval transaction
			session.getTransaction().commit();

			// retrieve employee based on company
			session = factory.getCurrentSession();
			session.beginTransaction();

			List<Employee> empList = session.createQuery("from Employee where company='Spotify'").getResultList();

			// print results to stdout
			displayEmployees(empList);

			// delete employee given their id (in this case, it's 1)
			session = factory.getCurrentSession();

			Employee empToBeDeleted = session.get(Employee.class, empID);

			session.delete(empToBeDeleted);

			session.getTransaction().commit();

		} finally {
			factory.close();
		}

	}

	private static void displayEmployees(List<Employee> employees) {
		for (Employee e : employees) {
			System.out.println("employee: " + e);
		}
	}

}

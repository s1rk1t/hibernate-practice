package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Address;
import com.luv2code.hibernate.demo.entity.Student;

public class CreateStudentAddressDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.addAnnotatedClass(Address.class).buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {
			// create the object
			Student tempStudent = new Student("Paul", "Peterson", "paul.peterson@gmail.com");

			Address billingAddress = new Address("456 Walnutwood Way", "Jeffersontown", "33103");

			tempStudent.setBillingAddress(billingAddress);
			// start transaction
			session.beginTransaction();

			// save object
			System.out.println("saving Student object (with embedded billing address) to database");
			session.persist(tempStudent);

			// commit transaction
			session.getTransaction().commit();
			System.out.println("transaction committed!");

		} finally {
			// clean up code
			session.close();
			factory.close();
		}

	}

}

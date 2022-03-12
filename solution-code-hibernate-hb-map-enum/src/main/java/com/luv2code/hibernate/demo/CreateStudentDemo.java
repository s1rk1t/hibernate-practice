package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Status;
import com.luv2code.hibernate.demo.entity.Student;

public class CreateStudentDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {
			// create the object
			Student tempStudent1 = new Student("Paul", "Peterson", "paul.peterson@gmail.com", Status.ACTIVE);
			Student tempStudent2 = new Student("Mary", "Public", "mary.public@gmail.com", Status.INACTIVE);

			// start transaction
			session.beginTransaction();

			// save object
			System.out.println("saving Student objects to database");
			session.persist(tempStudent1);
			session.persist(tempStudent2);

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

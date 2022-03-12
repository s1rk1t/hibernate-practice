package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class GetStudentImagesDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {
			// start a transaction
			session.beginTransaction();

			// get student id for query
			int id = 1;
			Student s = session.get(Student.class, id);

			// print student detail
			System.out.println("student info: " + s);

			// print images object
			System.out.println("images: " + s.getImages());

			// commit transaction
			session.getTransaction().commit();
			// done
		} finally {

			session.close();
			factory.close();
			System.out.println(">>>>>>> done");

		}
	}

}

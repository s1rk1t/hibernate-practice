package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class UpdateStudentDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		int studentID = 1;
		try {

			// start a database transaction
			session.beginTransaction();

			// get student to update based on primary key (student.id)
			Student s = session.get(Student.class, studentID);

			s.setFirstName("Scooby");

			// commit the transaction
			session.getTransaction().commit();
			System.out.println("Transaction committed");

			// update all student emails at once using new session
			session = factory.getCurrentSession();
			session.beginTransaction();

			session.createQuery("update Student set email='foo@gmail.com'").executeUpdate();
			session.getTransaction().commit();

		} finally {
			factory.close();
		}

	}

}

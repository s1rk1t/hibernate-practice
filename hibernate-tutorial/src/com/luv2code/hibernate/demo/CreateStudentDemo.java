package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class CreateStudentDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {

			// create a student object to save
			System.out.println("Creating a new Student object");

			Student student = new Student("Paul", "Wall", "pwall@itg.com");

			// start a database transaction
			session.beginTransaction();

			// save the student to database
			System.out.println("Saving the student");
			session.save(student);

			// commit transaction
			session.getTransaction().commit();
			System.out.println("Transaction committed");

		} finally {
			factory.close();
		}

	}

}

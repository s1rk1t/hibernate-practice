package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class PrimaryKeyDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {

			// create three student objects to save
			System.out.println("Creating new Student objects");

			Student student1 = new Student("Paul", "Wall", "pwall@itg.com");
			Student student2 = new Student("John", "Doe", "jdoe@itg.com");
			Student student3 = new Student("Bonita", "Applebum", "bapplebum@itg.com");

			// start a database transaction
			session.beginTransaction();

			// save the student to database
			System.out.println("Saving the students");
			session.save(student1);
			session.save(student2);
			session.save(student3);

			// commit transaction
			session.getTransaction().commit();
			System.out.println("Transaction committed");

		} finally {
			factory.close();
		}

	}

}

package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class GetInstructorDetailDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {

			// start a database transaction
			session.beginTransaction();

			// get the instructor detail object
			int iDetailID = 2;

			InstructorDetail iDetail = session.get(InstructorDetail.class, iDetailID);

			// confirm object via stdout
			System.out.println("Instructor Detail: " + iDetailID);

			System.out.println("Associated Instructor: " + iDetail.getInstructor());

			// commit transaction
			session.getTransaction().commit();
			System.out.println("Transaction committed");

		} catch (Exception e) {

			e.printStackTrace();

		} finally {
			// handle leak issue if null is returned when querying db
			session.close();

			factory.close();
		}

	}

}

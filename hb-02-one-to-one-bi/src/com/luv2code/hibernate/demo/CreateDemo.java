package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class CreateDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {

			// create the objects
//			Instructor i = new Instructor("Chris", "Aggeles", "chrisaggeles@demo.com");
//
//			InstructorDetail iDetail = new InstructorDetail("https://www.youtube.com/demoChannel", "drumming");

			Instructor i = new Instructor("James", "Grace", "jamesgrace@demo.com");

			InstructorDetail iDetail = new InstructorDetail("https://www.youtube.com/jamesDemoChannel",
					"learning to walk");

			// associate the objects
			i.setInstructorDetail(iDetail);

			// start a database transaction
			session.beginTransaction();

			// save the instructor (and via cascading, the instructor detail will be saved)
			session.save(i);

			// commit transaction
			session.getTransaction().commit();
			System.out.println("Transaction committed");

		} finally {
			factory.close();
		}

	}

}

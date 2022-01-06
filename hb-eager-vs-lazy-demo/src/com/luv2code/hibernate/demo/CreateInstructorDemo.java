package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class CreateInstructorDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class).buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {

			// create the objects
			Instructor i = new Instructor("Chris", "Aggeles", "chrisaggeles@demo.com");

			InstructorDetail iDetail = new InstructorDetail("https://www.youtube.com/demoChannel", "drumming");

			Instructor i2 = new Instructor("James", "Grace", "jamesgrace@demo.com");

			InstructorDetail iDetail2 = new InstructorDetail("https://www.youtube.com/jamesDemoChannel",
					"learning to walk");

			Instructor i3 = new Instructor("Ethan", "Eagle", "ethaneagle@demo.com");

			InstructorDetail iDetail3 = new InstructorDetail("https://www.youtube.com/ethanDemoChannel",
					"becoming a Dad!");

			// associate the objects
			i.setInstructorDetail(iDetail);

			i2.setInstructorDetail(iDetail2);

			i3.setInstructorDetail(iDetail3);

			// start a database transaction
			session.beginTransaction();

			// save the instructor (and via cascading, the instructor detail will be saved)
			session.save(i);
			session.save(i2);
			session.save(i3);

			// commit transaction
			session.getTransaction().commit();
			System.out.println("Transaction committed");

		} finally {
			session.close();
			factory.close();
		}

	}

}

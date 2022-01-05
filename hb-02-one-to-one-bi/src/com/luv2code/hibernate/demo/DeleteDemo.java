package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class DeleteDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {

			// start a database transaction
			session.beginTransaction();

			// get Instructor object from primary key
			int demoID = 1;

			Instructor i = session.get(Instructor.class, demoID);

			// confirm Instructor details via stdout
			System.out.println("Found Instructor: " + i);

			// delete Instructor from database
			if (i != null) {
				System.out.println("Deleting: " + i);

				// Note that because of cascading type being 'ALL', associated
				// InstructorDetail will also be deleted
				session.delete(i);
			} else
				System.out.println("Cannot delete null instructor!");

			// commit transaction
			session.getTransaction().commit();
			System.out.println("Transaction committed");

		} finally {
			factory.close();
		}

	}

}

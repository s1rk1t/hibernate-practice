package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class CreateCoursesDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class).buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {

			// start a database transaction
			session.beginTransaction();

			// get instructor from db
			int id = 1;
			Instructor i = session.get(Instructor.class, id);

			// create courses
			Course c1 = new Course("Air Guitar");
			Course c2 = new Course("Underwater Basket Weaving");
			Course c3 = new Course("Discrete Math");

			// add courses to instructor
			i.addCourse(c1);
			i.addCourse(c2);
			i.addCourse(c3);

			// save courses
			session.save(c1);
			session.save(c2);
			session.save(c3);

			// commit transaction
			session.getTransaction().commit();
			System.out.println("Transaction committed");

		} finally {
			session.close();
			factory.close();
		}

	}

}

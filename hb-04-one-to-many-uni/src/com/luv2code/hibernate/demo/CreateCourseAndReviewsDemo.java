package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;

public class CreateCourseAndReviewsDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class).buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {

			// start a database transaction
			session.beginTransaction();

			// create a course
			Course course = new Course("Pacman - How to score a million points");

			// add some reviews

			course.addReview(new Review("Great course! Loved learning how to play Pacman"));
			course.addReview(new Review("Fantastic course and teacher!"));
			course.addReview(new Review("Excellent less on how to play!"));

			// save the course and leverage the 'cascade all' to save associated reviews

			session.save(course);

			// commit transaction
			session.getTransaction().commit();
			System.out.println("Transaction committed");

		} finally {
			session.close();
			factory.close();
		}

	}

}

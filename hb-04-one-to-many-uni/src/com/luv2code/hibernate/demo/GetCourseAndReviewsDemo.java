package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;

public class GetCourseAndReviewsDemo {

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

			// get a course
			int courseID = 10;

			Course course = session.get(Course.class, courseID);

			// print the course
			System.out.println("Deleting the following course.");
			System.out.println("Course: " + course);

			// print course reviews, retrieved using lazy fetch
			System.out.println("Reviews: " + course.getReviews());

			// delete the course and, through cascading delete, the associated reviews
			session.delete(course);

			// commit transaction
			session.getTransaction().commit();
			System.out.println("Transaction committed");

		} finally {
			session.close();
			factory.close();
		}

	}

}

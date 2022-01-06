package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;
import com.luv2code.hibernate.demo.entity.Student;

public class CreateCourseAndStudentsDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class)
				.addAnnotatedClass(Student.class).addAnnotatedClass(Review.class).buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {

			// start a database transaction
			session.beginTransaction();

			// create a course
			Course course = new Course("Pacman - How to score a million points");

			// save the course
			session.save(course);

			// create the Students
			Student s1 = new Student("John", "Smith", "john.smith@demo.com");
			Student s2 = new Student("Marie", "Jones", "marie.jones@demo.com");

			// add the Students to the course
			course.addStudent(s1);
			course.addStudent(s2);

			// save the Students
			session.save(s1);
			session.save(s2);

			// commit transaction
			session.getTransaction().commit();
			System.out.println("Transaction committed");

		} finally {
			session.close();
			factory.close();
		}

	}

}

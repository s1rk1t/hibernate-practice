package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class ReadStudentDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {

			// create a student object to save
			System.out.println("Creating a new Student object");

			Student student = new Student("Daffy", "Duck", "dduck@itg.com");

			// start a database transaction
			session.beginTransaction();

			// save the student to database
			System.out.println("Saving the student");
			System.out.println("student: " + student);
			session.save(student);

			// commit transaction
			session.getTransaction().commit();
			System.out.println("Transaction committed");

			// find out the student's primary key
			System.out.println("generated id: " + student.getId());

			// get a new session and start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();

			// retrieve student from db using primary key
			System.out.println("getting student with id: " + student.getId());
			Student retrievedStudent = session.get(Student.class, student.getId());
			System.out.println("Student retrieved: " + retrievedStudent);

			// commit the transaction
			session.getTransaction().commit();

		} finally {
			factory.close();
		}

	}

}

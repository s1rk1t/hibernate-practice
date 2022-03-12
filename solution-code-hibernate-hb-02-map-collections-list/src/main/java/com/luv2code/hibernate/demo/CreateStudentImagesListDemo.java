package com.luv2code.hibernate.demo;

import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class CreateStudentImagesListDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {
			// create the object
			Student tempStudent = new Student("Paul", "Prophet", "paul@gmail.com");
			List<String> images = tempStudent.getImages();
			images.add("photo1.jpg");
			images.add("photo2.png");
			images.add("photo3.gif");

			// start transaction
			session.beginTransaction();

			// save object
			System.out.println("saving Student object to database");
			session.persist(tempStudent);

			// commit transaction
			session.getTransaction().commit();
			System.out.println("transaction committed!");

		} finally {
			// clean up code
			session.close();
			factory.close();
		}

	}

}

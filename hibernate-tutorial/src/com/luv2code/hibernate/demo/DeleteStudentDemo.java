package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class DeleteStudentDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		int studentID = 1;
		try {

			// start a database transaction
			session.beginTransaction();

			// retrieve Student to be deleted from database
			Student s = session.get(Student.class, studentID);

			// delete the student
			// System.out.println("Deleting student with id=" + studentID);
			// session.delete(s); // commented to avoid null exception b/c student already
			// deleted

			// delete student with id=2 using alternate, query based, approach
			session.createQuery("delete from Student where id=2").executeUpdate();

			// commit the transaction
			session.getTransaction().commit();
			System.out.println("Transaction committed");

		} finally {
			factory.close();
		}

	}

}

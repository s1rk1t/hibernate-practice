package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {

			// start a database transaction
			session.beginTransaction();

			// query students
			List<Student> students = session.createQuery("from Student").getResultList();

			// display the students
			displayStudents(students);

			// query students whose last name = 'Doe' using 'where' clause
			students = session.createQuery("from Student s where s.lastName='Doe'").getResultList();

			// display students that meet the new query criteria
			displayStudents(students);

			// query students whose last name is Doe or first name is Daffy using 'OR'
			// predicate
			students = session.createQuery("from Student s where s.lastName='Doe' OR s.firstName='Daffy'")
					.getResultList();

			// display students meeting the new criteria
			displayStudents(students);

			// query students whose email ends with: 'itg.com'
			students = session.createQuery("from Student s where s.email like '%itg.com'").getResultList();

			// display students meeting the new email criteria
			displayStudents(students);

			// commit transaction
			session.getTransaction().commit();
			System.out.println("Transaction committed");

		} finally {
			factory.close();
		}

	}

	private static void displayStudents(List<Student> students) {
		for (Student s : students) {
			System.out.println("student: " + s);
		}
	}

}

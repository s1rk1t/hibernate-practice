package com.luv2code.hibernate.demo.entity;

import java.util.Comparator;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;

import org.hibernate.annotations.SortComparator;

@Entity
@Table(name = "student")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email")
	private String email;

	@Column(name = "image_name")
	@ElementCollection
	@MapKeyColumn(name = "file_name")
	@CollectionTable(name = "image")
	// @OrderBy // defaults to ordering by map's key column, sorts in ascending
	// fashion unless otherwise specified
	@SortComparator(ReverseStringComparator.class) // used to implement custom sort
	private SortedMap<String, String> images = new TreeMap<String, String>();

	// custom sort logic can be implemented using this format
	public static class ReverseStringComparator implements Comparator<String> {

		@Override
		public int compare(String o1, String o2) {

			return o2.compareTo(o1);
		}

	}

	public Student(String firstName, String lastName, String email) {

		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public Student() {

	}

	public SortedMap<String, String> getImages() {
		return images;
	}

	public void setImages(SortedMap<String, String> images) {
		this.images = images;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + "]";
	}

}
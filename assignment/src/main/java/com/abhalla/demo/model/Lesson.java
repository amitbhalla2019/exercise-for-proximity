package com.abhalla.demo.model;

import java.util.List;

public class Lesson extends BasicEntity {

	List<Course> coursesToBeDealt;
	
	public Lesson() {
	}

	public Lesson(String id, String name, List<Course> coursesToBeDealt) {
		super(id, name);
		this.coursesToBeDealt = coursesToBeDealt;
	}

	public List<Course> getCoursesToBeDealt() {
		return coursesToBeDealt;
	}

}
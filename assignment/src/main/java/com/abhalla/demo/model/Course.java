package com.abhalla.demo.model;

import java.util.List;

public class Course extends BasicEntity {

	List<Subject> subjectsToBeTaught;
	
	public Course() {
	}

	public Course(String id, String name, List<Subject> subjects) {
		super(id, name);
		subjectsToBeTaught = subjects;
	}

	public List<Subject> getSubjectsToBeTaught() {
		return subjectsToBeTaught;
	}

}
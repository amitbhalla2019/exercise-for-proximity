package com.abhalla.demo.model;

import java.util.List;

public class Video extends BasicEntity {

	List<Lesson> lessonsUndertaken;
	
	public Video() {
	}

	public Video(String id, String name, List<Lesson> lessonsUndertaken) {
		super(id, name);
		this.lessonsUndertaken = lessonsUndertaken;
	}

	public List<Lesson> getLessonsUndertaken() {
		return lessonsUndertaken;
	}

}
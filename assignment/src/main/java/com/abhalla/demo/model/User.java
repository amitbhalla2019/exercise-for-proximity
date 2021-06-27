package com.abhalla.demo.model;

public interface User {
	
	public static boolean isInstructor  = false;

	default boolean isInstructor() {
		return isInstructor;
	}

}
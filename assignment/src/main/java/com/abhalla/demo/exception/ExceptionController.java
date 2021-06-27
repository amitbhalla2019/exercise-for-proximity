package com.abhalla.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

	@ExceptionHandler(value = SubjectNotFoundException.class)
	public ResponseEntity<Object> exception(SubjectNotFoundException exception) {
		return new ResponseEntity<>("Subject not found", HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = CourseNotFoundException.class)
	public ResponseEntity<Object> exception(CourseNotFoundException exception) {
		return new ResponseEntity<>("Course not found", HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = LessonNotFoundException.class)
	public ResponseEntity<Object> exception(LessonNotFoundException exception) {
		return new ResponseEntity<>("Lesson not found", HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = VideoNotFoundException.class)
	public ResponseEntity<Object> exception(VideoNotFoundException exception) {
		return new ResponseEntity<>("Video not found", HttpStatus.NOT_FOUND);
	}
}
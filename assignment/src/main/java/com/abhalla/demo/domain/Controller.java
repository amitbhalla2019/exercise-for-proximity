package com.abhalla.demo.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abhalla.demo.exception.CourseNotFoundException;
import com.abhalla.demo.exception.LessonNotFoundException;
import com.abhalla.demo.exception.SubjectNotFoundException;
import com.abhalla.demo.exception.VideoNotFoundException;
import com.abhalla.demo.model.Course;
import com.abhalla.demo.model.Lesson;
import com.abhalla.demo.model.Subject;
import com.abhalla.demo.model.Video;

@RestController
public class Controller {

	private static Map<String, Subject> subjectsDetailsMap = new HashMap<>();
	static {
		Subject maths = new Subject("Subj-1", "Mathematics");
		subjectsDetailsMap.put(maths.getId(), maths);

		Subject generalScience = new Subject("Subj-2", "General Science");
		subjectsDetailsMap.put(generalScience.getId(), generalScience);
		
		Subject geo = new Subject("Subj-3", "Geopgraphy");
		subjectsDetailsMap.put(geo.getId(), geo);
		
		Subject history = new Subject("Subj-4", "History");
		subjectsDetailsMap.put(history.getId(), history);
		
		Subject philan = new Subject("Subj-5", "Philanthropy");
		subjectsDetailsMap.put(philan.getId(), philan);
	}
	private static Map<String, Course> coursesDetailsMap = new HashMap<>();
	static {
		Course basicCourseOnStartUps = 
				new Course("Course-1", "Basic-Level-Course", new ArrayList<Subject>(subjectsDetailsMap.values()));
		coursesDetailsMap.put(basicCourseOnStartUps.getId(), basicCourseOnStartUps);
		
		Course beginnersCourseOnLanguage = 
				new Course("Course-2", "Beginners-Level-Course", new ArrayList<Subject>(subjectsDetailsMap.values()));
		coursesDetailsMap.put(beginnersCourseOnLanguage.getId(), beginnersCourseOnLanguage);
	}
	private static Map<String, Lesson> lessonsDetailsMap = new HashMap<>();
	static {
		Lesson basicLesson = 
				new Lesson("Lesson-1", "Basic-Lesson", new ArrayList<Course>(coursesDetailsMap.values()));
		lessonsDetailsMap.put(basicLesson.getId(), basicLesson);
		
		Lesson advLesson = 
				new Lesson("Lesson-2", "Advanced-Lesson", new ArrayList<Course>(coursesDetailsMap.values()));
		lessonsDetailsMap.put(advLesson.getId(), advLesson);
	}
	private static Map<String, Video> videosDetailsMap = new HashMap<>();
	static {
		Video basicVideo = 
				new Video("Video-1", "Basic-Video-Series", new ArrayList<Lesson>(lessonsDetailsMap.values()));
		videosDetailsMap.put(basicVideo.getId(), basicVideo);
		
		Video advVideo = 
				new Video("Video-2", "Advanced-Video-Series", new ArrayList<Lesson>(lessonsDetailsMap.values()));
		videosDetailsMap.put(advVideo.getId(), advVideo);
	}

	@RequestMapping(value = "/subjects/")
	public ResponseEntity<Object> getSubjects() {
		return new ResponseEntity<>(subjectsDetailsMap.values(), HttpStatus.OK);
	}

	@RequestMapping(value = "/subjects", method = RequestMethod.POST)
	public ResponseEntity<Object> createSubject(@RequestBody Subject subject) {
		subjectsDetailsMap.put(subject.getId(), subject);
		return new ResponseEntity<>("Subject is created successfully", HttpStatus.CREATED);
	}

	@RequestMapping(value = "/subjects/{id}")
	public ResponseEntity<Object> getSubject(@PathVariable("id") String id) {
		if (!subjectsDetailsMap.containsKey(id))
			throw new SubjectNotFoundException();
		return new ResponseEntity<>(subjectsDetailsMap.get(id), HttpStatus.OK);
	}

	@RequestMapping(value = "/subjects")
	public ResponseEntity<Object> getSubjectThruReqParamId(@RequestParam List<String> id) {
		Map<String, Subject> subjectsDetailsMapAskedFor = new HashMap<>();
		id.forEach(idV->{if (subjectsDetailsMap.containsKey(idV)) subjectsDetailsMapAskedFor.put(idV, subjectsDetailsMap.get(idV));});
		if (subjectsDetailsMapAskedFor.isEmpty())
			throw new SubjectNotFoundException();
		return new ResponseEntity<>(subjectsDetailsMapAskedFor.values(), HttpStatus.OK);
	}

	@RequestMapping(value = "/subjects/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateSubject(@PathVariable("id") String id, @RequestBody Subject subject) {
		if (!subjectsDetailsMap.containsKey(id)) throw new SubjectNotFoundException();
		subjectsDetailsMap.remove(id);
		subject.setId(id);
		subjectsDetailsMap.put(id, subject);
		return new ResponseEntity<>("Subject is updated successsfully", HttpStatus.OK);
	}

	@RequestMapping(value = "/subjects/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteParticularSubject(@PathVariable("id") String id) {
		if (!subjectsDetailsMap.containsKey(id))
			throw new SubjectNotFoundException();
		subjectsDetailsMap.remove(id);
		return new ResponseEntity<>("Subject is deleted successsfully", HttpStatus.OK);
	}

	@RequestMapping(value = "/courses/")
	public ResponseEntity<Object> getCourses() {
		return new ResponseEntity<>(coursesDetailsMap.values(), HttpStatus.OK);
	}

	@RequestMapping(value = "/courses", method = RequestMethod.POST)
	public ResponseEntity<Object> createCourse(@RequestBody Course course) {
		coursesDetailsMap.put(course.getId(), course);
		return new ResponseEntity<>("Course is created successfully", HttpStatus.CREATED);
	}

	@RequestMapping(value = "/courses/{id}")
	public ResponseEntity<Object> getCourse(@PathVariable("id") String id) {
		if (!coursesDetailsMap.containsKey(id))
			throw new CourseNotFoundException();
		return new ResponseEntity<>(coursesDetailsMap.get(id), HttpStatus.OK);
	}

	@RequestMapping(value = "/courses")
	public ResponseEntity<Object> getCourseThruReqParamId(@RequestParam List<String> id) {
		Map<String, Course> coursesDetailsMapAskedFor = new HashMap<>();
		id.forEach(idV->{if (coursesDetailsMap.containsKey(idV)) coursesDetailsMapAskedFor.put(idV, coursesDetailsMap.get(idV));});
		if (coursesDetailsMapAskedFor.isEmpty())
			throw new CourseNotFoundException();
		return new ResponseEntity<>(coursesDetailsMapAskedFor.values(), HttpStatus.OK);
	}

	@RequestMapping(value = "/courses/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateCourse(@PathVariable("id") String id, @RequestBody Course course) {
		if (!coursesDetailsMap.containsKey(id)) throw new CourseNotFoundException();
		coursesDetailsMap.remove(id);
		course.setId(id);
		coursesDetailsMap.put(id, course);
		return new ResponseEntity<>("Course is updated successsfully", HttpStatus.OK);
	}

	@RequestMapping(value = "/courses/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteParticularCourse(@PathVariable("id") String id) {
		if (!coursesDetailsMap.containsKey(id))
			throw new CourseNotFoundException();
		coursesDetailsMap.remove(id);
		return new ResponseEntity<>("Course is deleted successsfully", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/lessons/")
	public ResponseEntity<Object> getLessons() {
		return new ResponseEntity<>(lessonsDetailsMap.values(), HttpStatus.OK);
	}

	@RequestMapping(value = "/lessons", method = RequestMethod.POST)
	public ResponseEntity<Object> createLesson(@RequestBody Lesson lesson) {
		lessonsDetailsMap.put(lesson.getId(), lesson);
		return new ResponseEntity<>("Lesson is created successfully", HttpStatus.CREATED);
	}

	@RequestMapping(value = "/lessons/{id}")
	public ResponseEntity<Object> getLesson(@PathVariable("id") String id) {
		if (!lessonsDetailsMap.containsKey(id))
			throw new LessonNotFoundException();
		return new ResponseEntity<>(lessonsDetailsMap.get(id), HttpStatus.OK);
	}

	@RequestMapping(value = "/lessons")
	public ResponseEntity<Object> getLessonThruReqParamId(@RequestParam List<String> id) {
		Map<String, Lesson> lessonsDetailsMapAskedFor = new HashMap<>();
		id.forEach(idV->{if (lessonsDetailsMap.containsKey(idV)) lessonsDetailsMapAskedFor.put(idV, lessonsDetailsMap.get(idV));});
		if (lessonsDetailsMapAskedFor.isEmpty())
			throw new LessonNotFoundException();
		return new ResponseEntity<>(lessonsDetailsMapAskedFor.values(), HttpStatus.OK);
	}

	@RequestMapping(value = "/lessons/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateLesson(@PathVariable("id") String id, @RequestBody Lesson lesson) {
		if (!lessonsDetailsMap.containsKey(id)) throw new LessonNotFoundException();
		lessonsDetailsMap.remove(id);
		lesson.setId(id);
		lessonsDetailsMap.put(id, lesson);
		return new ResponseEntity<>("Lesson is updated successsfully", HttpStatus.OK);
	}

	@RequestMapping(value = "/lessons/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteParticularLesson(@PathVariable("id") String id) {
		if (!lessonsDetailsMap.containsKey(id))
			throw new LessonNotFoundException();
		lessonsDetailsMap.remove(id);
		return new ResponseEntity<>("Lesson is deleted successsfully", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/videos/")
	public ResponseEntity<Object> getVideos() {
		return new ResponseEntity<>(videosDetailsMap.values(), HttpStatus.OK);
	}

	@RequestMapping(value = "/videos", method = RequestMethod.POST)
	public ResponseEntity<Object> createVideo(@RequestBody Video video) {
		videosDetailsMap.put(video.getId(), video);
		return new ResponseEntity<>("Video is created successfully", HttpStatus.CREATED);
	}

	@RequestMapping(value = "/videos/{id}")
	public ResponseEntity<Object> getVideo(@PathVariable("id") String id) {
		if (!videosDetailsMap.containsKey(id))
			throw new VideoNotFoundException();
		return new ResponseEntity<>(videosDetailsMap.get(id), HttpStatus.OK);
	}

	@RequestMapping(value = "/videos")
	public ResponseEntity<Object> getVideoThruReqParamId(@RequestParam List<String> id) {
		Map<String, Video> videosDetailsMapAskedFor = new HashMap<>();
		id.forEach(idV->{if (videosDetailsMap.containsKey(idV)) videosDetailsMapAskedFor.put(idV, videosDetailsMap.get(idV));});
		if (videosDetailsMapAskedFor.isEmpty())
			throw new VideoNotFoundException();
		return new ResponseEntity<>(videosDetailsMapAskedFor.values(), HttpStatus.OK);
	}

	@RequestMapping(value = "/videos/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateVideo(@PathVariable("id") String id, @RequestBody Video video) {
		if (!videosDetailsMap.containsKey(id)) throw new VideoNotFoundException();
		videosDetailsMap.remove(id);
		video.setId(id);
		videosDetailsMap.put(id, video);
		return new ResponseEntity<>("Video is updated successsfully", HttpStatus.OK);
	}

	@RequestMapping(value = "/videos/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteParticularVideo(@PathVariable("id") String id) {
		if (!videosDetailsMap.containsKey(id))
			throw new VideoNotFoundException();
		videosDetailsMap.remove(id);
		return new ResponseEntity<>("Video is deleted successsfully", HttpStatus.OK);
	}

}
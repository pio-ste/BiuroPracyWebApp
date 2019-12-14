package com.biuropracy.demo.service;

import com.biuropracy.demo.model.Course;
import com.biuropracy.demo.model.User;
import com.biuropracy.demo.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    CourseRepository courseRepository;

    public List<Course> findCourseByUserId(Integer user) {
        return courseRepository.findByUserId(user);
    }

    public Course getCourseById(Integer id) {
        Optional<Course> courseOpt = courseRepository.findById(id);
        if (courseOpt.isPresent()) {
            return courseOpt.get();
        } else {
            throw new RuntimeException("ID kursu nie znalezione.");
        }
    }

    public Course updateCourse(Course course) {
        Optional<Course> courseOpt = courseRepository.findById(course.getIdCourse());
        if (courseOpt.isPresent()) {
            Course newCourse = courseOpt.get();
            newCourse.setCourseName(course.getCourseName());
            newCourse.setOrganizer(course.getOrganizer());
            newCourse.setCourseMonth(course.getCourseMonth());
            newCourse.setCourseYear(course.getCourseYear());

            newCourse = courseRepository.save(newCourse);

            return newCourse;
        } else {
            course = courseRepository.save(course);
            return course;
        }
    }

    public Course createCourse(Course course, User user) {
        course.setUser(user);
        course = courseRepository.save(course);
        return course;
    }

    public void deleteCourseById(Integer id) {
        Optional<Course> courseOpt = courseRepository.findById(id);
        if (courseOpt.isPresent()) {
            courseRepository.deleteById(id);
        } else {
            throw new RuntimeException("ID kursu nie znalezione.");
        }
    }

}

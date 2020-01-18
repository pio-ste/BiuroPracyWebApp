package com.biuropracy.demo.repository;

import com.biuropracy.demo.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {


    List<Course> findCourseByUser_IdUser(Integer user);

    Course findByUserIdUser(Integer id);
}

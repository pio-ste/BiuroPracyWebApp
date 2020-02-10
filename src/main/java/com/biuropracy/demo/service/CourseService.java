package com.biuropracy.demo.service;

import com.biuropracy.demo.model.Course;
import com.biuropracy.demo.model.User;
import com.biuropracy.demo.repository.CourseRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    CourseRepository courseRepository;
    @Autowired
    private EntityManagerService entityManagerService;

    public List<Course> findCourseByUserId(Integer user) {
        return courseRepository.findCourseByUser_IdUser(user);
    }

    public Course findByUserId(Integer id){
        return courseRepository.findByUserIdUser(id);
    }

    /**
     * aktualizowanie kursu
     * @param course
     * @return
     */
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

    /**
     * zapisywanie kursu do bazy
     * @param course
     * @param user
     * @return
     */
    public Course createCourse(Course course, User user) {
        course.setUser(user);
        course = courseRepository.save(course);
        return course;
    }

    /**
     * usuwanie kursu za bazy
     * @param id
     * @throws RuntimeException
     */
    public void deleteCourseById(Integer id) throws RuntimeException{
        Optional<Course> courseOpt = courseRepository.findById(id);
        if (courseOpt.isPresent()) {
            SessionFactory sessionFactory = entityManagerService.getSessionFactory();
            Session session = sessionFactory.openSession();
            String query = "Delete from course where id_course ="+id;
            session.doWork(connection -> connection.prepareStatement(query).execute());
            session.close();
        } else {
            throw new RuntimeException("ID kursu nie znalezione.");
        }
    }

}

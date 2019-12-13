package com.biuropracy.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_course")
    private Integer idCourse;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "organizer")
    private String organizer;

    @Column(name = "course_month")
    private String courseMonth;

    @Column(name = "course_year")
    private String courseYear;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    public Course(String courseName, String organizer, String courseMonth, String courseYear, User user) {
        this.courseName = courseName;
        this.organizer = organizer;
        this.courseMonth = courseMonth;
        this.courseYear = courseYear;
        this.user = user;
    }

    public Course() {
    }

    public Integer getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(Integer idCourse) {
        this.idCourse = idCourse;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public String getCourseMonth() {
        return courseMonth;
    }

    public void setCourseMonth(String courseMonth) {
        this.courseMonth = courseMonth;
    }

    public String getCourseYear() {
        return courseYear;
    }

    public void setCourseYear(String courseYear) {
        this.courseYear = courseYear;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

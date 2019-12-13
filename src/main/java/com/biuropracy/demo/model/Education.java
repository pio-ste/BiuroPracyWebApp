package com.biuropracy.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "education")
public class Education {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_education")
    private Integer idEducation;

    @Column(name = "school_name")
    private String schoolName;

    @Column(name = "education_level")
    private String educationLevel;

    @Column(name = "specialization")
    private String specialization;

    @Column(name = "from_month")
    private String fromMonth;

    @Column(name = "from_year")
    private String fromYear;

    @Column(name = "to_month")
    private String toMonth;

    @Column(name = "to_year")
    private String toYear;

    @Column(name = "continues")
    private String continues;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    public Education(String schoolName, String educationLevel, String specialization, String fromMonth, String fromYear, String toMonth, String toYear, String continues, User user) {
        this.schoolName = schoolName;
        this.educationLevel = educationLevel;
        this.specialization = specialization;
        this.fromMonth = fromMonth;
        this.fromYear = fromYear;
        this.toMonth = toMonth;
        this.toYear = toYear;
        this.continues = continues;
        this.user = user;
    }

    public Education() {
    }

    public Integer getIdEducation() {
        return idEducation;
    }

    public void setIdEducation(Integer idEducation) {
        this.idEducation = idEducation;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(String educationLevel) {
        this.educationLevel = educationLevel;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getFromMonth() {
        return fromMonth;
    }

    public void setFromMonth(String fromMonth) {
        this.fromMonth = fromMonth;
    }

    public String getFromYear() {
        return fromYear;
    }

    public void setFromYear(String fromYear) {
        this.fromYear = fromYear;
    }

    public String getToMonth() {
        return toMonth;
    }

    public void setToMonth(String toMonth) {
        this.toMonth = toMonth;
    }

    public String getToYear() {
        return toYear;
    }

    public void setToYear(String toYear) {
        this.toYear = toYear;
    }

    public String getContinues() {
        return continues;
    }

    public void setContinues(String continues) {
        this.continues = continues;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

package com.biuropracy.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "job_experience")
public class JobExperience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_job_experience")
    private Integer idJobExperience;

    @Column(name = "position")
    private String position;

    @Column(name = "location")
    private String location;

    @Column(name = "company_name")
    private String companyName;

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

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    public JobExperience(String position, String location, String companyName, String fromMonth, String fromYear, String toMonth, String toYear, String continues, String description, User user) {
        this.position = position;
        this.location = location;
        this.companyName = companyName;
        this.fromMonth = fromMonth;
        this.fromYear = fromYear;
        this.toMonth = toMonth;
        this.toYear = toYear;
        this.continues = continues;
        this.description = description;
        this.user = user;
    }

    public JobExperience() {
    }

    public Integer getIdJobExperience() {
        return idJobExperience;
    }

    public void setIdJobExperience(Integer idJobExperience) {
        this.idJobExperience = idJobExperience;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

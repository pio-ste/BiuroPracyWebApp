package com.biuropracy.demo.model;

import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Integer idUser;

    @Column(name = "name",nullable = false)
    @NotEmpty(message = "Wpisz swoje imię")
    private String name;

    @Column(name = "last_name",nullable = false)
    @NotEmpty(message = "Wpisz swoje nazwisko")
    private String lastName;

    @Column(name = "email",nullable = false, unique = true)
    @NotEmpty(message = "Wpisz swój adres email")
    @Email(message = "Zły adres email")
    private String email;

    @Column(name = "password",nullable = false)
    @NotEmpty(message = "Wpisz hasło")
    @Size(min=4, message = "Hasło musi mieć minimum 4 znaki")
    private String password;

    @Column(name = "date_birth")
    private String dateBirth;

    @Column(name = "home_city")
    private String homeCity;

    @Column(name = "work_city")
    private String workCity;

    @Column(name = "hobby")
    private String hobby;

    @Column(name = "current_position")
    private String currentPosition;

    @Column(name = "to_find")
    private String toFind;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_role",
            joinColumns={@JoinColumn(name="id_user")},
            inverseJoinColumns={@JoinColumn(name="id_role")})
    private Set<Role> roles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<JobOffer> jobOffers;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Course> courses;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Education> educations;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<JobExperience> jobExperiences;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Language> languages;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Organization> organizations;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Skill> skills;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<WebLink> webLinks;

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public List<JobOffer> getJobOffers() {
        return jobOffers;
    }

    public void setJobOffers(List<JobOffer> jobOffers) {
        this.jobOffers = jobOffers;
    }

    public String getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(String dateBirth) {
        this.dateBirth = dateBirth;
    }

    public String getHomeCity() {
        return homeCity;
    }

    public void setHomeCity(String homeCity) {
        this.homeCity = homeCity;
    }

    public String getWorkCity() {
        return workCity;
    }

    public void setWorkCity(String workCity) {
        this.workCity = workCity;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(String currentPosition) {
        this.currentPosition = currentPosition;
    }

    public String getToFind() {
        return toFind;
    }

    public void setToFind(String toFind) {
        this.toFind = toFind;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<Education> getEducations() {
        return educations;
    }

    public void setEducations(List<Education> educations) {
        this.educations = educations;
    }

    public List<JobExperience> getJobExperiences() {
        return jobExperiences;
    }

    public void setJobExperiences(List<JobExperience> jobExperiences) {
        this.jobExperiences = jobExperiences;
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }

    public List<Organization> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(List<Organization> organizations) {
        this.organizations = organizations;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public List<WebLink> getWebLinks() {
        return webLinks;
    }

    public void setWebLinks(List<WebLink> webLinks) {
        this.webLinks = webLinks;
    }
}

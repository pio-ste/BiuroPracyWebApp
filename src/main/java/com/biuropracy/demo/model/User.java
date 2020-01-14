package com.biuropracy.demo.model;

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

    @Column(name = "user_phone",nullable = false)
    @NotEmpty(message = "Wpisz swój numer telefonu")
    private String userPhone;

    @Column(name = "password",nullable = false)
    @NotEmpty(message = "Wpisz hasło")
    @Size(min=4, message = "Hasło musi mieć minimum 4 znaki")
    private String password;

    @Column(name = "profile_image")
    @Lob
    private Byte[] profileImage;

    @Column(name = "status")
    private String status;

    public User(@NotEmpty(message = "Wpisz swoje imię") String name, @NotEmpty(message = "Wpisz swoje nazwisko") String lastName, @NotEmpty(message = "Wpisz swój adres email") @Email(message = "Zły adres email") String email, @NotEmpty(message = "Wpisz swój numer telefonu") String userPhone, @NotEmpty(message = "Wpisz hasło") @Size(min = 4, message = "Hasło musi mieć minimum 4 znaki") String password, Byte[] profileImage, String status, Set<Role> roles, List<Course> courses, List<Education> educations, List<JobExperience> jobExperiences, List<Language> languages, List<Organization> organizations, List<Skill> skills, List<WebLink> webLinks, List<JobProposition> jobPropositions, List<ProfileProposition> users, UserInformation userInformation, Employer employer) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.userPhone = userPhone;
        this.password = password;
        this.profileImage = profileImage;
        this.status = status;
        this.roles = roles;
        this.courses = courses;
        this.educations = educations;
        this.jobExperiences = jobExperiences;
        this.languages = languages;
        this.organizations = organizations;
        this.skills = skills;
        this.webLinks = webLinks;
        this.jobPropositions = jobPropositions;
        this.users = users;
        this.userInformation = userInformation;
        this.employer = employer;
    }

    public User(Integer idUser, String email, String name, String lastName) {
        this.idUser = idUser;
        this.email = email;
        this.name = name;
        this.lastName = lastName;
    }

    public User() {
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_role",
            joinColumns={@JoinColumn(name="id_user")},
            inverseJoinColumns={@JoinColumn(name="id_role")})
    private Set<Role> roles;

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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<JobProposition> jobPropositions;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProfileProposition> users;

    @OneToOne(mappedBy = "user")
    private UserInformation userInformation;

    @OneToOne(mappedBy = "user")
    private Employer employer;

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

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
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

    public Byte[] getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(Byte[] profileImage) {
        this.profileImage = profileImage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public List<JobProposition> getJobPropositions() {
        return jobPropositions;
    }

    public void setJobPropositions(List<JobProposition> jobPropositions) {
        this.jobPropositions = jobPropositions;
    }

    public List<ProfileProposition> getUsers() {
        return users;
    }

    public void setUsers(List<ProfileProposition> users) {
        this.users = users;
    }

    public UserInformation getUserInformation() {
        return userInformation;
    }

    public void setUserInformation(UserInformation userInformation) {
        this.userInformation = userInformation;
    }

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }
}

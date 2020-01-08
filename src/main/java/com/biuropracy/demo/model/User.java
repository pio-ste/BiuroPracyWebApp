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

    @Column(name = "profile_image")
    @Lob
    private Byte[] profileImage;

    @Column(name = "status")
    private String status;

    public User(@NotEmpty(message = "Wpisz swoje imię") String name, @NotEmpty(message = "Wpisz swoje nazwisko") String lastName, @NotEmpty(message = "Wpisz swój adres email") @Email(message = "Zły adres email") String email, @NotEmpty(message = "Wpisz hasło") @Size(min = 4, message = "Hasło musi mieć minimum 4 znaki") String password, Byte[] profileImage, String status, Set<Role> roles, List<JobOffer> jobOffers, List<Course> courses, List<Education> educations, List<JobExperience> jobExperiences, List<Language> languages, List<Organization> organizations, List<Skill> skills, List<WebLink> webLinks, List<JobProposition> fromUsers, List<JobProposition> toUsers, List<ProfileProposition> users, UserDetails userDetails, Employer employer) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.profileImage = profileImage;
        this.status = status;
        this.roles = roles;
        this.jobOffers = jobOffers;
        this.courses = courses;
        this.educations = educations;
        this.jobExperiences = jobExperiences;
        this.languages = languages;
        this.organizations = organizations;
        this.skills = skills;
        this.webLinks = webLinks;
        this.fromUsers = fromUsers;
        this.toUsers = toUsers;
        this.users = users;
        this.userDetails = userDetails;
        this.employer = employer;
    }

    public User(int idUser, String email, String name, String lastName, Byte[] profileImage) {
        this.idUser = idUser;
        this.email = email;
        this.name = name;
        this.lastName = lastName;
        this.profileImage = profileImage;
    }

    public User() {
    }

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

    @OneToMany(mappedBy = "fromUser", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<JobProposition> fromUsers;

    @OneToMany(mappedBy = "toUser", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<JobProposition> toUsers;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProfileProposition> users;

    @OneToOne(mappedBy = "user")
    private UserDetails userDetails;

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

    public List<JobProposition> getFromUsers() {
        return fromUsers;
    }

    public void setFromUsers(List<JobProposition> fromUsers) {
        this.fromUsers = fromUsers;
    }

    public List<JobProposition> getToUsers() {
        return toUsers;
    }

    public void setToUsers(List<JobProposition> toUsers) {
        this.toUsers = toUsers;
    }

    public List<ProfileProposition> getUsers() {
        return users;
    }

    public void setUsers(List<ProfileProposition> users) {
        this.users = users;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }
}

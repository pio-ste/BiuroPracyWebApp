package com.biuropracy.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "employer")
public class Employer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_employer")
    private Integer idEmployer;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "web_link")
    private String webLink;

    @Column(name = "address")
    private String address;

    @Column(name = "description")
    private String description;

    @Column(name = "company_image")
    @Lob
    private Byte[] companyImage;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(unique = true)
    private User user;

    public Employer(String companyName, String phoneNumber, String email, String webLink, String address, String description, Byte[] companyImage, User user) {
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.webLink = webLink;
        this.address = address;
        this.description = description;
        this.companyImage = companyImage;
        this.user = user;
    }

    public Employer() {
    }

    public Integer getIdEmployer() {
        return idEmployer;
    }

    public void setIdEmployer(Integer idEmployer) {
        this.idEmployer = idEmployer;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebLink() {
        return webLink;
    }

    public void setWebLink(String webLink) {
        this.webLink = webLink;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Byte[] getCompanyImage() {
        return companyImage;
    }

    public void setCompanyImage(Byte[] companyImage) {
        this.companyImage = companyImage;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

package com.biuropracy.demo.model;

import javax.persistence.*;
import java.util.List;

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

    @Column(name = "company_email")
    private String companyEmail;

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

    @OneToMany(mappedBy = "employer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<JobOffer> jobOffers;

    @OneToMany(mappedBy = "employer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<JobProposition> jobPropositions;

    public Employer(String companyName, String phoneNumber, String companyEmail, String webLink, String address, String description, Byte[] companyImage, User user, List<JobOffer> jobOffers, List<JobProposition> jobPropositions) {
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.companyEmail = companyEmail;
        this.webLink = webLink;
        this.address = address;
        this.description = description;
        this.companyImage = companyImage;
        this.user = user;
        this.jobOffers = jobOffers;
        this.jobPropositions = jobPropositions;
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

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
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

    public List<JobOffer> getJobOffers() {
        return jobOffers;
    }

    public void setJobOffers(List<JobOffer> jobOffers) {
        this.jobOffers = jobOffers;
    }

    public List<JobProposition> getJobPropositions() {
        return jobPropositions;
    }

    public void setJobPropositions(List<JobProposition> jobPropositions) {
        this.jobPropositions = jobPropositions;
    }
}

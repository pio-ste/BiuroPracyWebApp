package com.biuropracy.demo.DTO;

import com.biuropracy.demo.model.JobOffer;
import com.biuropracy.demo.model.User;

import java.util.Arrays;

public class ProfilePropositionDTO {

    private Integer idProfileProposition;
    private Integer idUser;
    private Integer idJobOffer;
    private String title;
    private String location;
    private String companyName;
    private User user;
    private JobOffer jobOffer;
    private String name;
    private String lastName;
    private Byte[] profileImage;
    private String contactType;
    private String decision;


    public ProfilePropositionDTO(Integer idProfileProposition, Integer idUser, Integer idJobOffer, String title, String location, String companyName, User user, JobOffer jobOffer, String name, String lastName, Byte[] profileImage, String contactType, String decision) {
        this.idProfileProposition = idProfileProposition;
        this.idUser = idUser;
        this.idJobOffer = idJobOffer;
        this.title = title;
        this.location = location;
        this.companyName = companyName;
        this.user = user;
        this.jobOffer = jobOffer;
        this.name = name;
        this.lastName = lastName;
        this.profileImage = profileImage;
        this.contactType = contactType;
        this.decision = decision;
    }

    public ProfilePropositionDTO() {
    }

    public Integer getIdProfileProposition() {
        return idProfileProposition;
    }

    public void setIdProfileProposition(Integer idProfileProposition) {
        this.idProfileProposition = idProfileProposition;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getIdJobOffer() {
        return idJobOffer;
    }

    public void setIdJobOffer(Integer idJobOffer) {
        this.idJobOffer = idJobOffer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public JobOffer getJobOffer() {
        return jobOffer;
    }

    public void setJobOffer(JobOffer jobOffer) {
        this.jobOffer = jobOffer;
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

    public Byte[] getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(Byte[] profileImage) {
        this.profileImage = profileImage;
    }

    public String getContactType() {
        return contactType;
    }

    public void setContactType(String contactType) {
        this.contactType = contactType;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }
}

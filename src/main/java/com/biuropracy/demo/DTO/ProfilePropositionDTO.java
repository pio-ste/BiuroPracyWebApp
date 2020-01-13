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
    private String name;
    private String lastName;
    private Byte[] profileImage;
    private String contactType;
    private String substantiation;
    private String decision;

    public ProfilePropositionDTO(Integer idProfileProposition, Integer idUser, Integer idJobOffer, String title, String location, String name, String lastName, String contactType, String substantiation, String decision) {
        this.idProfileProposition = idProfileProposition;
        this.idUser = idUser;
        this.idJobOffer = idJobOffer;
        this.title = title;
        this.location = location;
        this.name = name;
        this.lastName = lastName;
        this.contactType = contactType;
        this.substantiation = substantiation;
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

    public String getSubstantiation() {
        return substantiation;
    }

    public void setSubstantiation(String substantiation) {
        this.substantiation = substantiation;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }
}

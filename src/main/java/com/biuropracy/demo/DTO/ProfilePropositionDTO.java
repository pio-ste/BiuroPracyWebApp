package com.biuropracy.demo.DTO;

import java.util.Arrays;

public class ProfilePropositionDTO {

    private Integer idProfileProposition;
    private Integer idUser;
    private Integer idJobOffer;
    private String name;
    private String lastName;
    private Byte[] profileImage;
    private String contactType;
    private String decision;

    public ProfilePropositionDTO(Integer idProfileProposition, Integer idUser, Integer idJobOffer, String name, String lastName, Byte[] profileImage, String contactType, String decision) {
        this.idProfileProposition = idProfileProposition;
        this.idUser = idUser;
        this.idJobOffer = idJobOffer;
        this.name = name;
        this.lastName = lastName;
        this.profileImage = profileImage;
        this.contactType = contactType;
        this.decision = decision;
    }

    public ProfilePropositionDTO() {
    }

    @Override
    public String toString() {
        return "ProfilePropositionDTO{" +
                "idProfileProposition=" + idProfileProposition +
                ", idUser=" + idUser +
                ", idJobOffer=" + idJobOffer +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", profileImage=" + Arrays.toString(profileImage) +
                ", contactType='" + contactType + '\'' +
                ", decision='" + decision + '\'' +
                '}';
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

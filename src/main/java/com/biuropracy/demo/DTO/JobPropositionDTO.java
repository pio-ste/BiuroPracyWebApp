package com.biuropracy.demo.DTO;

import com.biuropracy.demo.model.User;

public class JobPropositionDTO {

    private Integer idJobProposition;
    private Integer idUser;
    private String name;
    private String lastName;
    private String companyName;
    private String companyAddress;
    private String positionName;
    private String positionDescription;
    private String salary;
    private String webLinkOffer;
    private String contactType;
    private String decision;
    private User fromUser;
    private User toUser;

    public JobPropositionDTO(Integer idJobProposition, Integer idUser, String name, String lastName, String companyName, String companyAddress, String positionName, String positionDescription, String salary, String webLinkOffer, String contactType, String decision, User fromUser, User toUser) {
        this.idJobProposition = idJobProposition;
        this.idUser = idUser;
        this.name = name;
        this.lastName = lastName;
        this.companyName = companyName;
        this.companyAddress = companyAddress;
        this.positionName = positionName;
        this.positionDescription = positionDescription;
        this.salary = salary;
        this.webLinkOffer = webLinkOffer;
        this.contactType = contactType;
        this.decision = decision;
        this.fromUser = fromUser;
        this.toUser = toUser;
    }

    public JobPropositionDTO() {
    }

    @Override
    public String toString() {
        return "JobPropositionDTO{" +
                "idJobProposition=" + idJobProposition +
                ", idUser=" + idUser +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", companyName='" + companyName + '\'' +
                ", companyAddress='" + companyAddress + '\'' +
                ", positionName='" + positionName + '\'' +
                ", positionDescription='" + positionDescription + '\'' +
                ", salary='" + salary + '\'' +
                ", webLinkOffer='" + webLinkOffer + '\'' +
                ", contactType='" + contactType + '\'' +
                ", decision='" + decision + '\'' +
                ", fromUser=" + fromUser +
                ", toUser=" + toUser +
                '}';
    }

    public Integer getIdJobProposition() {
        return idJobProposition;
    }

    public void setIdJobProposition(Integer idJobProposition) {
        this.idJobProposition = idJobProposition;
    }

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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getPositionDescription() {
        return positionDescription;
    }

    public void setPositionDescription(String positionDescription) {
        this.positionDescription = positionDescription;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getWebLinkOffer() {
        return webLinkOffer;
    }

    public void setWebLinkOffer(String webLinkOffer) {
        this.webLinkOffer = webLinkOffer;
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

    public User getFromUser() {
        return fromUser;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }

    public User getToUser() {
        return toUser;
    }

    public void setToUser(User toUser) {
        this.toUser = toUser;
    }
}

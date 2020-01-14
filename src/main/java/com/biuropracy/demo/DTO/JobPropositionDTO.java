package com.biuropracy.demo.DTO;

import com.biuropracy.demo.model.User;

public class JobPropositionDTO {

    private Integer idJobProposition;
    private Integer idEmployer;
    private Integer idUser;
    private String companyName;
    private String positionName;
    private String name;
    private String lastName;
    private String positionDescription;
    private String salary;
    private String webLinkOffer;
    private String contactType;
    private String decision;

    public JobPropositionDTO(Integer idJobProposition, Integer idUser, String positionName, String name, String lastName, String positionDescription, String salary, String webLinkOffer, String contactType, String decision) {
        this.idJobProposition = idJobProposition;
        this.idUser = idUser;
        this.positionName = positionName;
        this.name = name;
        this.lastName = lastName;
        this.positionDescription = positionDescription;
        this.salary = salary;
        this.webLinkOffer = webLinkOffer;
        this.contactType = contactType;
        this.decision = decision;
    }

    public JobPropositionDTO(Integer idJobProposition, Integer idEmployer, String companyName, String positionName, String positionDescription, String salary, String webLinkOffer, String contactType, String decision) {
        this.idJobProposition = idJobProposition;
        this.idEmployer = idEmployer;
        this.companyName = companyName;
        this.positionName = positionName;
        this.positionDescription = positionDescription;
        this.salary = salary;
        this.webLinkOffer = webLinkOffer;
        this.contactType = contactType;
        this.decision = decision;
    }

    public JobPropositionDTO() {
    }

    public Integer getIdJobProposition() {
        return idJobProposition;
    }

    public void setIdJobProposition(Integer idJobProposition) {
        this.idJobProposition = idJobProposition;
    }

    public Integer getIdEmployer() {
        return idEmployer;
    }

    public void setIdEmployer(Integer idEmployer) {
        this.idEmployer = idEmployer;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
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
}

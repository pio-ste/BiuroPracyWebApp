package com.biuropracy.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "job_proposition")
public class JobProposition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idjob_proposition")
    private Integer idJobProposition;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "company_address")
    private String companyAddress;

    @Column(name = "position_name")
    private String positionName;

    @Column(name = "position_description")
    private String positionDescription;

    @Column(name = "salary")
    private String salary;

    @Column(name = "web_link_offer")
    private String webLinkOffer;

    @Column(name = "contact_type")
    private String contactType;

    @Column(name = "decision")
    private String decision;

    @ManyToOne
    @JoinColumn(name = "from_user_id")
    private User fromUser;

    @ManyToOne
    @JoinColumn(name = "to_user_id")
    private User toUser;

    public JobProposition(String companyName, String companyAddress, String positionName, String positionDescription, String salary, String webLinkOffer, String contactType, String decision, User fromUser, User toUser) {
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

    public JobProposition() {
    }

    public Integer getIdJobProposition() {
        return idJobProposition;
    }

    public void setIdJobProposition(Integer idJobProposition) {
        this.idJobProposition = idJobProposition;
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

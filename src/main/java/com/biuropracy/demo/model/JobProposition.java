package com.biuropracy.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "job_proposition")
public class JobProposition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idjob_proposition")
    private Integer idJobProposition;

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
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_employer")
    private Employer employer;

    public JobProposition(String positionName, String positionDescription, String salary, String webLinkOffer, String contactType, String decision, User user, Employer employer) {
        this.positionName = positionName;
        this.positionDescription = positionDescription;
        this.salary = salary;
        this.webLinkOffer = webLinkOffer;
        this.contactType = contactType;
        this.decision = decision;
        this.user = user;
        this.employer = employer;
    }

    public JobProposition() {
    }

    public Integer getIdJobProposition() {
        return idJobProposition;
    }

    public void setIdJobProposition(Integer idJobProposition) {
        this.idJobProposition = idJobProposition;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }
}

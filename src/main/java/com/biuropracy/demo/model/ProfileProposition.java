package com.biuropracy.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "profile_proposition")
public class ProfileProposition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idprofile_proposition")
    private Integer idProfileProposition;

    @Column(name = "substantiation")
    private String substantiation;

    @Column(name = "contact_type")
    private String contactType;

    @Column(name = "decision")
    private String decision;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "idjob_offer")
    private JobOffer jobOffer;

    public ProfileProposition(String substantiation, String contactType, String decision, User user, JobOffer jobOffer) {
        this.substantiation = substantiation;
        this.contactType = contactType;
        this.decision = decision;
        this.user = user;
        this.jobOffer = jobOffer;
    }

    public ProfileProposition() {
    }

    public Integer getIdProfileProposition() {
        return idProfileProposition;
    }

    public void setIdProfileProposition(Integer idProfileProposition) {
        this.idProfileProposition = idProfileProposition;
    }

    public String getSubstantiation() {
        return substantiation;
    }

    public void setSubstantiation(String substantiation) {
        this.substantiation = substantiation;
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

    public JobOffer getJobOffer() {
        return jobOffer;
    }

    public void setJobOffer(JobOffer jobOffer) {
        this.jobOffer = jobOffer;
    }
}

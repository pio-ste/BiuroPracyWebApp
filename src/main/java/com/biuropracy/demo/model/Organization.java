package com.biuropracy.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "organization")
public class Organization {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(name = "id_organization")
    private Integer idOrganization;

    @Column(name = "organization_name")
    private String organizationName;

    @Column(name = "location")
    private String location;

    @Column(name = "from_month")
    private String fromMonth;

    @Column(name = "from_year")
    private String fromYear;

    @Column(name = "to_month")
    private String toMonth;

    @Column(name = "to_year")
    private String toYear;

    @Column(name = "continues")
    private String continues;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    public Organization(String organizationName, String location, String fromMonth, String fromYear, String toMonth, String toYear, String continues, String description, User user) {
        this.organizationName = organizationName;
        this.location = location;
        this.fromMonth = fromMonth;
        this.fromYear = fromYear;
        this.toMonth = toMonth;
        this.toYear = toYear;
        this.continues = continues;
        this.description = description;
        this.user = user;
    }

    public Organization() {
    }

    public Integer getIdOrganization() {
        return idOrganization;
    }

    public void setIdOrganization(Integer idOrganization) {
        this.idOrganization = idOrganization;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getFromMonth() {
        return fromMonth;
    }

    public void setFromMonth(String fromMonth) {
        this.fromMonth = fromMonth;
    }

    public String getFromYear() {
        return fromYear;
    }

    public void setFromYear(String fromYear) {
        this.fromYear = fromYear;
    }

    public String getToMonth() {
        return toMonth;
    }

    public void setToMonth(String toMonth) {
        this.toMonth = toMonth;
    }

    public String getToYear() {
        return toYear;
    }

    public void setToYear(String toYear) {
        this.toYear = toYear;
    }

    public String getContinues() {
        return continues;
    }

    public void setContinues(String continues) {
        this.continues = continues;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

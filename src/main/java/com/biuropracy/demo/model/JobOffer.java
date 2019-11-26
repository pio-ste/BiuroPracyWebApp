package com.biuropracy.demo.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "job_offer")
public class JobOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idjob_offer")
    private Integer id;

    @Column(name = "title", nullable = false)
    @NotEmpty(message = "Wpisz tytuł ogłoszenia")
    private String title;

    @Column(name = "location", nullable = false)
    @NotEmpty(message = "Wpisz lokalizację")
    private String location;

    @Column(name = "description" , nullable = false)
    @NotEmpty(message = "Dodaj opis")
    private String description;

    @Column(name = "contact", nullable = false)
    @NotEmpty(message = "Podaj dane kontaktowe")
    private String contact;

    @Column(name = "category", nullable = false)
    @NotEmpty(message = "Wybierz kategorię")
    private String category;

    @Column(name = "company_name", nullable = false)
    @NotEmpty(message = "Wpisz nazwę firmy")
    private String companyName;

    @Column(name = "contract_type", nullable = false)
    @NotEmpty(message = "Wybierz rodzaj umowy")
    private String contractType;

    @Column(name = "working_time", nullable = false)
    @NotEmpty(message = "Wybierz wymiar pracy")
    private String workingTime;

    @Column(name = "monthly_pay", nullable = false)
    @NotEmpty(message = "Wpisz miesięczne wynagrodzenia")
    private String monthlyPay;

    @Column(name = "position_level", nullable = false)
    @NotEmpty(message = "Wpisz poziom stanowiska")
    private String positionLevel;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;



    public JobOffer() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getWorkingTime() {
        return workingTime;
    }

    public void setWorkingTime(String workingTime) {
        this.workingTime = workingTime;
    }

    public String getMonthlyPay() {
        return monthlyPay;
    }

    public void setMonthlyPay(String monthlyPay) {
        this.monthlyPay = monthlyPay;
    }

    public String getPositionLevel() {
        return positionLevel;
    }

    public void setPositionLevel(String positionLevel) {
        this.positionLevel = positionLevel;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

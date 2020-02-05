package com.biuropracy.demo.DTO;

public class JobOfferDTO {
    private Integer idEmployer;
    private Integer idJobOffer;
    private Integer idProfileProposition;
    private String title;
    private String location;
    private String description;
    private String contact;
    private String category;
    private String categorySalary;
    private String companyName;
    private String contractType;
    private String workingTime;
    private Integer monthlyPay;
    private String positionLevel;
    private String email;

    public JobOfferDTO(Integer idJobOffer, String title, String location, String category, String categorySalary, String companyName, Integer monthlyPay) {
        this.idJobOffer = idJobOffer;
        this.title = title;
        this.location = location;
        this.category = category;
        this.categorySalary = categorySalary;
        this.companyName = companyName;
        this.monthlyPay = monthlyPay;
    }

    public JobOfferDTO(Integer idEmployer, Integer idJobOffer, String title, String location, String description, String contact, String category, String categorySalary, String companyName, String contractType, String workingTime, Integer monthlyPay, String positionLevel) {
        this.idEmployer = idEmployer;
        this.idJobOffer = idJobOffer;
        this.title = title;
        this.location = location;
        this.description = description;
        this.contact = contact;
        this.category = category;
        this.categorySalary = categorySalary;
        this.companyName = companyName;
        this.contractType = contractType;
        this.workingTime = workingTime;
        this.monthlyPay = monthlyPay;
        this.positionLevel = positionLevel;
    }

    public Integer getIdEmployer() {
        return idEmployer;
    }

    public void setIdEmployer(Integer idEmployer) {
        this.idEmployer = idEmployer;
    }

    public Integer getIdJobOffer() {
        return idJobOffer;
    }

    public void setIdJobOffer(Integer idJobOffer) {
        this.idJobOffer = idJobOffer;
    }

    public Integer getIdProfileProposition() {
        return idProfileProposition;
    }

    public void setIdProfileProposition(Integer idProfileProposition) {
        this.idProfileProposition = idProfileProposition;
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

    public String getCategorySalary() {
        return categorySalary;
    }

    public void setCategorySalary(String categorySalary) {
        this.categorySalary = categorySalary;
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

    public Integer getMonthlyPay() {
        return monthlyPay;
    }

    public void setMonthlyPay(Integer monthlyPay) {
        this.monthlyPay = monthlyPay;
    }

    public String getPositionLevel() {
        return positionLevel;
    }

    public void setPositionLevel(String positionLevel) {
        this.positionLevel = positionLevel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

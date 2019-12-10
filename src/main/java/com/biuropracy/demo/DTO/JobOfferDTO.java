package com.biuropracy.demo.DTO;





public class JobOfferDTO {

    private Integer idUser;
    private Integer idJobOffer;
    private String title;
    private String location;
    private String description;
    private String contact;
    private String category;
    private String companyName;
    private String contractType;
    private String workingTime;
    private String monthlyPay;
    private String positionLevel;
    private String email;

    public JobOfferDTO() {
    }

    public JobOfferDTO(String title, String location, String category, String monthlyPay, String email) {
        this.title = title;
        this.location = location;
        this.category = category;
        this.monthlyPay = monthlyPay;
        this.email = email;
    }

    public JobOfferDTO(Integer idUser, Integer idJobOffer, String title, String location, String description, String contact, String category, String companyName, String contractType, String workingTime, String monthlyPay, String positionLevel, String email) {
        this.idUser = idUser;
        this.idJobOffer = idJobOffer;
        this.title = title;
        this.location = location;
        this.description = description;
        this.contact = contact;
        this.category = category;
        this.companyName = companyName;
        this.contractType = contractType;
        this.workingTime = workingTime;
        this.monthlyPay = monthlyPay;
        this.positionLevel = positionLevel;
        this.email = email;
    }

    @Override
    public String toString() {
        return "JobOfferDTO{" +
                "idUser=" + idUser +
                ", idJobOffer=" + idJobOffer +
                ", title='" + title + '\'' +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                ", contact='" + contact + '\'' +
                ", category='" + category + '\'' +
                ", companyName='" + companyName + '\'' +
                ", contractType='" + contractType + '\'' +
                ", workingTime='" + workingTime + '\'' +
                ", monthlyPay='" + monthlyPay + '\'' +
                ", positionLevel='" + positionLevel + '\'' +
                ", email='" + email + '\'' +
                '}';
    }


    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getIdJobOffer() {
        return idJobOffer;
    }

    public void setIdJobOffer(Integer idJobOffer) {
        this.idJobOffer = idJobOffer;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getEmail() {
        return email;
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

    public void setEmail(String email) {
        this.email = email;
    }
}

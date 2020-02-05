package com.biuropracy.demo.DTO;

public class ProfilePropositionDTO {

    private Integer idProfileProposition;
    private Integer idUser;
    private Integer idJobOffer;
    private Integer idEmployer;
    private String companyName;
    private Integer monthlyPay;
    private String categorySalary;
    private String title;
    private String location;
    private String name;
    private String lastName;
    private Byte[] profileImage;
    private String contactType;
    private String substantiation;
    private String decision;

    public ProfilePropositionDTO(Integer idProfileProposition, Integer idJobOffer, Integer idEmployer, String companyName, Integer monthlyPay, String categorySalary, String title, String location, String contactType, String substantiation, String decision) {
        this.idProfileProposition = idProfileProposition;
        this.idJobOffer = idJobOffer;
        this.idEmployer = idEmployer;
        this.companyName = companyName;
        this.monthlyPay = monthlyPay;
        this.categorySalary = categorySalary;
        this.title = title;
        this.location = location;
        this.contactType = contactType;
        this.substantiation = substantiation;
        this.decision = decision;
    }

    public ProfilePropositionDTO(Integer idProfileProposition, Integer idUser, Integer idJobOffer, String title, String location, String name, String lastName, String contactType, String substantiation, String decision) {
        this.idProfileProposition = idProfileProposition;
        this.idUser = idUser;
        this.idJobOffer = idJobOffer;
        this.title = title;
        this.location = location;
        this.name = name;
        this.lastName = lastName;
        this.contactType = contactType;
        this.substantiation = substantiation;
        this.decision = decision;
    }

    public ProfilePropositionDTO() {
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

    public Integer getIdEmployer() {
        return idEmployer;
    }

    public void setIdEmployer(Integer idEmployer) {
        this.idEmployer = idEmployer;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getMonthlyPay() {
        return monthlyPay;
    }

    public void setMonthlyPay(Integer monthlyPay) {
        this.monthlyPay = monthlyPay;
    }

    public String getCategorySalary() {
        return categorySalary;
    }

    public void setCategorySalary(String categorySalary) {
        this.categorySalary = categorySalary;
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

    public String getSubstantiation() {
        return substantiation;
    }

    public void setSubstantiation(String substantiation) {
        this.substantiation = substantiation;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }
}

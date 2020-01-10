package com.biuropracy.demo.DTO;

public class EmployerUserDTO {

    private Integer idUser;
    private String name;
    private String lastName;
    private String email;
    private Integer idEmployer;
    private String companyName;
    private String phoneNumber;
    private String companyEmail;
    private String webLink;
    private String address;
    private String description;

    public EmployerUserDTO(Integer idUser, String name, String lastName, String email, Integer idEmployer, String companyName, String phoneNumber, String companyEmail, String webLink, String address, String description) {
        this.idUser = idUser;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.idEmployer = idEmployer;
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.companyEmail = companyEmail;
        this.webLink = webLink;
        this.address = address;
        this.description = description;
    }

    public EmployerUserDTO(Integer idEmployer, String companyName, String webLink, String address) {
        this.idEmployer = idEmployer;
        this.companyName = companyName;
        this.webLink = webLink;
        this.address = address;
    }

    @Override
    public String toString() {
        return "EmployerUserDTO{" +
                "idUser=" + idUser +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", idEmployer=" + idEmployer +
                ", companyName='" + companyName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", companyEmail='" + companyEmail + '\'' +
                ", webLink='" + webLink + '\'' +
                ", address='" + address + '\'' +
                ", description='" + description + '\'' +
                '}';
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public String getWebLink() {
        return webLink;
    }

    public void setWebLink(String webLink) {
        this.webLink = webLink;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

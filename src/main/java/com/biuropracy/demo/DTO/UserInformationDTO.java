package com.biuropracy.demo.DTO;

public class UserInformationDTO {

    private Integer idUser;
    private String name;
    private String lastName;
    private String email;
    private String userPhone;
    private Integer idUserDetail;
    private String dateBirth;
    private String homeCity;
    private String workCity;
    private String hobby;
    private String currentPosition;
    private String positionSought;

    public UserInformationDTO(Integer idUser, String name, String lastName, String email, String userPhone, Integer idUserDetail, String dateBirth, String homeCity, String workCity, String hobby, String currentPosition, String positionSought) {
        this.idUser = idUser;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.userPhone = userPhone;
        this.idUserDetail = idUserDetail;
        this.dateBirth = dateBirth;
        this.homeCity = homeCity;
        this.workCity = workCity;
        this.hobby = hobby;
        this.currentPosition = currentPosition;
        this.positionSought = positionSought;
    }

    public UserInformationDTO() {
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

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public Integer getIdUserDetail() {
        return idUserDetail;
    }

    public void setIdUserDetail(Integer idUserDetail) {
        this.idUserDetail = idUserDetail;
    }

    public String getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(String dateBirth) {
        this.dateBirth = dateBirth;
    }

    public String getHomeCity() {
        return homeCity;
    }

    public void setHomeCity(String homeCity) {
        this.homeCity = homeCity;
    }

    public String getWorkCity() {
        return workCity;
    }

    public void setWorkCity(String workCity) {
        this.workCity = workCity;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(String currentPosition) {
        this.currentPosition = currentPosition;
    }

    public String getPositionSought() {
        return positionSought;
    }

    public void setPositionSought(String positionSought) {
        this.positionSought = positionSought;
    }
}

package com.biuropracy.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "user_details")
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user_details")
    private Integer idUserDetail;

    @Column(name = "date_birth")
    private String dateBirth;

    @Column(name = "home_city")
    private String homeCity;

    @Column(name = "work_city")
    private String workCity;

    @Column(name = "hobby")
    private String hobby;

    @Column(name = "current_position")
    private String currentPosition;

    @Column(name = "position_sought")
    private String positionSought;

    @Column(name = "to_find")
    private String toFind;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(unique = true)
    private User user;

    public UserDetails(String dateBirth, String homeCity, String workCity, String hobby, String currentPosition, String positionSought, String toFind, User user) {
        this.dateBirth = dateBirth;
        this.homeCity = homeCity;
        this.workCity = workCity;
        this.hobby = hobby;
        this.currentPosition = currentPosition;
        this.positionSought = positionSought;
        this.toFind = toFind;
        this.user = user;
    }

    public UserDetails() {
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

    public String getToFind() {
        return toFind;
    }

    public void setToFind(String toFind) {
        this.toFind = toFind;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

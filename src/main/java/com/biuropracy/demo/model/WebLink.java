package com.biuropracy.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "web_link")
public class WebLink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_web_link")
    private Integer idWebLink;

    @Column(name = "url_address")
    private String urlAddress;

    @Column(name = "url_type")
    private String urlType;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    public WebLink(String urlAddress, String urlType, User user) {
        this.urlAddress = urlAddress;
        this.urlType = urlType;
        this.user = user;
    }

    public WebLink() {
    }

    public Integer getIdWebLink() {
        return idWebLink;
    }

    public void setIdWebLink(Integer idWebLink) {
        this.idWebLink = idWebLink;
    }

    public String getUrlAddress() {
        return urlAddress;
    }

    public void setUrlAddress(String urlAddress) {
        this.urlAddress = urlAddress;
    }

    public String getUrlType() {
        return urlType;
    }

    public void setUrlType(String urlType) {
        this.urlType = urlType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

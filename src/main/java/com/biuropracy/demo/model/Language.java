package com.biuropracy.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "language")
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_language")
    private Integer idLanguage;

    @Column(name = "language_name")
    private String languageNmae;

    @Column(name = "language_level")
    private String languageLevel;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    public Language(String languageNmae, String languageLevel, User user) {
        this.languageNmae = languageNmae;
        this.languageLevel = languageLevel;
        this.user = user;
    }

    public Language() {
    }

    public Integer getIdLanguage() {
        return idLanguage;
    }

    public void setIdLanguage(Integer idLanguage) {
        this.idLanguage = idLanguage;
    }

    public String getLanguageNmae() {
        return languageNmae;
    }

    public void setLanguageNmae(String languageNmae) {
        this.languageNmae = languageNmae;
    }

    public String getLanguageLevel() {
        return languageLevel;
    }

    public void setLanguageLevel(String languageLevel) {
        this.languageLevel = languageLevel;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

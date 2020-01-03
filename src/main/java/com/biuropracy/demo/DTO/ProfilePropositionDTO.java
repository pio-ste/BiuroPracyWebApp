package com.biuropracy.demo.DTO;

public class ProfilePropositionDTO {

    private Integer idProfileProposition;
    private Integer idUser;
    private Integer idJobOffer;
    private String title;
    private String category;
    private String contactType;
    private String decision;

    public ProfilePropositionDTO(Integer idProfileProposition, Integer idUser, Integer idJobOffer, String title, String category, String contactType, String decision) {
        this.idProfileProposition = idProfileProposition;
        this.idUser = idUser;
        this.idJobOffer = idJobOffer;
        this.title = title;
        this.category = category;
        this.contactType = contactType;
        this.decision = decision;
    }

    public ProfilePropositionDTO() {
    }

    @Override
    public String toString() {
        return "ProfilePropositionDTO{" +
                "idProfileProposition=" + idProfileProposition +
                ", idUser=" + idUser +
                ", idJobOffer=" + idJobOffer +
                ", title='" + title + '\'' +
                ", category='" + category + '\'' +
                ", contactType='" + contactType + '\'' +
                ", decision='" + decision + '\'' +
                '}';
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContactType() {
        return contactType;
    }

    public void setContactType(String contactType) {
        this.contactType = contactType;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }
}

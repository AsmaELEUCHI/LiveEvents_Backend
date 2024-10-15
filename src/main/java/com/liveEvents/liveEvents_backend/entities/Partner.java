package com.liveEvents.liveEvents_backend.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="partners")
public class Partner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //génération de clé automatique
    private Long id;

    private String partnerName;


    private String partnerImage;


    private String partnerDescription;


    private String partnerLink;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id=id;
    }

    public String getPartnerName(){
        return partnerName;
    }

    public void setPartnerName(String partnerName){
        this.partnerName=partnerName;
    }

    public String getPartnerImage(){
        return partnerImage;
    }
    public void setPartnerImage(String partnerImage){
        this.partnerImage=partnerImage;
    }
    public String getPartnerDescription(){
        return partnerDescription;
    }
    public void setPartnerDescription(String partnerDescription){
        this.partnerDescription=partnerDescription;
    }
    public String getPartnerLink(){
        return partnerLink;
    }
    public void setPartnerLink(String partnerLink){
        this.partnerLink=partnerLink;
    }
}

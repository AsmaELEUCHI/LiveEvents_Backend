package com.liveEvents.liveEvents_backend.dtos;


import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PartnerDto {

    @Id
    private Long id;

    @NotBlank(message = "Le nom du partenaire est requis")
    @Size(min=1, max=50, message ="Le nom doit du partenaire contenir entre 1 et 50 caractères")
    private String partnerName;

    @NotBlank(message = " l'URL de l'image du partenaire est requise")
    @Size(min=1, max=100, message =" l'URL de l'image du partenaire doit contenir entre 1 et 100 caractères")
    private String partnerImage;

    @NotBlank(message = " la description du partenaire est requise")
    @Size(min=1, max=500, message ="La description doit contenir entre 1 et 500 caractères")
    private String partnerDescription;

    @NotBlank(message ="L'URL du lien est requise")
    @Size(min=1, max=100, message ="L'URL du lien doit contenir entre 1 et 100 caractères")
    private String partnerLink;

    private String message;

    public PartnerDto(){}
    public PartnerDto(Long id, String partnerName, String partnerImage, String partnerDescription, String partnerLink){
        this.id=id;
        this.partnerName=partnerName;
        this.partnerImage=partnerImage;
        this.partnerDescription=partnerDescription;
        this.partnerLink=partnerLink;
    }


    public PartnerDto(String message) {
        this.message = message;
    }

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
    public String getMessage(){
        return message;
    }
    public void setMessage(String message){
        this.message=message;
    }

}

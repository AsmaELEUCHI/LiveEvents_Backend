package com.liveEvents.liveEvents_backend.dtos;


import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public class LegalNoticeDto {

    @Id
    private Long id;

    @NotBlank(message = "Le titre de la mention légale est requis")
    @Size(min=1, max=50, message ="Le titre de la mention légale  doit contenir entre 1 et 50 caractères")
    private String title;

    @NotBlank(message = "Le contenu de la mention légale est requis")
    @Size(min=1, max=500, message ="Le contenu de la mention légale doit contenir entre 1 et 500 caractères")
    private String content;

    @NotBlank(message = "Le nom de l'auteur de la mention légale est requis")
    @Size(min=1, max=50, message ="Le nom de l'auteur de la mention légale doit contenir entre 1 et 50 caractères")
    private String createdBy;



    public LegalNoticeDto(){};

    public LegalNoticeDto(Long id,String title, String content, String createdBy){
        this.id=id;
        this.title=title;
        this.content=content;
        this.createdBy=createdBy;

    }



    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id=id;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }



}

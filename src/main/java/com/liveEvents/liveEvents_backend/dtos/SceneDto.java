package com.liveEvents.liveEvents_backend.dtos;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class SceneDto {

    @Id
    private Long id;

    @NotBlank(message = "Le nom de la scéne est requise")
    @Size(min=1, max=50, message ="Le nom la scéne doit contenir entre 1 et 50 caractères")
    private String sceneName;

    private String message;

    public SceneDto(){};
    public SceneDto(Long id, String sceneName){
        this.id=id;
        this.sceneName=sceneName;
    }

    public SceneDto(String message) {
        this.message = message; // Utilisez un attribut approprié pour stocker le message
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id=id;
    }
    public String getSceneName(){
        return sceneName;
    }

    public void setSceneName(String sceneName){
        this.sceneName=sceneName;
    }

    public String getMessage(){
        return message;
    }
    public void setMessage(String message){
        this.message=message;
    }
}

package com.liveEvents.liveEvents_backend.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="scenes")
public class Scene {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String sceneName;

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
}

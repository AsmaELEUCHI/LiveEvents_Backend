package com.liveEvents.liveEvents_backend.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

@Entity
@Table(name="map")
public class Map {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //génération de clé automatique
    private Long id;


    private Integer zoomLevel;


    private Double latitude;


    private Double longitude;


    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id=id;
    }
    public Integer getZoomLevel(){
        return zoomLevel;
    }
    public void setZoomLevel(Integer zoomLevel){
        this.zoomLevel=zoomLevel;
    }
    public Double getLatitude(){
        return latitude;
    }
    public void setLatitude(Double latitude){
        this.latitude=latitude;
    }
    public Double getLongitude(){
        return longitude;
    }
    public void setLongitude(Double longitude){
        this.longitude=longitude;
    }

}

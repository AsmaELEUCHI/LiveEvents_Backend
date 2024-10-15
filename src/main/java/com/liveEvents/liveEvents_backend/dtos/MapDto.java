package com.liveEvents.liveEvents_backend.dtos;


import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

public class MapDto {



    @Id
    private Long id;

    @NotNull(message = "Le niveau de zoom de la carte est requis")
    private Integer zoomLevel;

    @NotNull(message = "la latitude est requise")
    private Double latitude;

    @NotNull(message = "la longitude est requise")
    private Double longitude;

    private String message;



    public MapDto (){}
    public MapDto(Long id, Integer zoomLevel, Double latitude, Double longitude){
        this.id=id;
        this.zoomLevel=zoomLevel;
        this.latitude=latitude;
        this.longitude=longitude;
    }

    public MapDto(String message) {
        this.message = message;
    }
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

    public String getMessage(){
        return message;
    }
    public void setMessage(String message){
        this.message=message;
    }
}

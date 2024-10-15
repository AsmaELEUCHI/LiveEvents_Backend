package com.liveEvents.liveEvents_backend.dtos;

import com.liveEvents.liveEvents_backend.entities.Map;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class MarkerDto {


    @Id
    private Long id;

    @NotBlank(message = "Le nom du lieu est requis")
    @Size(min=1, max=50, message ="Le nom du lieu doit contenir entre 1 et 50 caractères")
    private String placeName;

    @NotBlank(message = "L'adresse du lieu est requise")
    @Size(min=1, max=50, message ="L'adresse' doit contenir entre 1 et 50 caractères")
    private String address;

    @NotBlank(message = "L'Url de l'icone' est requise")
    @Size(min=1, max=100, message ="L'url de l'icone doit contenir entre 1 et 100 caractères")
    private String iconUrl;

    @NotNull(message = "la latitude est requise")
    private Double latitude;

    @NotNull(message = "la longitude est requise")
    private Double longitude;

    @NotBlank(message = "La catégorie du lieu est requise")
    @Size(min=1, max=50, message ="Le catégorie doit contenir entre 1 et 50 caractères")
    private String category;

    @NotNull(message = "L'ID de la carte est requis")
    private Long mapId;

    private String message;

    public MarkerDto (){}
    public MarkerDto(Long id, String placeName, String address, String iconUrl, Double latitude, Double longitude, String category, Long mapId){
        this.id=id;
        this.placeName=placeName;
        this.address=address;
        this.iconUrl=iconUrl;
        this.latitude=latitude;
        this.longitude=longitude;
        this.category=category;
        this.mapId=mapId;
    }

    public MarkerDto(String message) {
        this.message = message;
    }

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id=id;
    }
    public String getPlaceName(){
        return placeName;
    }
    public void setPlaceName(String placeName){
        this.placeName=placeName;
    }
    public String getAddress(){
        return address;
    }
    public void setAddress(String address){
        this.address=address;
    }
    public String getIconUrl(){
        return iconUrl;
    }
    public void setIconUrl(String iconUrl){
        this.iconUrl=iconUrl;
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
    public String getCategory(){
        return category;
    }
    public void setCategory(String category){
        this.category=category;
    }
    public Long getMapId(){
        return mapId;
    }
    public void setMapId(Long mapId){
        this.mapId=mapId;
    }
    public String getMessage(){
        return message;
    }
    public void setMessage(String message){
        this.message=message;
    }
}

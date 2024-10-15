package com.liveEvents.liveEvents_backend.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="markers")
public class Marker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String placeName;


    private String address;


    private String iconUrl;


    private Double latitude;


    private Double longitude;


    private String category;

    @ManyToOne
    @JoinColumn(name="map_id")
    private Map map;

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
    public Map getMap(){
        return map;
    }
    public void setMap(Map map){
        this.map=map;
    }
}

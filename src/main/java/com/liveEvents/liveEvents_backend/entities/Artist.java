package com.liveEvents.liveEvents_backend.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
@Table(name="artists")
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String artistName;


    private String artistImage;


    private String artistBiography;



    private String futureFestivalName;

    private LocalDate futureDate;


    private String futureLocation;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id=id;
    }
    public String getArtistName(){
        return artistName;
    }

    public void setArtistName(String artistName){
        this.artistName=artistName;
    }

    public String getArtistImage(){
        return artistImage;
    }

    public void setArtistImage(String artistImage){
        this.artistImage=artistImage;
    }

    public String getArtistBiography(){
        return artistBiography;
    }

    public void setArtistBiography(String artistBiography){
        this.artistBiography=artistBiography;
    }

    public String getFutureFestivalName(){
        return futureFestivalName;
    }
    public void setFutureFestivalName(String futureFestivalName){
        this.futureFestivalName=futureFestivalName;
    }
    public LocalDate getFutureDate(){
        return futureDate;
    }
    public void setFutureDate(LocalDate futureDate){
        this.futureDate=futureDate;
    }
    public String getFutureLocation(){
        return futureLocation;
    }
    public void setFutureLocation(String futureLocation){
        this.futureLocation=futureLocation;
    }


}

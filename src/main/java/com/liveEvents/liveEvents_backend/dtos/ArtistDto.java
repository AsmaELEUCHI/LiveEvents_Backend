package com.liveEvents.liveEvents_backend.dtos;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class ArtistDto {

    @Id
    private Long id;

    @NotBlank(message = "Le nom du l'artiste est requis")
    @Size(min=1, max=50, message ="Le nom l'artiste doit contenir entre 1 et 50 caractères")
    private String artistName;

    @NotBlank(message = " l'URL de l'image de l'artiste est requise")
    @Size(min=1, max=100, message =" l'URL de l'image de l'artiste doit contenir entre 1 et 100 caractères")
    private String artistImage;

    @NotBlank(message = " la biographie de l'artiste est requise")
    @Size(min=1, max=500, message =" La biographie de l'artiste doit contenir entre 1 et 500 caractères")
    private String artistBiography;


    @Size(min=1, max=50, message =" La biographie de l'artiste doit contenir entre 1 et 50 caractères")
    private String futureFestivalName;

    private LocalDate futureDate;

    @Size(min=1, max=50, message =" La biographie de l'artiste doit contenir entre 1 et 50 caractères")
    private String futureLocation;

    private String message;


    public ArtistDto (){}
    public ArtistDto(Long id, String artistName, String artistImage, String artistBiography){
        this.id=id;
        this.artistName=artistName;
        this.artistImage=artistImage;
        this.artistBiography=artistBiography;
    }

    public ArtistDto(Long id, String artistName, String artistImage, String artistBiography, String futureFestivalName,LocalDate futureDate,String futureLocation){
        this.id=id;
        this.artistName=artistName;
        this.artistImage=artistImage;
        this.artistBiography=artistBiography;
        this.futureFestivalName=futureFestivalName;
        this.futureDate=futureDate;
        this.futureLocation=futureLocation;
    }
    public ArtistDto(String message) {
        this.message = message;
    }

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

    public String getMessage(){
        return message;
    }
    public void setMessage(String message){
        this.message=message;
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

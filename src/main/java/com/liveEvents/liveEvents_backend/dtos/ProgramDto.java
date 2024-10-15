package com.liveEvents.liveEvents_backend.dtos;


import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

public class ProgramDto {

    @Id
    private Long id;

    @NotNull(message = "L'ID de l'artiste est requis")
    private Long artistId;

    @NotNull(message = "L'ID de la scène est requis")
    private Long sceneId;

    @NotBlank(message = " la date du programme est requise")
    private String date;

    @NotBlank(message = " l'heure de début du programme est requise")
    private String startTime;
    @NotBlank(message = " l'heure de début du programme est requise")
    private String endTime;

    private String message;


    public ProgramDto (){}
    public ProgramDto(Long id, Long artistId, Long sceneId, String date,String startTime, String endTime){
        this.id=id;
        this.artistId=artistId;
        this.sceneId=sceneId;
        this.date=date;
        this.startTime=startTime;
        this.endTime=endTime;
    }

    public ProgramDto(String message) {
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getArtistId() {
        return artistId;
    }

    public void setArtistId(Long artistId) {
        this.artistId = artistId;
    }

    public Long getSceneId() {
        return sceneId;
    }

    public void setSceneId(Long sceneId) {
        this.sceneId = sceneId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }



    public String getMessage(){
        return message;
    }
    public void setMessage(String message){
        this.message=message;
    }

}

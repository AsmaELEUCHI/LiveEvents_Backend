package com.liveEvents.liveEvents_backend.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
@Table(name="generalPushNotifications")
public class GeneralPushNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String title;


    private String message;

    private LocalDateTime notificationDate;

    public GeneralPushNotification() {
        this.notificationDate = LocalDateTime.now();  // Timestamp automatique lors de la création
    }

    @ManyToOne
    @JoinColumn(name = "program_id", nullable = false)
    private Program program;



    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id=id;
    }


    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title=title;
    }

    public String getMessage(){
        return message;
    }
    public void setMessage(String message){
        this.message=message;
    }

    public LocalDateTime getNotificationDate(){
        return notificationDate;
    }

    public void  setNotificationDate(LocalDateTime notificationDate){
        this.notificationDate=notificationDate;
    }
    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }
}

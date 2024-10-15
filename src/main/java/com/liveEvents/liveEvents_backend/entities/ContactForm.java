package com.liveEvents.liveEvents_backend.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "contact_formulaire")
public class ContactForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String userFirstName;


    private String userLastName;


    private String email;


    private String message;


    private String response;

    private String status;


    public ContactForm (){}
    public ContactForm(Long id,String userFirstName,String userLastName,String email, String message ){
        this.id=id;
        this.userFirstName=userFirstName;
        this.userLastName=userLastName;
        this.email=email;
        this.message=message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResponse(){
        return response;
    }

    public void setResponse(String response){
        this.response=response;
    }
    public String getStatus(){
        return status;
    }
    public void setStatus(String status){
        this.status=status;
    }

}

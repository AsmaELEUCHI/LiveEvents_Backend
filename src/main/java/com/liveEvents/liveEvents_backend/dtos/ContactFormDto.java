package com.liveEvents.liveEvents_backend.dtos;


import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ContactFormDto {

    @Id
    private Long id;

    @NotBlank(message = "Le prénom de l'utilisateur est requis")
    @Size(min=1, max=50, message ="Le prénom de l'utilisateur doit contenir entre 1 et 50 caractères")
    private String userFirstName;

    @NotBlank(message = "Le nom de l'utilisateur est requis")
    @Size(min=1, max=50, message ="Le nom de l'utilisateur doit contenir entre 1 et 50 caractères")
    private String userLastName;

    @NotBlank(message = "L'email est requis")
    @Email(message = "L'email doit être valide")
    private String email;

    @NotBlank(message = "Le nom de l'utilisateur est requis")
    @Size(min=1, max=500, message ="Le nom de l'utilisateur doit contenir entre 1 et 500 caractères")
    private String message;

    @Size(min=1, max=500, message ="Le nom de l'utilisateur doit contenir entre 1 et 500 caractères")
    private String response;

    private String status;

    public ContactFormDto (){}
    public ContactFormDto(Long id,String userFirstName,String userLastName,String email, String message, String status, String response ){
        this.id=id;
        this.userFirstName=userFirstName;
        this.userLastName=userLastName;
        this.email=email;
        this.message=message;
        this.status=status;
        this.response=response;
    }


    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id=id;
    }

    public String getUserFirstName(){
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName){
        this.userFirstName=userFirstName;
    }
    public String getUserLastName(){
        return userLastName;
    }

    public void setUserLastName(String userLastName){
        this.userLastName=userLastName;
    }
    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email=email;
    }

    public String getMessage(){
        return message;
    }
    public void setMessage(String message){
        this.message=message;
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

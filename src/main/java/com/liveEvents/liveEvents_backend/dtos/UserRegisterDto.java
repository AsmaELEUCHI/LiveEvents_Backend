package com.liveEvents.liveEvents_backend.dtos;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserRegisterDto {



    @NotBlank(message = "Le prénom de l'utilisateur est requis")
    @Size(min=1, max=50, message ="Le prénom de l'utilisateur doit contenir entre 1 et 50 caractères")
    private String userFirstName;

    @NotBlank(message = "Le nom de l'utilisateur est requis")
    @Size(min=1, max=50, message ="Le nom de l'utilisateur doit contenir entre 1 et 50 caractères")
    private String userLastName;

    @NotBlank(message = "L'email est requis")
    @Email(message = "L'email doit être valide")
    private String email;


    @NotBlank(message = "Le mot de passe est requis")
    @Size(min = 8, max=20, message = "Le mot de passe doit contenir au moins 8 caractères")
    @Pattern(regexp = "(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}",
            message = "Le mot de passe doit contenir au moins une majuscule, un chiffre et un caractère spécial")
    private String password;


    private String message;


    public UserRegisterDto(){}
    public UserRegisterDto( String userFirstName, String userLastName ){

        this.userFirstName=userFirstName;
        this.userLastName=userLastName;

    }

    public UserRegisterDto(String message) {
        this.message = message;
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
    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password=password;
    }

    public String getMessage(){
        return message;
    }
    public void setMessage(String message){
        this.message=message;
    }
}

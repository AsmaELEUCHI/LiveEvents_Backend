package com.liveEvents.liveEvents_backend.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserLoginDto {


    @NotBlank(message = "L'email est requis")
    @Email(message = "L'email doit être valide")
    private String email;


    @NotBlank(message = "Le mot de passe est requis")
    @Size(min = 8, max=20, message = "Le mot de passe doit contenir au moins 8 caractères")
    @Pattern(regexp = "(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}",
            message = "Le mot de passe doit contenir au moins une majuscule, un chiffre et un caractère spécial")
    private String password;

    private String token;
    private String message;

    public UserLoginDto(){}
    public UserLoginDto(String message) {
        this.message = message;
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
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    public String getMessage(){
        return message;
    }
    public void setMessage(String message){
        this.message=message;
    }

}

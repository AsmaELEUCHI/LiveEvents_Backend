package com.liveEvents.liveEvents_backend.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.Set;

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String userFirstName;


    private String userLastName;


    private String email;



    private String password;






    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="user_role",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="role_id")
    )



    private Set<Role> roles;

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
    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password=password;
    }


    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles){
        this.roles=roles;
    }
}

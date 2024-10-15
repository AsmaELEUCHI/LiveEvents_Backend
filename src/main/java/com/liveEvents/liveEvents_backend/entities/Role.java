package com.liveEvents.liveEvents_backend.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Set;

@Entity
@Table(name="roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String roleName;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id=id;
    }

    public String getRoleName(){
        return roleName;
    }

    public void setRoleName(String roleName){
        this.roleName=roleName;
    }
}

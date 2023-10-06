package com.blog.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Role {
    @Id
    private int id;

    @Enumerated(EnumType.STRING)
    private Role_Name role;
    
    public enum Role_Name{
    	NORMAL,ADMIN
    }
}


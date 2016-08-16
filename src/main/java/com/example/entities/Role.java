package com.example.entities;

import javax.persistence.*;

@Entity
@Table(name = "role")
public class Role {

    public Role() {}

    public Role(String role) {
        this.role = role;
    }

    @Id
    @GeneratedValue//(strategy=GenerationType.IDENTITY)
    @Column(name = "id_role")
    private int idRole;

    @Column(nullable = false, columnDefinition = "varchar(10) default 'ROLE_USER'")
    private String role;

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

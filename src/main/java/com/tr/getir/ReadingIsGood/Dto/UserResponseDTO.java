package com.tr.getir.ReadingIsGood.Dto;

import java.util.Date;

public class UserResponseDTO {

    private String name;
    private String surname;
    private Date creDate;
    private String id;
    private String email;


    public UserResponseDTO(String name, String surname, Date creDate, String id, String email) {
        this.name = name;
        this.surname = surname;
        this.creDate = creDate;
        this.id = id;
        this.email = email;
    }

    public UserResponseDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getCreDate() {
        return creDate;
    }

    public void setCreDate(Date creDate) {
        this.creDate = creDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

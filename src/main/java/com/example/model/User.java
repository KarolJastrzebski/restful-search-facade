package com.example.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document("users")
public class User {

    @MongoId
    private String id;
    private String user;
    private String workstation;

    public User() {
    }

    public User(String id, String user, String workstation) {
        this.id = id;
        this.user = user;
        this.workstation = workstation;
    }

    public String getId() {
        return id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getWorkstation() {
        return workstation;
    }

    public void setWorkstation(String workstation) {
        this.workstation = workstation;
    }
}

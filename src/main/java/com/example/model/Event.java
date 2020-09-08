package com.example.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document("events")
public class Event {

    @MongoId
    private String id;
    private String type;
    private long time;
    private String user;
    private String ip;

    public Event() {
    }

    public Event(String id, String type, long time, String user, String ip) {
        this.id = id;
        this.type = type;
        this.time = time;
        this.user = user;
        this.ip = ip;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}

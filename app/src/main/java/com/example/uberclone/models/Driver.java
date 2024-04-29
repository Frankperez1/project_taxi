package com.example.uberclone.models;

public class Driver {
    String id;
    String name;
    String email;
    String vehiculeBand;
    String vehiculePlate;

    public Driver(String id, String name, String email, String vehiculeBand, String vehiculePlate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.vehiculeBand = vehiculeBand;
        this.vehiculePlate = vehiculePlate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVehiculeBand() {
        return vehiculeBand;
    }

    public void setVehiculeBand(String vehiculeBand) {
        this.vehiculeBand = vehiculeBand;
    }

    public String getVehiculePlate() {
        return vehiculePlate;
    }

    public void setVehiculePlate(String vehiculePlate) {
        this.vehiculePlate = vehiculePlate;
    }
}

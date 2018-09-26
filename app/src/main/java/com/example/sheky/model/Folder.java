package com.example.sheky.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Folder {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private int position;
    private boolean markedDefaultPage;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isMarkedDefaultPage() {
        return markedDefaultPage;
    }

    public void setMarkedDefaultPage(boolean markedDefaultPage) {
        this.markedDefaultPage = markedDefaultPage;
    }
}

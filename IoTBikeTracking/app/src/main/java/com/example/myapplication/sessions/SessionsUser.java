package com.example.myapplication.sessions;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity()
public class SessionsUser {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "sessionTime")
    private String sessionTime;

    @ColumnInfo(name = "sessionDistance")
    private String sessionDistance;

    public SessionsUser(String sessionTime, String sessionDistance) {
        this.sessionTime = sessionTime;
        this.sessionDistance = sessionDistance;
    }

    public SessionsUser() {
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getSessionTime() {
        return sessionTime;
    }
    public void setSessionTime(String sessionTime) {
        this.sessionTime = sessionTime;
    }

    public String getSessionDistance() {
        return sessionDistance;
    }
    public void setSessionDistance(String sessionDistance) {
        this.sessionDistance = sessionDistance;
    }
}

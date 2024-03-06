package com.example.myapplication.sessions;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {SessionsUser.class}, version = 1, exportSchema = false)
public abstract class MyDatabase extends RoomDatabase{
    public abstract SessionsUserDAO sessionsUserDAO();


}


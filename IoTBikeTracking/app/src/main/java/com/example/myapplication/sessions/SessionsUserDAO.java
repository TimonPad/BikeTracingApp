package com.example.myapplication.sessions;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SessionsUserDAO {
    @Query("SELECT * FROM SessionsUser")
    List<SessionsUser> getAllSessionsUser();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSessionUser(SessionsUser sessionsUser);


}

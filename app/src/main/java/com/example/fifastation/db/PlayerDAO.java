package com.example.fifastation.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PlayerDAO {

    @Insert
    void insert(Player... players);

    @Update
    void update(Player... players);

    @Delete
    void delete(Player... players);

    @Query("SELECT * FROM players ORDER BY rating")
    LiveData<List<Player>> getAllPlayers();

    @Query("SELECT * FROM players WHERE ID = :ID")
    Player getPlayerById(int ID);

    @Query("SELECT * FROM players WHERE name = :name")
    Player getPlayerByName(String name);

    @Query("SELECT * FROM players WHERE club_name IN (:clubName)")
    List<Player> getClub(List<String> clubName);

    @Query("SELECT * FROM players WHERE nationality IN (:nation)")
    List<Player> getNation(List<String> nation);
}

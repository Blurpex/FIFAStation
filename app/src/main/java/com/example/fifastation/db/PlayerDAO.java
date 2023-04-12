package com.example.fifastation.db;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PlayerDAO {

    @Query("SELECT * FROM players ORDER BY rating")
    List<Player> getAllPlayers();

    @Query("SELECT * FROM players WHERE short_name LIKE '%' || :playerName || '%' OR long_name LIKE '%' || :playerName || '%'")
    List<Player> getPlayerByName(String playerName);

    @Query("SELECT * FROM players WHERE id = :id")
    List<Player> getPlayerById(int id);

    @Query("SELECT * FROM players ORDER BY rating DESC LIMIT 10")
    List<Player> getTopTenRatedPlayers();

    @Query("SELECT * FROM players WHERE club_name LIKE '%' || :clubName || '%'")
    List<Player> getPlayerByClub(String clubName);

    @Query("SELECT * FROM players WHERE positions LIKE '%' || :position")
    List<Player> getPlayerByPosition(String position);
}

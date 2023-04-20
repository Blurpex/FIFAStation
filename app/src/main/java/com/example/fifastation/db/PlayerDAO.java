package com.example.fifastation.db;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PlayerDAO {

    @Query("SELECT * FROM players ORDER BY rating")
    List<Player> getAllPlayers();

    @Query("SELECT * FROM players GROUP BY club_name")
    List<Player> getAllClubs();

    @Query("SELECT * FROM players GROUP BY league_name")
    List<Player> getAllLeagues();

    @Query("SELECT * FROM players GROUP BY nationality")
    List<Player> getAllNations();

    @Query("SELECT * FROM players WHERE long_name LIKE '%' || :playerName || '%' OR long_name LIKE '%' || :playerName || '%'")
    List<Player> getPlayerByName(String playerName);

    @Query("SELECT * FROM players WHERE id = :id")
    List<Player> getPlayerById(int id);

    @Query("SELECT * FROM players WHERE rating > :min AND rating < :max")
    List<Player> getPlayerByRating(int min, int max);

    @Query("SELECT * FROM players ORDER BY rating DESC LIMIT 10")
    List<Player> getTopTenRatedPlayers();

    @Query("SELECT * FROM players WHERE club_name LIKE '%' || :clubName || '%'")
    List<Player> getPlayerByClub(String clubName);

    @Query("SELECT * FROM players WHERE positions LIKE '%' || :position")
    List<Player> getPlayerByPosition(String position);

    @Query("SELECT * FROM players GROUP BY club_name ORDER BY rating DESC LIMIT 10")
    List<Player> getPopularClubs();

    @Query("SELECT * FROM players " +
            "WHERE (long_name LIKE '%' || :playerName || '%' OR :playerName IS NULL)" +
            "AND (rating > :min OR :min IS NULL)" + "" +
            "AND (rating < :max OR :max IS NULL)" +
            "AND (club_name LIKE '%' || :club || '%' OR :club IS NULL)" +
            "AND (league_name LIKE '%' || :league || '%' OR :league IS NULL)" +
            "AND (nationality LIKE '%' || :nation || '%' OR :nation IS NULL)")
    List<Player> playerQuery(String playerName, float min, float max, String club, String league, String nation);

}
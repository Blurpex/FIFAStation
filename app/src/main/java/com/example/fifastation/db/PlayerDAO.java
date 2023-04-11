package com.example.fifastation.db;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PlayerDAO {

    @Query("SELECT * FROM players ORDER BY overall")
    List<Player> getAllPlayers();

    @Query("SELECT * FROM players WHERE id = :id")
    Player getPlayerById(int id);
}

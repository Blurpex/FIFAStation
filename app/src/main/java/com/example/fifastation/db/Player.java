package com.example.fifastation.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "players")
public class Player {

    public Player(int id, String short_name, String player_positions, int overall, String player_face_url) {
        this.id = id;
        this.short_name = short_name;
        this.player_positions = player_positions;
        this.overall = overall;
        this.player_face_url = player_face_url;
    }

    @PrimaryKey
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "short_name")
    public String short_name;

    @ColumnInfo(name = "player_positions")
    public String player_positions;

    @ColumnInfo(name = "overall")
    public int overall;

    @ColumnInfo(name = "player_face_url")
    public String player_face_url;
}

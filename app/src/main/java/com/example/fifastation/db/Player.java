package com.example.fifastation.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "players")
public class Player {

    public Player(int sofifa_id, String short_name, String long_name, String player_positions,
                  int overall, int potential, int value, int age, String dob,
                  int height, int weight, String club_name, String league_name,
                  String nationality, String player_face_url, String club_logo_url, String nation_logo_url) {
        this.sofifa_id = sofifa_id;
        this.short_name = short_name;
        this.long_name = long_name;
        this.player_positions = player_positions;
        this.overall = overall;
        this.potential = potential;
        this.value = value;
        this.age = age;
        this.dob = dob;
        this.height = height;
        this.weight = weight;
        this.club_name = club_name;
        this.league_name = league_name;
        this.nationality = nationality;
        this.player_face_url = player_face_url;
        this.club_logo_url = club_logo_url;
        this.nation_logo_url = nation_logo_url;
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    public int sofifa_id;

    @ColumnInfo(name = "short_name")
    public String short_name;

    @ColumnInfo(name = "name")
    public String long_name;

    @ColumnInfo(name = "positions")
    public String player_positions;

    @ColumnInfo(name = "rating")
    public int overall;

    @ColumnInfo(name = "potential")
    public int potential;

    @ColumnInfo(name = "value")
    public int value;

    @ColumnInfo(name = "age")
    public int age;

    @ColumnInfo(name = "dob")
    public String dob;

    @ColumnInfo(name = "height")
    public int height;

    @ColumnInfo(name = "weight")
    public int weight;

    @ColumnInfo(name = "club_name")
    public String club_name;

    @ColumnInfo(name = "league_name")
    public String league_name;

    @ColumnInfo(name = "nationality")
    public String nationality;

    @ColumnInfo(name = "player_photo")
    public String player_face_url;

    @ColumnInfo(name = "club_logo")
    public String club_logo_url;

    @ColumnInfo(name = "nation_flag")
    public String nation_logo_url;
}

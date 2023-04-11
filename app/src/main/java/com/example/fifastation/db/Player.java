package com.example.fifastation.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "players")
public class Player {

    public Player(int id, String short_name, String long_name, String positions, int rating,
                  int potential, int value, int age, String dob, int height, int weight,
                  String club_name, String league_name, int jersey_number, String club_joined,
                  String nationality, String preferred_foot, int weak_foot, int skill_moves,
                  int pace, int shooting, int passing, int dribbling, int defending, int physical,
                  int gk_diving, int gk_handling, int gk_kicking, int gk_positioning, int gk_reflexes,
                  int gk_speed, String player_face_url, String club_logo_url, String nation_flag_url

    ) {
        this.id = id;
        this.short_name = short_name;
        this.long_name = long_name;
        this.positions = positions;
        this.rating = rating;
        this.potential = potential;
        this.value = value;
        this.age = age;
        this.dob = dob;
        this.height = height;
        this.weight = weight;
        this.club_name = club_name;
        this.league_name = league_name;
        this.jersey_number = jersey_number;
        this.club_joined = club_joined;
        this.nationality = nationality;
        this.preferred_foot = preferred_foot;
        this.weak_foot = weak_foot;
        this.skill_moves = skill_moves;
        this.pace = pace;
        this.shooting = shooting;
        this.passing = passing;
        this.dribbling = dribbling;
        this.defending = defending;
        this.physical = physical;
        this.gk_diving = gk_diving;
        this.gk_handling = gk_handling;
        this.gk_kicking = gk_kicking;
        this.gk_positioning = gk_positioning;
        this.gk_reflexes = gk_reflexes;
        this.gk_speed = gk_speed;
        this.player_face_url = player_face_url;
        this.club_logo_url = club_logo_url;
        this.nation_flag_url = nation_flag_url;
    }

    @PrimaryKey
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "short_name")
    public String short_name;

    @ColumnInfo(name = "long_name")
    public String long_name;

    @ColumnInfo(name = "positions")
    public String positions;

    @ColumnInfo(name = "rating")
    public int rating;

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

    @ColumnInfo(name = "jersey_number")
    public int jersey_number;

    @ColumnInfo(name = "club_joined")
    public String club_joined;

    @ColumnInfo(name = "nationality")
    public String nationality;

    @ColumnInfo(name = "preferred_foot")
    public String preferred_foot;

    @ColumnInfo(name = "weak_foot")
    public int weak_foot;

    @ColumnInfo(name = "skill_moves")
    public int skill_moves;

    @ColumnInfo(name = "pace")
    public int pace;

    @ColumnInfo(name = "shooting")
    public int shooting;

    @ColumnInfo(name = "passing")
    public int passing;

    @ColumnInfo(name = "dribbling")
    public int dribbling;

    @ColumnInfo(name = "defending")
    public int defending;

    @ColumnInfo(name = "physical")
    public int physical;

    @ColumnInfo(name = "gk_diving")
    public int gk_diving;

    @ColumnInfo(name = "gk_handling")
    public int gk_handling;

    @ColumnInfo(name = "gk_kicking")
    public int gk_kicking;

    @ColumnInfo(name = "gk_positioning")
    public int gk_positioning;

    @ColumnInfo(name = "gk_reflexes")
    public int gk_reflexes;

    @ColumnInfo(name = "gk_speed")
    public int gk_speed;

    @ColumnInfo(name = "player_face_url")
    public String player_face_url;

    @ColumnInfo(name = "club_logo_url")
    public String club_logo_url;

    @ColumnInfo(name = "nation_flag_url")
    public String nation_flag_url;
}

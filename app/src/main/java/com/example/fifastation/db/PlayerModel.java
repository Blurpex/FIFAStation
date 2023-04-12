package com.example.fifastation.db;

public class PlayerModel {
    String player;
    String club;
    String league;
    String nationality;
    String positions;
    int rating;

    public PlayerModel(String player, String club, String league, String nationality, String positions, int rating) {
        this.player = player;
        this.club = club;
        this.league = league;
        this.nationality = nationality;
        this.positions = positions;
        this.rating = rating;
    }

    public String getPlayer() { return player; }
    public String getClub() { return club; }
    public String getLeague() { return league; }
    public String getNationality() { return nationality; }
    public String getPositions() { return positions; }
    public int getRating() { return rating; }
}

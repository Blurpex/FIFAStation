package com.example.fifastation.db;

import android.content.Context;
import android.os.Looper;
import android.os.Message;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.os.Handler;

import java.util.List;

@Database(entities = { Player.class }, version = 1, exportSchema = false)
public abstract class PlayerDatabase extends RoomDatabase {

    // interface for listener
    @FunctionalInterface
    public interface PlayerListener {
        void onPlayerReturned(List<Player> player);
    }

    // instances for data access object and database
    public abstract PlayerDAO playerDAO();
    private static PlayerDatabase INSTANCE;

    // returns instance of database class using the singleton pattern
    public static PlayerDatabase getInstance(final Context context) {
        if(INSTANCE == null) {
            synchronized (PlayerDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                                    PlayerDatabase.class,
                                    "PlayerDatabase"
                            )
                            .createFromAsset("database/fifadb.sqlite")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    // returns all players in the database
    public static void getAllPlayers(PlayerListener listener) {
        Handler handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                listener.onPlayerReturned((List<Player>) msg.obj);
            }
        };
        (new Thread(() -> {
            Message msg = handler.obtainMessage();
            msg.obj =  INSTANCE.playerDAO().getAllPlayers();
            handler.sendMessage(msg);
        })).start();
    }

    // returns players with similar name as the given query
    public static void getPlayersByName(String playerName, PlayerListener listener) {
        Handler handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                listener.onPlayerReturned((List<Player>) msg.obj);
            }
        };
        (new Thread(() -> {
            Message msg = handler.obtainMessage();
            msg.obj =  INSTANCE.playerDAO().getPlayerByName(playerName);
            handler.sendMessage(msg);
        })).start();
    }

    // returns one player with the corresponding ID
    public static void getPlayerById(int id, PlayerListener listener) {
        Handler handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                listener.onPlayerReturned((List<Player>) msg.obj);
            }
        };
        (new Thread(() -> {
            Message msg = handler.obtainMessage();
            msg.obj = INSTANCE.playerDAO().getPlayerById(id);
            handler.sendMessage(msg);
        })).start();
    }

    // returns 10 players with the highest overall rating
    public static void getTopTenRatedPlayers(PlayerListener listener) {
        Handler handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                listener.onPlayerReturned((List<Player>) msg.obj);
            }
        };
        (new Thread(() -> {
            Message msg = handler.obtainMessage();
            msg.obj = INSTANCE.playerDAO().getTopTenRatedPlayers();
            handler.sendMessage(msg);
        })).start();
    }

    // returns players who play for a given club
    public static void getPlayerByClub(String clubName, PlayerListener listener) {
        Handler handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                listener.onPlayerReturned((List<Player>) msg.obj);
            }
        };
        (new Thread(() -> {
            Message msg = handler.obtainMessage();
            msg.obj = INSTANCE.playerDAO().getPlayerByClub(clubName);
            handler.sendMessage(msg);
        })).start();
    }

    // returns players who play in a given position
    public static void getPlayerByPosition(String position, PlayerListener listener) {
        Handler handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                listener.onPlayerReturned((List<Player>) msg.obj);
            }
        };
        (new Thread(() -> {
            Message msg = handler.obtainMessage();
            msg.obj = INSTANCE.playerDAO().getPlayerByPosition(position);
            handler.sendMessage(msg);
        })).start();
    }

    // returns a list of 10 popular clubs
    public static void getPopularClubs(PlayerListener listener) {
        Handler handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                listener.onPlayerReturned((List<Player>) msg.obj);
            }
        };
        (new Thread(() -> {
            Message msg = handler.obtainMessage();
            msg.obj = INSTANCE.playerDAO().getPopularClubs();
            handler.sendMessage(msg);
        })).start();
    }

}
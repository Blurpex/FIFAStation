package com.example.fifastation.db;

import android.content.Context;
import android.os.Looper;
import android.os.Message;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.os.Handler;

@Database(entities = { Player.class }, version = 1, exportSchema = false)
public abstract class PlayerDatabase extends RoomDatabase {

    @FunctionalInterface
    public interface PlayerListener {
        void onPlayerReturned(Player player);
    }

    // returns data access object
    public abstract PlayerDAO playerDAO();

    // create an instance of database class
    private static PlayerDatabase INSTANCE;

    // returns instance of database class
    public static PlayerDatabase getDatabase(final Context context) {
        if(INSTANCE == null) {
            synchronized (PlayerDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), PlayerDatabase.class, "PlayerDatabase").build();
                }
            }
        }
        return INSTANCE;
    }

    public static void getAllPlayers(PlayerListener listener) {
        Handler handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                listener.onPlayerReturned((Player) msg.obj);
            }
        };
        (new Thread(() -> {
            Message msg = handler.obtainMessage();
            msg.obj =  INSTANCE.playerDAO().getAllPlayers();
            handler.sendMessage(msg);
        })).start();
    }

}

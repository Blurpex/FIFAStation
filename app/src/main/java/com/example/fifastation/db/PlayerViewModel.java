package com.example.fifastation.db;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

public class PlayerViewModel extends AndroidViewModel {

    private Player player;

    public PlayerViewModel(Application application) {
        super(application);
    }

    public Player getPlayerById(int id) {
        return PlayerDatabase.getInstance(getApplication()).playerDAO().getPlayerById(id);
    }
}

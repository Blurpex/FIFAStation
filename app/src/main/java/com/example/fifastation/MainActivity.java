package com.example.fifastation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.os.Bundle;
import android.util.Log;

import com.example.fifastation.db.Player;
import com.example.fifastation.db.PlayerDatabase;
import com.google.android.material.appbar.MaterialToolbar;

public class MainActivity extends AppCompatActivity {

    private Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // change the title of the toolbar title to 'Home'
        CoordinatorLayout outerToolbar = findViewById(R.id.home_toolbar);
        MaterialToolbar toolbar = outerToolbar.findViewById(R.id.topAppBar);
        toolbar.setTitle("Home");

        // open drawer
        toolbar.setNavigationOnClickListener(view -> {
            Log.d("MainActivity", "navigation drawer");
        });

        // handle if the user clicks on the menu items
        toolbar.setOnMenuItemClickListener( menuItem -> {
            switch(menuItem.getItemId()) {
                case R.id.search:
                    Log.d("MainActivity", "search: " + R.id.search);
                    return true;
                case R.id.settings:
                    Log.d("MainActivity", "settings: "  + R.id.settings);
                    return true;
                default: return false;
            }
        });

        PlayerDatabase.getInstance(this);
        PlayerDatabase.getPlayerById(41, player -> this.player = player);
        Log.d("debug_player", this.player.short_name);
    }

}

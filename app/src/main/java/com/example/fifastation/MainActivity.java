package com.example.fifastation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.os.Bundle;
import android.util.Log;

import com.example.fifastation.db.Player;
import com.example.fifastation.db.PlayerDatabase;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Player player;
    List<Player> playersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // change the itle of the toolbar title to 'Home'
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
//        PlayerDatabase.getPlayerById(20801, player -> {
//            this.player = player;
//            Log.d("playerDebug", player.short_name);
//        });

        PlayerDatabase.getTopTenRatedPlayers(players -> {
            playersList = players;
            playersList.forEach(elem -> Log.d("playerDebug", elem.long_name + " --- " + elem.club_name + " --- " + elem.rating));
        });
    }

}

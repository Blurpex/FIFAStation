package com.example.fifastation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.fifastation.db.PlayerDatabase;
import com.google.android.material.appbar.MaterialToolbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // change the title of the toolbar title to 'Home'
        CoordinatorLayout outerToolbar = findViewById(R.id.home_toolbar);
        MaterialToolbar toolbar = outerToolbar.findViewById(R.id.topAppBar);
        toolbar.setTitle("Home");

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView = findViewById(R.id.trending_player_list);
        PlayerDatabase.getInstance(this);
        PlayerDatabase.getTopTenRatedPlayers(players -> {
            PlayerAdapter adapter = new PlayerAdapter(this, players);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(layoutManager);
        });

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
    }

}

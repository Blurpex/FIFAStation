package com.example.fifastation;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;

import com.example.fifastation.db.PlayerDatabase;
import com.google.android.material.appbar.MaterialToolbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //dialog
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();

        boolean isFirstRun = sharedPreferences.getBoolean("IS_FIRST_RUN", true);

        if(isFirstRun == true) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);

            alertDialog.setTitle("Alert");
            alertDialog.setMessage("The data used on this app is from website and not intended to use copyright.");
            alertDialog.setIcon(R.drawable.ic_alert);

            alertDialog.setPositiveButton("Acknowledge", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });

            alertDialog.show();

            // Update
            editor.putBoolean("IS_FIRST_RUN", false);
            editor.commit();
        }



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

    @Override
    protected void onDestroy() {
        super.onDestroy();

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Update
        editor.putBoolean("IS_FIRST_RUN", true);
        editor.commit();
    }

}

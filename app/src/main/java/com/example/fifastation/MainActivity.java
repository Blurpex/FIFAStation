package com.example.fifastation;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.fifastation.db.PlayerDatabase;
import com.google.android.material.appbar.MaterialToolbar;

public class MainActivity extends AppCompatActivity {

    private MaterialToolbar toolbar;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // top app bar
        toolbar = findViewById(R.id.topAppBar);
        toolbar.setTitle("Home");
        setSupportActionBar(toolbar);

        // drawer
        this.drawer = findViewById(R.id.drawer);
        toolbar.setNavigationOnClickListener(view -> drawer.open());
        toolbar.setOnMenuItemClickListener( menuItem -> {
            switch(menuItem.getItemId()) {
                case R.id.players:
                    return true;
                case R.id.settings:
                    Log.d("MainActivity", "settings: "  + R.id.settings);
                    return true;
                default: return false;
            }
        });

        // content for the activity
        displayCopyrightDialog();
        displayTrendingPlayers();
    }

    private void displayTrendingPlayers() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView = findViewById(R.id.trending_player_list);
        PlayerDatabase.getInstance(this);
        PlayerDatabase.getTopTenRatedPlayers(players -> {
            PlayerAdapter adapter = new PlayerAdapter(this, players);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(layoutManager);
        });
    }

    private void displayCopyrightDialog() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();

        boolean isFirstRun = sharedPreferences.getBoolean("IS_FIRST_RUN", true);

        if(isFirstRun) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);

            alertDialog.setTitle("Alert");
            alertDialog.setMessage("The data used on this app is from website and not intended to use copyright.");
            alertDialog.setIcon(R.drawable.ic_alert);
            alertDialog.setPositiveButton(
                    "Acknowledge",
                    (dialog, which) -> Toast.makeText(MainActivity.this, "Thanks for your acknowledge!!!", Toast.LENGTH_LONG).show()
            );

            alertDialog.show();

            // Update
            editor.putBoolean("IS_FIRST_RUN", false);
            editor.commit();
        }
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

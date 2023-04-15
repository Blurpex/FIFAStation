package com.example.fifastation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.fifastation.db.PlayerDatabase;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener {

    private MaterialToolbar toolbar;
    private DrawerLayout drawer;
    private NavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // top app bar
        this.toolbar = findViewById(R.id.topAppBar);
        this.toolbar.setTitle("Home");
        setSupportActionBar(toolbar);

        // navigation
        this.navigation = findViewById(R.id.navigation);
        this.navigation.setNavigationItemSelectedListener(this);

        // drawer
        this.drawer = findViewById(R.id.drawer);
        this.toolbar.setNavigationOnClickListener(view -> drawer.open());

        // content for the activity
        displayCopyrightDialog();
        displayTrendingPlayers();
    }

    // handles navigation item when selected
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == R.id.players) {
            test("players: " + R.id.players);
            Intent intent = new Intent(this, PlayerActivity.class);
            startActivity(intent);
        }
        else if(itemId == R.id.favorite) {
            test("favorite: " + R.id.favorite);
        }
        else if(itemId == R.id.settings) {
            test("settings: "  + R.id.settings);
        }
        this.drawer.close();
        return true;
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
            alertDialog.setPositiveButton("Acknowledge",
                    (dialog, which) -> Toast.makeText(MainActivity.this, "Thanks for your acknowledge!!!", Toast.LENGTH_LONG).show());
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

    private void test(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
}

package com.example.fifastation;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // theme
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String theme = sharedPreferences.getString("theme", "");
        switch(theme) {
            case "Blue":
                setTheme(R.style.Theme_FIFAStation_Blue);
                break;
            case "Red":
                setTheme(R.style.Theme_FIFAStation_Red);
                break;
        }

        // dark mode
        AppCompatDelegate.setDefaultNightMode(
            sharedPreferences.getBoolean("darkMode", false)
                ? AppCompatDelegate.MODE_NIGHT_YES
                : AppCompatDelegate.MODE_NIGHT_NO
        );

        // inflate layout
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ack dialog
        displayCopyrightDialog();

        // setup toolbar
        MaterialToolbar toolbar = findViewById(R.id.topAppBar);
        setSupportActionBar(toolbar);

        // setup navigation
        Set<Integer> topLevelDestinations = new HashSet<>();
        topLevelDestinations.add(R.id.homeItem);
        topLevelDestinations.add(R.id.playersItem);
        topLevelDestinations.add(R.id.clubsItem);
        topLevelDestinations.add(R.id.favoriteItem);
        topLevelDestinations.add(R.id.settingsItem);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        NavController navController = navHostFragment.getNavController();
        DrawerLayout drawer = findViewById(R.id.drawer);
        appBarConfiguration = new AppBarConfiguration.Builder(topLevelDestinations).setDrawerLayout(drawer).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationView navigation = findViewById(R.id.navigation);
        NavigationUI.setupWithNavController(navigation, navController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
    }

    // ask user to ack
    private void displayCopyrightDialog() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // show the dialog only once
        if(!sharedPreferences.getBoolean("dataAck", false)) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
            alertDialog.setTitle("Terms & Conditions");
            alertDialog.setMessage("This app is not for commercial use and not intended to use copyright.");
            alertDialog.setIcon(R.drawable.ic_alert);
            alertDialog.setPositiveButton("Acknowledge", (dialog, which) ->
                    Toast.makeText(MainActivity.this, "Thank you for acknowledging!", Toast.LENGTH_LONG).show());
            alertDialog.show();

            // save it to shared preferences
            editor.putBoolean("dataAck", true);
            editor.apply();
        }
    }
}

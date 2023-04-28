package com.example.fifastation;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

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

        // inflate layout
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // display dialog
        displayCopyrightDialog();

        // top app bar
        MaterialToolbar toolbar = findViewById(R.id.topAppBar);
        setSupportActionBar(toolbar);

        // drawer
        DrawerLayout drawer = findViewById(R.id.drawer);
        toolbar.setNavigationOnClickListener(view -> drawer.open());

        // navigation
        NavigationView navigation = findViewById(R.id.navigation);
        NavController controller = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navigation, controller);
        controller.addOnDestinationChangedListener((navController, navDestination, bundle) -> {
            toolbar.setTitle(navDestination.getLabel());
        });
    }

    //ask user to ack
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

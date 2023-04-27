package com.example.fifastation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

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
}

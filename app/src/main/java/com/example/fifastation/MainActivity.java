package com.example.fifastation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
        NavController navController1 = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navigation, navController1);
        navController1.addOnDestinationChangedListener((navController, navDestination, bundle) -> {
            toolbar.setTitle(navDestination.getLabel());
        });
    }
}

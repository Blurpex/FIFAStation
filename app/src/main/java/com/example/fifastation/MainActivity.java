package com.example.fifastation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // top app bar
        MaterialToolbar toolbar = findViewById(R.id.topAppBar);
        NavigationView navigation = findViewById(R.id.navigation);
        setTheme(toolbar,navigation);
        
        setSupportActionBar(toolbar);

        // drawer
        DrawerLayout drawer = findViewById(R.id.drawer);
        toolbar.setNavigationOnClickListener(view -> drawer.open());

        // navigation

        NavController controller = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navigation, controller);
        controller.addOnDestinationChangedListener((navController, navDestination, bundle) -> {
            toolbar.setTitle(navDestination.getLabel());
        });
        setTheme(toolbar,navigation);
    }
    public void setTheme(MaterialToolbar toolbar, NavigationView navigation){
        // setting theme
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String color = prefs.getString("Theme", "");
        Log.d("color", color);
        if(color.equals("red")){
            toolbar.setBackgroundColor(getResources().getColor(R.color.red));
            navigation.setBackgroundColor(getResources().getColor(R.color.lightRed));
        }
        else if(color.equals("blue")){
            toolbar.setBackgroundColor(getResources().getColor(R.color.blue));
            navigation.setBackgroundColor(getResources().getColor(R.color.lightBlue));
        }
        else if(color.equals("yellow")){
            toolbar.setBackgroundColor(getResources().getColor(R.color.yellow));
            navigation.setBackgroundColor(getResources().getColor(R.color.lightYellow));
        }
        else if(color.equals("purple")){
            toolbar.setBackgroundColor(getResources().getColor(R.color.purple));
            navigation.setBackgroundColor(getResources().getColor(R.color.lightPurple));
        }
        else if(color.equals("black")){
            toolbar.setBackgroundColor(getResources().getColor(R.color.black));
            navigation.setBackgroundColor(getResources().getColor(R.color.gray));
        }
        else if(color.equals("default")){
            toolbar.setBackgroundColor(getResources().getColor(R.color.primary));
            navigation.setBackgroundColor(getResources().getColor(R.color.secondary));
        }
    }



}

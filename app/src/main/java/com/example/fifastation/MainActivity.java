package com.example.fifastation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;

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
    }

    // handles navigation item when drawer item is selected
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == R.id.players) {
//            Intent intent = new Intent(this, PlayerActivity.class);
//            startActivity(intent);
        }
        else if(itemId == R.id.favorite) {
        }
        else if(itemId == R.id.settings) {
        }
        this.drawer.close();
        return true;
    }
}

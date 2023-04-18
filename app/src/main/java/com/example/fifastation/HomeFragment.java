package com.example.fifastation;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fifastation.db.PlayerDatabase;

public class HomeFragment extends Fragment {

    private Context context;
    private RecyclerView trendingPlayersView;
    private RecyclerView popularClubsView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // get the layout
        View view =  inflater.inflate(R.layout.fragment_home, container, false);

        // trending players
        this.context = container.getContext();
        this.trendingPlayersView = view.findViewById(R.id.trending_player_list);
        displayTrendingPlayers();

        // popular clubs
        this.popularClubsView = view.findViewById(R.id.popular_clubs_list);
        displayPopularClubs();

        // inflate the layout
        return view;
    }

    private void displayTrendingPlayers() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        PlayerDatabase.getInstance(context);
        PlayerDatabase.getTopTenRatedPlayers(players -> {
            PlayerAdapter adapter = new PlayerAdapter(context, players);
            trendingPlayersView.setAdapter(adapter);
            trendingPlayersView.setLayoutManager(layoutManager);
        });
    }

    private void displayPopularClubs() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        PlayerDatabase.getInstance(context);
        PlayerDatabase.getPopularClubs(clubs -> {
            ClubAdapter adapter = new ClubAdapter(context, clubs);
            popularClubsView.setAdapter(adapter);
            popularClubsView.setLayoutManager(layoutManager);
        });
    }

    /*
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
    */
}
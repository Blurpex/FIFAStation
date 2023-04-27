package com.example.fifastation;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fifastation.db.Player;
import com.example.fifastation.db.PlayerDatabase;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

public class HomeFragment extends Fragment {

    private Context context;
    private RecyclerView trendingPlayersView;
    private RecyclerView popularClubsView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // get the layout
        View view =  inflater.inflate(R.layout.fragment_home, container, false);

        // setting theme
        FrameLayout layout = view.findViewById(R.id.frameLayout);
        Context context = container.getContext();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String color = prefs.getString("Theme", "");
        Log.d("color", color);
        if(color.equals("red")){
            layout.setBackgroundColor(getResources().getColor(R.color.lightRed));
        }
        else if(color.equals("blue")){
            layout.setBackgroundColor(getResources().getColor(R.color.lightBlue));
        }
        else if(color.equals("yellow")){
            layout.setBackgroundColor(getResources().getColor(R.color.lightYellow));
        }
        else if(color.equals("purple")){
            layout.setBackgroundColor(getResources().getColor(R.color.lightPurple));
        }
        else if(color.equals("black")){
            layout.setBackgroundColor(getResources().getColor(R.color.gray));
        }
        else if(color.equals("default")){
            layout.setBackgroundColor(getResources().getColor(R.color.secondary));
        }


        // trending players
        this.context = container.getContext();
        this.trendingPlayersView = view.findViewById(R.id.trending_player_list);
        displayTrendingPlayers();

        // popular clubs
        this.popularClubsView = view.findViewById(R.id.popular_clubs_list);
        displayPopularClubs();

        // discover player
        PlayerDatabase.getInstance(context);
        PlayerDatabase.getPlayerById(41, tempPlayer -> {
            Player player = tempPlayer.get(0);

            ImageView photo = view.findViewById(R.id.player_photo_discover);
            Picasso.get().load(player.player_face_url).placeholder(R.drawable.player_placeholder).into(photo);
            ImageView nation = view.findViewById(R.id.player_nation_discover);
            Picasso.get().load(player.nation_flag_url).placeholder(R.drawable.player_placeholder).into(nation);
            ImageView clubLogo = view.findViewById(R.id.player_club_discover);
            Picasso.get().load(player.club_logo_url).placeholder(R.drawable.player_placeholder).into(clubLogo);
            ((TextView) view.findViewById(R.id.player_name_discover)).setText(player.long_name);
        });

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
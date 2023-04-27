package com.example.fifastation;

import android.content.Context;
import android.content.SearchRecentSuggestionsProvider;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fifastation.db.Player;
import com.example.fifastation.db.PlayerDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class FavoriteFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // get the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);

        // get an instance of database
        Context context = container.getContext();
        PlayerDatabase.getInstance(context);

        // get favorite players from shared preferences
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String tempFav = sharedPreferences.getString("favoritePlayers", "");
        String[] favoritePlayers = new String[0];
        if(!tempFav.isEmpty())
            favoritePlayers = tempFav.split("-");

        // display favorite players
        List<Player> favList = new ArrayList<>();
        for(String favPlayer: favoritePlayers) {
            PlayerDatabase.getPlayerById(Integer.parseInt(favPlayer), player -> {
                favList.add(player.get(0));

                LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
                PlayerAdapter adapter = new PlayerAdapter(context, favList);
                RecyclerView favoritePlayersView = view.findViewById(R.id.favorite_Player_View);
                favoritePlayersView.setAdapter(adapter);
                favoritePlayersView.setLayoutManager(layoutManager);
            });
        }

        // inflate layout
        return view;
    }
}
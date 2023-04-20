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

public class PlayerQueryFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // get the layout
        View view = inflater.inflate(R.layout.fragment_player_query, container, false);

        // get instance of database
        Context context = container.getContext();
        PlayerDatabase.getInstance(context);

        // get passed values
        Bundle bundle = getArguments();
        String playerName = bundle.getString("playerName");
        float min = bundle.getFloat("min");
        float max = bundle.getFloat("max");
        String club = bundle.getString("club");
        String league = bundle.getString("league");
        String nation = bundle.getString("nation");

        // populate the recycler view
        RecyclerView playersQueryView = view.findViewById(R.id.player_query);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        PlayerDatabase.getInstance(context);
        PlayerDatabase.playerQuery(playerName, min, max, club, league, nation, (PlayerDatabase.PlayerListener) players -> {
            PlayerAdapter adapter = new PlayerAdapter(context, players);
            playersQueryView.setAdapter(adapter);
            playersQueryView.setLayoutManager(layoutManager);
        });

        // Inflate the layout for this fragment
        return view;
    }
}
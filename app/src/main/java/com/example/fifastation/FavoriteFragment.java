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

    private Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // get the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        this.context = container.getContext();
        PlayerDatabase.getInstance(context);
        RecyclerView favoritePlayersView = view.findViewById(R.id.favorite_Player_View);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        String playerIds = sharedPref.getString("playerIds", "");
        Log.d("PlayerId: ", playerIds);
        List<String[]> playerId = new ArrayList<>();
        playerId.add(playerIds.split("-"));
        String[] playerIdList = playerId.get(0);
        List<Integer> list = new ArrayList<>();
        for(String player : playerIdList ){
            list.add(Integer.parseInt(player));
        }
        List<Player> playerList = new ArrayList<>();
        list.forEach(x-> PlayerDatabase.getPlayerById(x, new PlayerDatabase.PlayerListener() {
            @Override
            public void onPlayerReturned(List<Player> player) {
                Player tempPlayer = player.get(0);
                playerList.add(tempPlayer);
                Log.d("PlayerID", tempPlayer.long_name);
                LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
                PlayerAdapter adapter = new PlayerAdapter(context, playerList);
                favoritePlayersView.setAdapter(adapter);
                favoritePlayersView.setLayoutManager(layoutManager);
            }
        }));

        // inflate layout
        return view;
    }
}
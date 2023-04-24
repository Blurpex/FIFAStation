package com.example.fifastation;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fifastation.db.Player;
import com.example.fifastation.db.PlayerDatabase;
import com.squareup.picasso.Picasso;

public class PlayerDetailFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // get the layout
        View view = inflater.inflate(R.layout.fragment_player_detail, container, false);

        // change the title of fragment

        // get passed values
        Context context = container.getContext();
        PlayerDatabase.getInstance(context);
        int playerId = getArguments().getInt("playerId");

        // bind the values
        PlayerDatabase.getPlayerById(playerId, tempPlayer -> {
            Player player = tempPlayer.get(0);
            ImageView photo = view.findViewById(R.id.player_photo_detail);

            // player information
            Picasso.get().load(player.player_face_url).placeholder(R.drawable.player_placeholder).into(photo);
            ((TextView) view.findViewById(R.id.player_name_detail)).setText(player.short_name);
            ((TextView) view.findViewById(R.id.club_detail)).setText(player.club_name);
            ((TextView) view.findViewById(R.id.league_detail)).setText(player.league_name);
            ((TextView) view.findViewById(R.id.nation_detail)).setText(player.nationality);
            ((TextView) view.findViewById(R.id.rating_detail)).setText(String.valueOf(player.rating));

            // player stats
            ((TextView) view.findViewById(R.id.pace_stat)).setText(String.valueOf(player.pace));
            ((TextView) view.findViewById(R.id.shooting_stat)).setText(String.valueOf(player.shooting));
            ((TextView) view.findViewById(R.id.passing_stat)).setText(String.valueOf(player.passing));
            ((TextView) view.findViewById(R.id.dribbling_stat)).setText(String.valueOf(player.dribbling));
            ((TextView) view.findViewById(R.id.defending_stat)).setText(String.valueOf(player.defending));
            ((TextView) view.findViewById(R.id.physical_stat)).setText(String.valueOf(player.physical));

            // more stats
            ((TextView) view.findViewById(R.id.age_stat)).setText(player.age + " years");
            ((TextView) view.findViewById(R.id.height_stat)).setText(player.height + "cm");
            ((TextView) view.findViewById(R.id.weight_stat)).setText(player.weight + "kg");
            ((TextView) view.findViewById(R.id.club_joined_stat)).setText(player.club_joined);
            ((TextView) view.findViewById(R.id.potential_stat)).setText(String.valueOf(player.potential));
            ((TextView) view.findViewById(R.id.value_stat)).setText(String.valueOf("€" + player.value));
            ((TextView) view.findViewById(R.id.preferred_foot_stat)).setText(player.preferred_foot);
            ((TextView) view.findViewById(R.id.skill_stat)).setText(String.valueOf(player.skill_moves));
            ((TextView) view.findViewById(R.id.weak_foot_stat)).setText(String.valueOf(player.weak_foot));

        });

        // Inflate the layout for this fragment
        return view;
    }
}
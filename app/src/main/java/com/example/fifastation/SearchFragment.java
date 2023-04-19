package com.example.fifastation;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.example.fifastation.db.PlayerDatabase;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.slider.RangeSlider;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    private View view;
    private Context context;
    private final List<String> clubList = new ArrayList<>();
    private final List<String> leagueList = new ArrayList<>();
    private final List<String> nationList = new ArrayList<>();
    List<String> position = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // get layout for this fragment
        view = inflater.inflate(R.layout.fragment_search, container, false);

        // get context
        this.context = container.getContext();
        PlayerDatabase.getInstance(context);

        // populate arrays for search queries
        populateDropdownArray();

        // handle submit
        FloatingActionButton submitBtn = view.findViewById(R.id.submit_search);
        submitBtn.setOnClickListener(btn -> {
            // player name input
            String playerName = ((TextInputLayout) view.findViewById(R.id.player_name_search)).getEditText().getText().toString();

            // rating input (first index is min, second index is max)
            List<Float> rating = ((RangeSlider) view.findViewById(R.id.rating_search)).getValues();

            // club, league, and nation input
            String club = ((AutoCompleteTextView) view.findViewById(R.id.club_dropdown_search)).getText().toString();
            String league = ((AutoCompleteTextView) view.findViewById(R.id.league_dropdown_search)).getText().toString();
            String nation = ((AutoCompleteTextView) view.findViewById(R.id.nation_dropdown_search)).getText().toString();

            // position input
            ((MaterialButtonToggleGroup) view.findViewById(R.id.position_search)).getCheckedButtonIds().forEach(position ->
                this.position.add(((Button) view.findViewById(position)).getText().toString()));
        });

        // inflate the layout
        return view;
    }

    private void populateDropdownArray() {
        // populate club array
        PlayerDatabase.getAllClubs(clubs -> clubs.forEach(club -> this.clubList.add(club.club_name)));
        AutoCompleteTextView clubView = view.findViewById(R.id.club_dropdown_search);
        ArrayAdapter<String> clubAdapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, this.clubList);
        clubView.setAdapter(clubAdapter);

        // populate league array
        PlayerDatabase.getAllLeagues(leagues -> leagues.forEach(league -> this.leagueList.add(league.league_name)));
        AutoCompleteTextView leagueView = view.findViewById(R.id.league_dropdown_search);
        ArrayAdapter<String> leagueAdapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, this.leagueList);
        leagueView.setAdapter(leagueAdapter);

        // populate nation array
        PlayerDatabase.getAllNations(nations -> nations.forEach(nation -> this.nationList.add(nation.nationality)));
        AutoCompleteTextView nationView = view.findViewById(R.id.nation_dropdown_search);
        ArrayAdapter<String> nationAdapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, this.nationList);
        nationView.setAdapter(nationAdapter);
    }
}
package com.example.fifastation;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PlayerDetailFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // get the layout
        View view = inflater.inflate(R.layout.fragment_player_detail, container, false);



        // Inflate the layout for this fragment
        return view;
    }
}
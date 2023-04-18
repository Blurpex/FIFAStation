package com.example.fifastation;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ClubsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // get the layout
        View view = inflater.inflate(R.layout.fragment_clubs, container, false);

        // inflate layout
        return view;
    }
}
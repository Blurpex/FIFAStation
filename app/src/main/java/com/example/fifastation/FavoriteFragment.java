package com.example.fifastation;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FavoriteFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // get the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);

        // inflate layout
        return view;
    }
}
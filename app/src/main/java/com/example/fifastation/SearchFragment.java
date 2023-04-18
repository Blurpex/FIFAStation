package com.example.fifastation;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SearchFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // get layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        // inflate the layout
        return view;
    }
}
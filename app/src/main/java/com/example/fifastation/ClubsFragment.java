package com.example.fifastation;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class ClubsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // get the layout
        View view = inflater.inflate(R.layout.fragment_clubs, container, false);
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


        // inflate layout
        return view;
    }
}
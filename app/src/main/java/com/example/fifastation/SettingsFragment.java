package com.example.fifastation;

import static android.content.Intent.getIntent;
import static android.content.Intent.getIntentOld;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

public class SettingsFragment extends Fragment {
    private RadioButton red, blue, purple, yellow, black, defaultColor;
    private FrameLayout settingsLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // get the layout
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        settingsLayout = view.findViewById(R.id.frameLayout);
        View mainView = inflater.inflate(R.layout.activity_main, container, false);

        // setting theme
        Context context = container.getContext();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String color = prefs.getString("Theme", "");
        Log.d("color", color);
        if(color.equals("red")){
            settingsLayout.setBackgroundColor(getResources().getColor(R.color.lightRed));
        }
        else if(color.equals("blue")){
            settingsLayout.setBackgroundColor(getResources().getColor(R.color.lightBlue));
        }
        else if(color.equals("yellow")){
            settingsLayout.setBackgroundColor(getResources().getColor(R.color.lightYellow));
        }
        else if(color.equals("purple")){
            settingsLayout.setBackgroundColor(getResources().getColor(R.color.lightPurple));
        }
        else if(color.equals("black")){
            settingsLayout.setBackgroundColor(getResources().getColor(R.color.gray));
        }
        else if(color.equals("default")){
            settingsLayout.setBackgroundColor(getResources().getColor(R.color.secondary));
        }

        //define buttons
        red = view.findViewById(R.id.theme_red);
        blue = view.findViewById(R.id.theme_blue);
        purple = view.findViewById(R.id.theme_purple);
        yellow = view.findViewById(R.id.theme_yellow);
        black = view.findViewById(R.id.theme_black);
        defaultColor = view.findViewById(R.id.theme_default);

        // store theme color in shared preferences
        SharedPreferences.Editor editor = prefs.edit();
        Button submitBtn = view.findViewById(R.id.changeTheme);
        submitBtn.setOnClickListener(btn ->{
            if(red.isChecked()){
                settingsLayout.setBackgroundColor(ContextCompat.getColor(settingsLayout.getContext(),R.color.lightRed));
                editor.putString("Theme", "red");
                editor.apply();
                Log.d("theme", "Theme: red ");
            }
            else if(blue.isChecked()){
                settingsLayout.setBackgroundColor(getResources().getColor(R.color.lightBlue));
                editor.putString("Theme", "blue");
                editor.apply();
                Log.d("theme", "Theme: blue");
            }
            else if(yellow.isChecked()){
                settingsLayout.setBackgroundColor(getResources().getColor(R.color.lightYellow));
                editor.putString("Theme", "yellow");
                editor.apply();
                Log.d("theme", "Theme: yellow");
            }
            else if(purple.isChecked()){
                settingsLayout.setBackgroundColor(getResources().getColor(R.color.lightPurple));
                editor.putString("Theme", "purple");
                editor.apply();
                Log.d("theme", "Theme: purple");
            }
            else if(black.isChecked()){
                settingsLayout.setBackgroundColor(getResources().getColor(R.color.gray));
                editor.putString("Theme", "black");
                editor.apply();
                Log.d("theme", "Theme: black");
            }
            else if(defaultColor.isChecked()){
                settingsLayout.setBackgroundColor(getResources().getColor(R.color.secondary));
                editor.putString("Theme","default");
                editor.apply();
                Log.d("theme", "Theme: default");
            }
        });

        // inflate layout
        return view;
    }
}


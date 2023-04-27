package com.example.fifastation;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

public class SettingsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // get the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

       // handle button click
        ExtendedFloatingActionButton applyBtn = view.findViewById(R.id.settings_apply);
        applyBtn.setOnClickListener(btn -> {

            // get checked checkbox
            int selectedBtn = ((RadioGroup) view.findViewById(R.id.themeRadioGroup)).getCheckedRadioButtonId();
            String selectedColor = ((RadioButton)view.findViewById(selectedBtn)).getText().toString();

            // save the theme in shared preferences
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(container.getContext());
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("theme", selectedColor);
            editor.apply();

            // change the theme
            getActivity().recreate();
        });

        // inflate layout
        return view;
    }
}
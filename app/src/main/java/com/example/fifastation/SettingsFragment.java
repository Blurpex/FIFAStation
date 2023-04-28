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
import com.google.android.material.materialswitch.MaterialSwitch;

public class SettingsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // get the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        // initialize shared preferences
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(container.getContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // persist dark mode
        MaterialSwitch darkModeSwitch = view.findViewById(R.id.dark_mode);
        darkModeSwitch.setChecked(sharedPreferences.getBoolean("darkMode", false));

        // handle button click
        ExtendedFloatingActionButton applyBtn = view.findViewById(R.id.settings_apply);
        applyBtn.setOnClickListener(btn -> {

            // get theme
            int selectedBtn = ((RadioGroup) view.findViewById(R.id.themeRadioGroup)).getCheckedRadioButtonId();
            if(selectedBtn > 0) {
                String selectedColor = ((RadioButton)view.findViewById(selectedBtn)).getText().toString();
                editor.putString("theme", selectedColor);
            }

            // handle dark mode status
            boolean darkModeStatus = darkModeSwitch.isChecked();
            editor.putBoolean("darkMode", darkModeSwitch.isChecked());

            // save shared preferences
            editor.apply();

            // change the theme
            requireActivity().recreate();
        });

        // inflate layout
        return view;
    }
}
package com.example.fifastation;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fifastation.db.PlayerDatabase;
import com.google.android.material.appbar.MaterialToolbar;

public class PlayerActivity extends AppCompatActivity {

    private MaterialToolbar toolbar;
    EditText input;
    Button submitBtn;
    Spinner spinner;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        // create dropdown list for league
        spinner = findViewById(R.id.league_dropdown);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.League, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        // dropdown list for nationality
        spinner = findViewById(R.id.nationality_dropdown);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.League, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter1);
        spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);


        input = (EditText) findViewById(R.id.search_header);
        submitBtn = (Button) findViewById(R.id.submitButton);

        submitBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v){
                        String userInput = input.getText().toString();
                        Toast.makeText(PlayerActivity.this,userInput,Toast.LENGTH_SHORT).show();

                        PlayerDatabase.getInstance(this);
                        PlayerDatabase.getPlayersByName(userInput, players->{
                            PlayerAdapter adapter = new PlayerAdapter(this,players);
                            Toast.makeText(PlayerActivity.this,userInput,Toast.LENGTH_SHORT).show();
                        });
                    }
                }
        );
    }

}

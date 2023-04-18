package com.example.fifastation;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fifastation.db.PlayerDatabase;
import com.google.android.material.appbar.MaterialToolbar;

public class PlayerActivity extends AppCompatActivity {

    private MaterialToolbar toolbar;
    EditText input;
    Button submitBtn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        input = (EditText) findViewById(R.id.search_header);
        submitBtn = (Button) findViewById(R.id.submitButton);

        submitBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v){
                        String userInput = input.getText().toString();
                        Toast.makeText(PlayerActivity.this,userInput,Toast.LENGTH_SHORT).show();
                        /*
                        PlayerDatabase.getInstance(this);
                        PlayerDatabase.getPlayersByName(userInput, players->{
                            PlayerAdapter adapter = new PlayerAdapter(this,players);
                            Toast.makeText(PlayerActivity.this,userInput,Toast.LENGTH_SHORT).show();
                        }); */
                    }
                }
        );
    }

}

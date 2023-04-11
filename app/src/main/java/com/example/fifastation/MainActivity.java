package com.example.fifastation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fifastation.db.Player;
import com.example.fifastation.db.PlayerDatabase;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // change the itle of the toolbar title to 'Home'
        CoordinatorLayout outerToolbar = findViewById(R.id.home_toolbar);
        MaterialToolbar toolbar = outerToolbar.findViewById(R.id.topAppBar);
        toolbar.setTitle("Home");

        // open drawer
        toolbar.setNavigationOnClickListener(view -> {
            Log.d("MainActivity", "navigation drawer");
        });

        // handle if the user clicks on the menu items
        toolbar.setOnMenuItemClickListener( menuItem -> {
            switch(menuItem.getItemId()) {
                case R.id.search:
                    Log.d("MainActivity", "search: " + R.id.search);
                    return true;
                case R.id.settings:
                    Log.d("MainActivity", "settings: "  + R.id.settings);
                    return true;
                default: return false;
            }
        });

        PlayerDatabase.getInstance(this);
        PlayerDatabase.getPlayerById(20801, player -> {
            this.player = player;
            Log.d("playerDebug", player.short_name);
        });

    }

    /*public class PlayerListAdapter extends RecyclerView.Adapter<PlayerListAdapter.PlayerViewHolder> {
        class PlayerViewHolder extends RecyclerView.ViewHolder {
            private final TextView playerName;
            private final TextView playerOverall;

            private Player player;

            private PlayerViewHolder(View view) {
                super(view);
                playerName = view.findViewById(R.id.player_name);
                playerOverall = view.findViewById(R.id.player_overall);
            }
        }

        private final LayoutInflater layoutInflater;
        PlayerListAdapter(Context context) {
            layoutInflater = LayoutInflater.from(context);
        }

        @Override
        public PlayerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = layoutInflater.inflate(R.layout.player_list_item, parent, false);
            return new PlayerViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(PlayerViewHolder holder, int position) {
            if (player != null) {
                Player current = player.;
                holder.player = current;
                holder.titleView.setText(current.title);
            }
        }

        void setJokes(List<Joke> jokes){
            this.jokes = jokes;
            notifyDataSetChanged();
        }

        @Override
        public int getItemCount() {
            if (jokes != null)
                return jokes.size();
            else return 0;
        }
    }*/

}

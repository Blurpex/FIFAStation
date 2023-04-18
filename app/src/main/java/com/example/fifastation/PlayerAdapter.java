package com.example.fifastation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fifastation.db.Player;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder> {

    private final Context context;
    private final List<Player> players;

    public PlayerAdapter(Context context, List<Player> players) {
        this.context = context;
        this.players = players;
    }

    // inflate layout and giving a look to the row
    @NonNull
    @Override
    public PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.player_list_item, parent, false);
        return new PlayerViewHolder(view);
    }

    // assign values to the views in the recycler view
    @Override
    public void onBindViewHolder(@NonNull PlayerViewHolder holder, int position) {
        holder.name.setText(this.players.get(position).short_name);
        holder.clubAndNation.setText(this.players.get(position).club_name + " | " + this.players.get(position).nationality);
        holder.rating.setText(String.valueOf(this.players.get(position).rating));
        Picasso.get().load(this.players.get(position).player_face_url).placeholder(R.drawable.player_placeholder).into(holder.photo);
    }

    // number of items to be displayed
    @Override
    public int getItemCount() {
        return players.size();
    }

    // holds views from recycler view
    public static class PlayerViewHolder extends RecyclerView.ViewHolder {

        TextView name, clubAndNation, rating;
        ImageView photo;

        public PlayerViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.player_name);
            clubAndNation = itemView.findViewById(R.id.club_nationality);
            rating = itemView.findViewById(R.id.rating);
            photo = itemView.findViewById(R.id.player_photo);
        }
    }
}

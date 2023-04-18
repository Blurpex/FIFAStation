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

public class ClubAdapter extends RecyclerView.Adapter<ClubAdapter.ClubViewHolder> {

    private final Context context;
    private final List<Player> clubs;

    public ClubAdapter(Context context, List<Player> clubs) {
        this.context = context;
        this.clubs = clubs;
    }

    @NonNull
    @Override
    public ClubViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.club_list_item, parent, false);
        return new ClubViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClubViewHolder holder, int position) {
        holder.name.setText(this.clubs.get(position).club_name);
        holder.league.setText(this.clubs.get(position).league_name);
        Picasso.get().load(this.clubs.get(position).club_logo_url).into(holder.logo);
    }

    @Override
    public int getItemCount() {
        return clubs.size();
    }

    public static class ClubViewHolder extends RecyclerView.ViewHolder {

        TextView name, league;
        ImageView logo;

        public ClubViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.club_name);
            league = itemView.findViewById(R.id.club_league);
            logo = itemView.findViewById(R.id.club_logo);
        }
    }
}

package com.example.fifastation;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PlayerRecycler extends RecyclerView.Adapter<PlayerRecycler.MyViewHolder> {

    // inflate layout and giving a look to the row
    @NonNull
    @Override
    public PlayerRecycler.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    // assign values to the views in the recycler view
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    // number of items to be displayed
    @Override
    public int getItemCount() {
        return 0;
    }

    // holds views from recycler view
    public static class MyViewHolder extends  RecyclerView.ViewHolder {

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}

package com.example.databaseapp.Search;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.databaseapp.Model.Drivers;
import com.example.databaseapp.R;

import java.util.List;

class SearchViewHolder extends RecyclerView.ViewHolder
{
    public TextView name, year, nationality, team;

    public SearchViewHolder(View itemView) {
        super(itemView);
        name = (TextView)itemView.findViewById(R.id.name);
        year = (TextView)itemView.findViewById(R.id.year);
        nationality = (TextView)itemView.findViewById(R.id.nationality);
        team = (TextView)itemView.findViewById(R.id.team);
    }
}


public class SearchInput extends RecyclerView.Adapter<SearchViewHolder> {

    private Context context;
    private final List<Drivers> drivers;

    public SearchInput(Context context, List<Drivers> drivers) {
        this.context = context;
        this.drivers = drivers;
    }

    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.layout_item, parent, false);
        return new SearchViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder( SearchViewHolder holder, int position) {
        holder.name.setText(drivers.get(position).getName());
        holder.year.setText(drivers.get(position).getYear());
        holder.nationality.setText(drivers.get(position).getNationality());
        holder.team.setText(drivers.get(position).getTeam());

    }

    @Override
    public int getItemCount() {
        return drivers.size();
    }
}

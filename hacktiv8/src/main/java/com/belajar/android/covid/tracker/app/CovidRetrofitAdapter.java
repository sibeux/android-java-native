package com.belajar.android.covid.tracker.app;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.belajar.android.R;

import java.util.List;

public class CovidRetrofitAdapter extends RecyclerView.Adapter<CovidRetrofitAdapter.ViewHolder>{

    private final List<CovidRetrofitModelClass> data;

    public CovidRetrofitAdapter(List<CovidRetrofitModelClass> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.covid_tracker_app_layout,
                        parent,false);

        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.cases.setText(""+data.get(position).getCases());
        holder.recovered.setText(""+data.get(position).getRecovered());
        holder.deaths.setText(""+data.get(position).getDeaths());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView cases,recovered,deaths;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cases = itemView.findViewById(R.id.cases);
            recovered = itemView.findViewById(R.id.recovered);
            deaths = itemView.findViewById(R.id.deaths);
        }
    }
}

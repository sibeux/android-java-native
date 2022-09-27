package com.belajar.android.covid.disease.api;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.belajar.android.R;

public class CDAdapter extends RecyclerView.Adapter<CDAdapter.ViewHolder>{

    private final CDModelClass data;

    public CDAdapter(CDModelClass data) {
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

        holder.cases.setText(""+data.getCases());
        holder.recovered.setText(""+data.getRecovered());
        holder.deaths.setText(""+data.getDeaths());

        holder.textViewCases.setText("Total Cases in The World");
        holder.textViewRecovered.setText("Total Recovered in The World");
        holder.textViewDeaths.setText("Total Deaths in The World");
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView cases,recovered,deaths,
                textViewCases,textViewRecovered,textViewDeaths;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cases = itemView.findViewById(R.id.cases);
            recovered = itemView.findViewById(R.id.recovered);
            deaths = itemView.findViewById(R.id.deaths);

            textViewCases = itemView.findViewById(R.id.textViewCases);
            textViewRecovered = itemView.findViewById(R.id.textViewRecovered);
            textViewDeaths = itemView.findViewById(R.id.textViewDeaths);
        }
    }
}

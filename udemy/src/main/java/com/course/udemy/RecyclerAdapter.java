package com.course.udemy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.CountryviewHolder>{

    private final ArrayList<String> countryNameList;
    private final ArrayList<String> detailsList;
    private final ArrayList<Integer> imageList;
    private final Context context;

    public RecyclerAdapter(ArrayList<String> countryNameList, ArrayList<String> detailsList, ArrayList<Integer> imageList, Context context) {
        this.countryNameList = countryNameList;
        this.detailsList = detailsList;
        this.imageList = imageList;
        this.context = context;
    }

    @NonNull
    @Override
    public CountryviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_design, parent, false);

        return new CountryviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryviewHolder holder, int position) {

        holder.textViewCountryName.setText(countryNameList.get(position));
        holder.textViewdetails.setText(detailsList.get(position));
        holder.imageView.setImageResource(imageList.get(position));
        holder.cardView.setOnClickListener(v -> {
            if (position == 0){
                Toast.makeText(context, "You selected the Spain", Toast.LENGTH_SHORT).show();
            } else if (position == 1){
                Toast.makeText(context, "Yout selected the Brazil", Toast.LENGTH_SHORT).show();
            } else if (position == 2){
                Toast.makeText(context, "You Selected the America", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return countryNameList.size();
    }

    public class CountryviewHolder extends RecyclerView.ViewHolder {

        private final TextView textViewCountryName;
        private final TextView textViewdetails;
        private final ImageView imageView;
        private final CardView cardView;

        public CountryviewHolder(@NonNull View itemView) {
            super(itemView);

            textViewCountryName = itemView.findViewById(R.id.textViewCountryName);
            textViewdetails = itemView.findViewById(R.id.textViewDetail);
            imageView = itemView.findViewById(R.id.image_view);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}

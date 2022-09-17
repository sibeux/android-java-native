package com.belajar.android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CountryAdapterSQLite extends RecyclerView.Adapter<CountryAdapterSQLite.ViewHolder> {

    private Context context;

    private List<CountrySQLite> countrySQLiteList = new ArrayList<>();

    public CountryAdapterSQLite(Context context, List<CountrySQLite> countrySQLiteList, EditTextListener editTextListener, RemoveTextListener removeTextListener) {
        this.context = context;
        this.countrySQLiteList = countrySQLiteList;
        this.editTextListener = editTextListener;
        this.removeTextListener = removeTextListener;
    }

    @NonNull
    @Override
    public CountryAdapterSQLite.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.country_item, parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryAdapterSQLite.ViewHolder holder, int position) {
        CountrySQLite countrySQLite = countrySQLiteList.get(position);

        holder.countryIdView.setText(String.valueOf(countrySQLite.getId()));
        holder.countryNameView.setText(String.valueOf(countrySQLite.getCountryName()));
        holder.countryPopulationView.setText(String.valueOf(countrySQLite.getPopulations()));
    }

    @Override
    public int getItemCount() {
        return countrySQLiteList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView countryIdView;
        private TextView countryNameView;
        private TextView countryPopulationView;
        private ImageView editButton;
        private ImageView removeButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            countryIdView = itemView.findViewById(R.id.idCountry);
            countryNameView = itemView.findViewById(R.id.countryName);
            countryPopulationView = itemView.findViewById(R.id.population);

            editButton = itemView.findViewById(R.id.editIcon);
            removeButton = itemView.findViewById(R.id.removeIcon);

            editButton.setOnClickListener(this);
            removeButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            int position = getAdapterPosition();
            if (v.getId() == R.id.editIcon){
                editTextListener.onEditTextClick(position);
            }else if(v.getId() == R.id.removeIcon){
                removeTextListener.onRemoveTextClick(position);
            }
        }
    }

    private EditTextListener editTextListener;
    private RemoveTextListener removeTextListener;

    public interface EditTextListener{
        void onEditTextClick(int position);
    }

    public interface RemoveTextListener{
        void onRemoveTextClick(int position);
    }
}

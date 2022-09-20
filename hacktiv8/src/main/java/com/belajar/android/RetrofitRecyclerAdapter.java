package com.belajar.android;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RetrofitRecyclerAdapter extends RecyclerView.Adapter<RetrofitRecyclerAdapter.ViewHolder>{

    private final List<RetrofitModelClass> data;

    public RetrofitRecyclerAdapter(List<RetrofitModelClass> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.retrofit_recyclerview_layout_item,
                        parent,false);

        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.name.setText(""+data.get(position).getName());
        holder.email.setText(""+data.get(position).getEmail());
        holder.phone.setText(""+data.get(position).getPhone());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView name,email,phone;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.textViewName);
            email = itemView.findViewById(R.id.textViewEmail);
            phone = itemView.findViewById(R.id.textViewPhone);
        }
    }
}

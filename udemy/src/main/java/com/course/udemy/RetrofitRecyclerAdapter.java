package com.course.udemy;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RetrofitRecyclerAdapter extends RecyclerView.Adapter<RetrofitRecyclerAdapter.ViewHolder> {

    private List<RetrofitModelClass> data;

    public RetrofitRecyclerAdapter(List<RetrofitModelClass> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_layout_item,
                        parent,false);

        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.texta.setText(""+data.get(position).getUserID());
        holder.textb.setText(""+data.get(position).getID());
        holder.textc.setText(""+data.get(position).getTitle());
        holder.textd.setText(""+data.get(position).getSubTitle());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView texta,textb,textc,textd;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            texta = itemView.findViewById(R.id.textView1);
            textb = itemView.findViewById(R.id.textView2);
            textc = itemView.findViewById(R.id.textView3);
            textd = itemView.findViewById(R.id.textView4);
        }
    }
}

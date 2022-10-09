package com.belajar.android.to_do_list;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.belajar.android.R;

import java.util.ArrayList;
import java.util.List;

public class ToDoListAdapter extends RecyclerView.Adapter<ToDoListAdapter.ViewHolder> {
    private List<ListModuleClass> moduleClassList;
    Context context;

    public ToDoListAdapter(List<ListModuleClass> moduleClassList,
                           Context context, MoreButtonListener moreButtonListener,
                           SquareButtonListener squareButtonListener) {

        this.moduleClassList = moduleClassList;
        this.context = context;
        this.moreButtonListener = moreButtonListener;
        this.squareButtonListener = squareButtonListener;
    }

    public void setItems(ArrayList<ListModuleClass> moduleClassList){
        moduleClassList.addAll(moduleClassList);
    }

    @NonNull
    @Override
    public ToDoListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.to_do_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ToDoListAdapter.ViewHolder holder, int position) {

        ListModuleClass data = moduleClassList.get(position);
        holder.itemList.setText(data.getList());

        if (data.getCheck().equals("true")){
            holder.checklist.setVisibility(View.VISIBLE);
            holder.itemList.setPaintFlags(holder.itemList.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.itemList.setTextColor(Color.GRAY);
            holder.more.setVisibility(View.INVISIBLE);
        } else {
            holder.checklist.setVisibility(View.INVISIBLE);
            holder.itemList.setPaintFlags(0);
            holder.itemList.setTextColor(Color.BLACK);
            holder.more.setVisibility(View.VISIBLE);
        }

        holder.more.setOnClickListener(view -> moreButtonListener.moreButtonClick(position,view,data));
        holder.square.setOnClickListener(view -> squareButtonListener.squareButtonClick(data));
    }

    @Override
    public int getItemCount() {
        return moduleClassList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView itemList;
        ImageView checklist;
        ImageView more;
        ImageView square;

        public ViewHolder(View itemView) {
            super(itemView);

            itemList = itemView.findViewById(R.id.ListItem);
            square = itemView.findViewById(R.id.box);
            more = itemView.findViewById(R.id.moreItemList);
            checklist = itemView.findViewById(R.id.centang);
        }
    }

    private final MoreButtonListener moreButtonListener;
    private final SquareButtonListener squareButtonListener;

    public interface MoreButtonListener{
        void moreButtonClick(int position, View view, ListModuleClass data);
    }

    public interface SquareButtonListener{
        void squareButtonClick(ListModuleClass data);
    }
}
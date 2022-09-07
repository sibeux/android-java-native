package com.belajar.android;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ClubAdapter extends RecyclerView.Adapter<ClubAdapter.ViewHolder> {

    // 1 dataset
    private List<Club> clubList;

    // 2 constructor
    public ClubAdapter(List<Club> clubList){
        this.clubList = clubList;
    }

    private OnClubClickListener listener;

    public interface OnClubClickListener{
        void onClick(View view, int position);
    }

    public void setListener(OnClubClickListener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vh = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.club_item,parent,false);

        return new ViewHolder(vh);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Club club = clubList.get(position);
        holder.teamName.setText(club.getTeamName());
        Picasso.get()
                .load(club.getLogo())
//                .placeholder(R.drawable.ic_launcher_background)
                // gunanya buat placeholder saat gambar diload
                .into(holder.logo);

    }

    @Override
    public int getItemCount() {
        return clubList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // 5 membuat variable di view holder
        private final ImageView logo;
        private final TextView teamName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            logo = itemView.findViewById(R.id.logo_view);
            teamName = itemView.findViewById(R.id.nama_team);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                Club club = clubList.get(position);
                String name = club.getTeamName();
                int logo = club.getLogo();
                Toast.makeText(v.getContext(),name,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(v.getContext(), UserInteraction2.class);
                intent.putExtra("namaTeam",name);
                intent.putExtra("logoTeam",logo);
                v.getContext().startActivity(intent);
            });
        }
    }
}

package com.belajar.android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class UserInteraction extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_interaction);

        RecyclerView clubListRv = (RecyclerView) findViewById(R.id.club_list_rv);

        List<Club> clubList = new ArrayList<>();
        clubList.add(new Club("Arsenal",R.drawable.arsenal));
        clubList.add(new Club("Manchester United", R.drawable.mu));
        clubList.add(new Club("Chelsea", R.drawable.celsi));
        clubList.add(new Club("Liverpool", R.drawable.pool));
        clubList.add(new Club("Barcelona", R.drawable.fcb));
        clubList.add(new Club("Real Madrid", R.drawable.madrid));
        clubList.add(new Club("Juventus", R.drawable.jupe));
        clubList.add(new Club("Manchester City", R.drawable.city));
        clubList.add(new Club("Paris Saint Germain", R.drawable.psg));

        ClubAdapter clubAdapter = new ClubAdapter(clubList);
        clubListRv.setAdapter(clubAdapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        clubListRv.setLayoutManager(layoutManager);
    }
}
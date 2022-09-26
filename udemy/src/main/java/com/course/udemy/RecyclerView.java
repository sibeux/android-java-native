package com.course.udemy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import java.util.ArrayList;

public class RecyclerView extends AppCompatActivity {

    private final ArrayList<String> countryNameList = new ArrayList<>();
    private final ArrayList<String> detailsList = new ArrayList<>();
    private final ArrayList<Integer> imageList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        androidx.recyclerview.widget.RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(RecyclerView.this));

        countryNameList.add("Spain");
        countryNameList.add("Brazil");
        countryNameList.add("America");

        detailsList.add("Thus is the Spain Flag");
        detailsList.add("Thus is the Brazil Flag");
        detailsList.add("Thus is the America Flag");

        imageList.add(R.drawable.spain);
        imageList.add(R.drawable.brazil);
        imageList.add(R.drawable.america);

        RecyclerAdapter adapter = new RecyclerAdapter(countryNameList, detailsList, imageList, RecyclerView.this);
        recyclerView.setAdapter(adapter);

    }
}
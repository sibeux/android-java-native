package com.course.udemy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.ArrayList;

public class GridView extends AppCompatActivity {

    android.widget.GridView gridView;
    ArrayList<String> text = new ArrayList<>();
    ArrayList<Integer> image = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);

        gridView = findViewById((R.id.gridview));
        fillArray();
        GridAdapter adapter = new GridAdapter(this, text, image);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener((parent, view, position, id) -> {
            Toast.makeText(getApplicationContext(), "You selected " + text.get(position),
                    Toast.LENGTH_SHORT).show();
        });
    }

    public void fillArray(){
        text.add("Bird");
        text.add("Cat");
        text.add("Lion");
        text.add("Zebra");
        text.add("Duck");
        text.add("Dog");
        text.add("Snake");
        text.add("Sheep");
        text.add("Horse");

        image.add(R.drawable.bird);
        image.add(R.drawable.cat);
        image.add(R.drawable.lion);
        image.add(R.drawable.zebra);
        image.add(R.drawable.duck);
        image.add(R.drawable.dog);
        image.add(R.drawable.snake);
        image.add(R.drawable.sheep);
        image.add(R.drawable.horse);
    }
}
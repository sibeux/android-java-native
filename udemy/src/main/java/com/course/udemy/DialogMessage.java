package com.course.udemy;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DialogMessage extends AppCompatActivity {

    ImageView picture;
    TextView text;
    Button delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_message);

        picture = findViewById(R.id.insta);
        text = findViewById(R.id.textView);
        delete = findViewById(R.id.delete);

        delete.setOnClickListener(v -> showDialogMessage());
    }

    @SuppressLint("SetTextI18n")
    private void showDialogMessage() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Delete")
                .setMessage("Do you want to delete text?")
                .setNegativeButton("No", (dialog, which) -> dialog.cancel())
                .setPositiveButton("Yes", (dialog, which) -> {
                    picture.setVisibility(View.INVISIBLE);
                    text.setText("Image Deleted");
                }).show();
        alertDialog.create();
    }

}
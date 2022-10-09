package com.belajar.android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.belajar.android.to_do_list.DatabaseList;
import com.belajar.android.to_do_list.ListModuleClass;
import com.belajar.android.to_do_list.ToDoListAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class ToDoListApp extends AppCompatActivity implements
        ToDoListAdapter.MoreButtonListener, ToDoListAdapter.SquareButtonListener{

    TextView itemList ;
    ImageView checklist;
    ImageView more;

    String key =  null;

    ToDoListAdapter adapter;

    DatabaseList databaseList = new DatabaseList();

    RecyclerView recyclerView;
    ArrayList<ListModuleClass> listTODO;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list_app);

        listTODO = new ArrayList<>();

        adapter = new ToDoListAdapter(listTODO,ToDoListApp.this,
                        ToDoListApp.this,ToDoListApp.this);

        recyclerView = findViewById(R.id.toDoList_review);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        loadData();
    }

    private void loadData() {
        databaseList.get(key).addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                listTODO.clear();

                for(DataSnapshot data : snapshot.getChildren()){
                    ListModuleClass module = data.getValue(ListModuleClass.class);
                    module.setKey(data.getKey());
                    listTODO.add(module);
                    key = data.getKey();
                }
//                adapter.setItems(listTODO);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu_to_do_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_item_add) {
            // Do something
            showDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showDialog(){
        final Context c = this;

        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(this);
        @SuppressLint("InflateParams") View mView = layoutInflaterAndroid.inflate(R.layout.user_input_dialog_box, null);
        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(c);
        alertDialogBuilderUserInput.setView(mView);

        TextView userInput = mView.findViewById(R.id.userInputDialog);
        alertDialogBuilderUserInput
                .setCancelable(false)
                .setPositiveButton("OK", (dialogBox, id1) -> {
                    // ToDo get user input here
                    String list = userInput.getText().toString();

                    if (list.trim().length() != 0){
                        ListModuleClass listModuleClass = new ListModuleClass(list,"false");
                        databaseList.add(listModuleClass);
                    }
                })
                .setNegativeButton("CANCEL",
                        (dialogBox, id12) -> dialogBox.cancel())
                .setNeutralButton("NEXT", (dialogInterface, i) -> {
                    // ToDo get user input here
                    String list = userInput.getText().toString();

                    if (list.trim().length() != 0){
                        ListModuleClass listModuleClass = new ListModuleClass(list,"false");
                        databaseList.add(listModuleClass);
                    }

                    showDialog();
                });
        AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();
        alertDialogAndroid.setCanceledOnTouchOutside(true);
        alertDialogAndroid.show();
    }

    public void popUpMenu(View view, ListModuleClass data){
        PopupMenu menu = new PopupMenu(this,view);
        menu.inflate(R.menu.menu_item_list);
        menu.setOnMenuItemClickListener(menuItem -> {
            switch (menuItem.getItemId()){
                case R.id.edit_item:
                    showEditDialog(data);
                    return true;
                case R.id.remove_item:
                    removeItem(data);
                    return true;
                default:
                    return false;
            }
        });
        menu.show();
    }

    @Override
    public void moreButtonClick(int position, View view, ListModuleClass data) {
        popUpMenu(view,data);
    }

    @Override
    public void squareButtonClick(ListModuleClass data) {

        DatabaseList dao = new DatabaseList();

        HashMap<String,Object> hashMap = new HashMap<>();
        if (data.getCheck().equals("true")){
            hashMap.put("check","false");
        } else{
            hashMap.put("check","true");
        }
        dao.update(data.getKey(),hashMap);
    }

    @SuppressLint("SetTextI18n")
    protected void showEditDialog(ListModuleClass data){
        final Context c = this;
        DatabaseList dao = new DatabaseList();

        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(this);
        @SuppressLint("InflateParams") View mView = layoutInflaterAndroid.inflate(R.layout.user_input_dialog_box, null);
        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(c);
        alertDialogBuilderUserInput.setView(mView);

        TextView userInput = mView.findViewById(R.id.userInputDialog);
        TextView dialogTitle = mView.findViewById(R.id.dialogTitle);

        dialogTitle.setText("Edit Data");
        userInput.setText(data.getList().trim());

        alertDialogBuilderUserInput
                .setCancelable(false)
                .setPositiveButton("OK", (dialogBox, id1) -> {
                    // ToDo get user input here
                    String list = userInput.getText().toString();

                    if (list.trim().length() != 0){
                        HashMap<String,Object> hashMap = new HashMap<>();
                        hashMap.put("list",list);
                        dao.update(data.getKey(),hashMap);
                        Toast.makeText(c, "Data Changed", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("CANCEL",
                        (dialogBox, id12) -> dialogBox.cancel());
        AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();
        alertDialogAndroid.setCanceledOnTouchOutside(true);
        alertDialogAndroid.show();
    }

    protected void removeItem(ListModuleClass data){
        DatabaseList dao = new DatabaseList();
        dao.remove(data.getKey());
        Toast.makeText(this, "Data Removed", Toast.LENGTH_SHORT).show();
    }
}

package com.belajar.android.database.api;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.belajar.android.DatabaseApiEditActivity;
import com.belajar.android.DatabaseApiMainActivity;
import com.belajar.android.R;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;


public class StudentRVAdapter extends RecyclerView.Adapter<StudentRVAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<String> array_noinduk,array_nama,array_alamat,array_hobi;
    ProgressDialog progressDialog;

    public StudentRVAdapter(Context mContext, ArrayList<String> array_noinduk,
                            ArrayList<String> array_nama,ArrayList<String> array_alamat,
                            ArrayList<String> array_hobi) {
        super();
        this.mContext = mContext;
        this.array_noinduk = array_noinduk;
        this.array_nama = array_nama;
        this.array_alamat = array_alamat;
        this.array_hobi = array_hobi;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.student_rv,parent,false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.tv_noind.setText(array_noinduk.get(position));
        holder.tv_nama.setText(array_nama.get(position));
        holder.tv_alamat.setText(array_alamat.get(position));
        holder.tv_hobi.setText(array_hobi.get(position));

        holder.cv_main.setOnClickListener(view -> {
            Intent i = new Intent(mContext, DatabaseApiEditActivity.class);
            i.putExtra("noinduk",array_noinduk.get(position));
            i.putExtra("nama",array_nama.get(position));
            i.putExtra("alamat",array_alamat.get(position));
            i.putExtra("hobi",array_hobi.get(position));
            ((DatabaseApiMainActivity)mContext).startActivityForResult(i,2);
        });

        holder.cv_main.setOnLongClickListener(view -> {
            new AlertDialog.Builder(mContext)
                    .setMessage("Ingin menghapus nomor induk "+array_noinduk.get(position)+" ?")
                    .setCancelable(false)
                    .setPositiveButton("Ya", (dialogInterface, i) -> {
                        progressDialog.setMessage("Menghapus...");
                        progressDialog.setCancelable(false);
                        progressDialog.show();

                        AndroidNetworking.post("https://sibeux.my.id/database/android_crud/deleteSiswa.php")
                                .addBodyParameter("noinduk",""+array_noinduk.get(position))
                                .setPriority(Priority.MEDIUM)
                                .build()
                                .getAsJSONObject(new JSONObjectRequestListener() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        progressDialog.dismiss();
                                        try {
                                            boolean status = response.getBoolean("status");
                                            Log.d("statuss",""+status);
                                            String result = response.getString("result");
                                            if(status){
                                                if(mContext instanceof DatabaseApiMainActivity){
                                                    ((DatabaseApiMainActivity)mContext).scrollRefresh();
                                                }
                                            }else{
                                                Toast.makeText(mContext, ""+result, Toast.LENGTH_SHORT).show();
                                            }
                                        }catch (Exception e){
                                            e.printStackTrace();
                                        }
                                    }

                                    @Override
                                    public void onError(ANError anError) {
                                    }
                                });
                    })
                    .setNegativeButton("Tidak", (dialogInterface, i) -> dialogInterface.cancel()).show();
            return false;
        });

    }

    @Override
    public int getItemCount() {
        return array_noinduk.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tv_noind,tv_nama,tv_alamat,tv_hobi;
        public CardView cv_main;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cv_main = itemView.findViewById(R.id.cv_main);
            tv_noind = itemView.findViewById(R.id.tv_noind);
            tv_nama = itemView.findViewById(R.id.tv_nama);
            tv_alamat = itemView.findViewById(R.id.tv_alamat);
            tv_hobi = itemView.findViewById(R.id.tv_hobi);

            progressDialog = new ProgressDialog(mContext);
        }
    }

}

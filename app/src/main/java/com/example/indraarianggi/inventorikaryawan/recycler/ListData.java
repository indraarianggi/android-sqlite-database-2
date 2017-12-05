package com.example.indraarianggi.inventorikaryawan.recycler;

import android.app.Activity;
import android.content.Intent;
import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;

import com.example.indraarianggi.inventorikaryawan.MainActivity;
import com.example.indraarianggi.inventorikaryawan.R;
import com.example.indraarianggi.inventorikaryawan.adapterKaryawan.DBDataSource;
import com.example.indraarianggi.inventorikaryawan.adapterKaryawan.DBHelper;
import com.example.indraarianggi.inventorikaryawan.adapterKaryawan.Karyawan;

import java.util.ArrayList;

public class ListData extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    private DBHelper dbHelper;
    public static Activity activity;

    // Inisialisasi kontroler
    private DBDataSource dataSource;

    // Inisialisasi ArrayList
    private ArrayList<Karyawan> karyawanArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);

        activity = this;
        dataSource = new DBDataSource(this);
        dataSource.open();
        try {
            dataSource.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        karyawanArrayList = (ArrayList<Karyawan>) dataSource.getAllKaryawan();
        ArrayAdapter<Karyawan> arrayAdapter = new ArrayAdapter<Karyawan>(
                this, android.R.layout.simple_list_item_1, karyawanArrayList);

        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);

        // Membuat adapter baru untuk RecyclerView
        RecyclerAdapter adapter = new RecyclerAdapter(this, karyawanArrayList);
        // Menset nilai dari adapter
        recyclerView.setAdapter(adapter);
        // Menset setukuran
        recyclerView.setHasFixedSize(true);
        // Menset layout manager dan menampilkan list/daftar
        // dalam bentuk linearlayoutmanager pada class saat ini
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ListData.this, MainActivity.class));
    }
}

package com.example.indraarianggi.inventorikaryawan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.indraarianggi.inventorikaryawan.recycler.ListData;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton ibTambah, ibLihat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ibTambah = (ImageButton) findViewById(R.id.ib_tambah_data);
        ibLihat = (ImageButton) findViewById(R.id.ib_lihat_data);

        ibTambah.setOnClickListener(this);
        ibLihat.setOnClickListener(this);
    }

    // Method handler event click button
    // Mengarahkan ke tambah data/lihat data karyawan
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_tambah_data:
                Intent i = new Intent(HomeActivity.this, TambahDataActivity.class);
                startActivity(i);
                break;
            case R.id.ib_lihat_data:
                Intent i2 = new Intent(HomeActivity.this, ListData.class);
                startActivity(i2);
                break;
        }
    }

    // Method agar tidak kembali ke Login (SignupActivity).

    @Override
    public void onBackPressed() {
        startActivity(new Intent(HomeActivity.this, HomeActivity.class));
        finish();
    }
}

package com.example.indraarianggi.inventorikaryawan;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.SQLException;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.indraarianggi.inventorikaryawan.adapterKaryawan.DBDataSource;
import com.example.indraarianggi.inventorikaryawan.adapterKaryawan.Karyawan;
import com.example.indraarianggi.inventorikaryawan.recycler.ListData;

public class DetailActivity extends AppCompatActivity {

    private TextView tvNama, tvEmail, tvDeplop, tvPerusahaan, tvId;
    private Button btnEdit, btnHapus;
    private DBDataSource dbDataSource;
    Karyawan karyawan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        dbDataSource = new DBDataSource(this);
        dbDataSource.open();
        try {
            dbDataSource.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        initViews();

        // Manangkap data dari intent.
        tvNama.setText(getIntent().getStringExtra("karNama"));
        tvEmail.setText(getIntent().getStringExtra("karEmail"));
        tvDeplop.setText(getIntent().getStringExtra("karDeplop"));
        tvPerusahaan.setText(getIntent().getStringExtra("karPerusahaan"));

        Log.w("INFOO", getIntent().getStringExtra("karId"));

        btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                karyawan = new Karyawan();
                karyawan.setId(Long.parseLong(""+getIntent().getStringExtra("karId")));

                AlertDialog.Builder dialog = new AlertDialog.Builder(DetailActivity.this);
                dialog.setTitle("Konfirmasi");
                dialog.setMessage("Anda akan menghapus data ini ?");
                dialog.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dbDataSource.deleteKaryawan(karyawan);
                        Toast.makeText(DetailActivity.this, "Berhasil dihapus!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(DetailActivity.this, ListData.class));
                    }
                });
                dialog.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                dialog.show();
            }
        });

        // munculkan dialog edit data.
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(DetailActivity.this);
                dialog.setContentView(R.layout.ubah_data);

                final EditText edtNama = (EditText)dialog.findViewById(R.id.edt_nama);
                final EditText edtEmail = (EditText)dialog.findViewById(R.id.edt_email);
                final EditText edtDeplop = (EditText)dialog.findViewById(R.id.edt_deplop);
                final EditText edtPerusahaan = (EditText)dialog.findViewById(R.id.edt_perusahaan);
                final Button btnSimpan = (Button)dialog.findViewById(R.id.btn_simpan);

                edtNama.setText(tvNama.getText());
                edtEmail.setText(tvEmail.getText());
                edtDeplop.setText(tvDeplop.getText());
                edtPerusahaan.setText(tvPerusahaan.getText());

                dialog.setTitle("Udah Data Karyawan");
                dialog.show();

                // Menyimpan data yang telah diedit.
                btnSimpan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        tvNama.setText(edtNama.getText());
                        tvEmail.setText(edtEmail.getText());
                        tvDeplop.setText(edtDeplop.getText());
                        tvPerusahaan.setText(edtPerusahaan.getText());

                        karyawan = new Karyawan();

                        karyawan.setId(Long.parseLong(""+getIntent().getStringExtra("karId")));
                        karyawan.setNama(""+edtNama.getText());
                        karyawan.setEmail(""+edtEmail.getText());
                        karyawan.setDeplop(""+edtDeplop.getText());
                        karyawan.setPerusahaan(""+edtPerusahaan.getText());

                        DetailActivity.this.finish();
                        dialog.dismiss();
                        startActivity(new Intent(DetailActivity.this, ListData.class));
                    }
                });
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(DetailActivity.this, ListData.class));
        finish();
    }

    private void initViews() {
        tvNama = (TextView)findViewById(R.id.tv_nama);
        tvEmail = (TextView)findViewById(R.id.tv_email);
        tvDeplop = (TextView)findViewById(R.id.tv_depelop);
        tvPerusahaan = (TextView)findViewById(R.id.tv_perusahaan);

        btnEdit = (Button) findViewById(R.id.btn_edit);
        btnHapus = (Button) findViewById(R.id.btn_hapus);
    }
}

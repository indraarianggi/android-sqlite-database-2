package com.example.indraarianggi.inventorikaryawan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.indraarianggi.inventorikaryawan.adapterKaryawan.DBDataSource;
import com.example.indraarianggi.inventorikaryawan.adapterKaryawan.Karyawan;
import com.example.indraarianggi.inventorikaryawan.recycler.ListData;

public class TambahDataActivity extends AppCompatActivity implements View.OnClickListener {

    // Deklarasi variabel
    private Button btnSubmit;
    private EditText edtNama, edtEmail, edtDeplop, edtPerusahaan;
    private DBDataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data);

        edtNama = (EditText)findViewById(R.id.edt_nama);
        edtEmail = (EditText)findViewById(R.id.edt_email);
        edtDeplop = (EditText)findViewById(R.id.edt_deplop);
        edtPerusahaan = (EditText)findViewById(R.id.edt_perusahaan);

        dataSource = new DBDataSource(this);
        dataSource.open();

        btnSubmit = (Button)findViewById(R.id.btn_submit);
        btnSubmit.setOnClickListener(this);
    }

    // Method pada button, yang jika diklik akan menyimpan data
    @Override
    public void onClick(View view) {
        String txtNama = null;
        String txtEmail = null;
        String txtDeplop = null;
        String txtPerusahaan = null;

        @SuppressWarnings("unused")
        Karyawan karyawan = null;

        if (edtNama.getText()!=null && edtEmail.getText()!=null &&
                edtDeplop.getText()!=null && edtPerusahaan.getText()!=null) {
            txtNama = edtNama.getText().toString();
            txtEmail = edtEmail.getText().toString();
            txtDeplop = edtDeplop.getText().toString();
            txtPerusahaan = edtPerusahaan.getText().toString();
        }

        switch (view.getId()) {
            case R.id.btn_submit:
                karyawan = dataSource.createKaryawan(txtNama, txtEmail, txtDeplop, txtPerusahaan);

                startActivity(new Intent(TambahDataActivity.this, ListData.class));
                Toast.makeText(this, "Berhasil masukkan "+" Nama: "+ karyawan.getNama() +
                        " Email: "+ karyawan.getEmail() +" Dep: "+ karyawan.getDeplop(), Toast.LENGTH_LONG).show();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(TambahDataActivity.this, MainActivity.class));
        finish();
    }
}

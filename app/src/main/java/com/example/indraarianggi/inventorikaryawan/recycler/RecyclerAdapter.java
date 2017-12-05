package com.example.indraarianggi.inventorikaryawan.recycler;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.indraarianggi.inventorikaryawan.DetailActivity;
import com.example.indraarianggi.inventorikaryawan.R;
import com.example.indraarianggi.inventorikaryawan.adapterKaryawan.DBDataSource;
import com.example.indraarianggi.inventorikaryawan.adapterKaryawan.DBHelper;
import com.example.indraarianggi.inventorikaryawan.adapterKaryawan.Karyawan;

import java.util.ArrayList;

/**
 * Created by indraarianggi on 05/12/17.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    // Deklarasi variabel context
    private SQLiteDatabase db;
    private final Context context;
    private DBHelper dbHelper;
    ArrayList<Karyawan> karyawanArrayList;

    LayoutInflater inflater;

    public RecyclerAdapter(Context context, ArrayList<Karyawan> values) {
        this.context = context;
        this.karyawanArrayList = values;

        // Buat dv
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_list, parent, false);
        RecyclerViewHolder viewHolder = new RecyclerViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, final int position) {
        final String KarId = "" + karyawanArrayList.get(position).getId();
        final String KarNama = "" + karyawanArrayList.get(position).getNama();
        final String KarEmail = "" + karyawanArrayList.get(position).getEmail();
        final String KarDeplop = "" + karyawanArrayList.get(position).getDeplop();
        final String KarPerusahaan = "" + karyawanArrayList.get(position).getPerusahaan();

        holder.tvNama.setText(karyawanArrayList.get(position).getNama());
        holder.tvEmail.setText(karyawanArrayList.get(position).getEmail());
        holder.tvDepee.setText(karyawanArrayList.get(position).getDeplop());
        holder.tvPeree.setText(karyawanArrayList.get(position).getPerusahaan());

        // Menampilkan data pada CardView
        holder.tvNama.setTag(holder);
        holder.tvEmail.setTag(holder);

        // Event click ketika salah satu text di CardView diklik.
        holder.tvNama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenDetailKaryawan(KarId, KarNama, KarEmail, KarDeplop, KarPerusahaan);
            }
        });

        // Event ImageButton yang ada pada CardView.
        holder.btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteItem(karyawanArrayList.get(position).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return karyawanArrayList.size();
    }

    // Method hapus data
    private void deleteItem(final long karId) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setTitle("Konfirmasi");
        dialog.setMessage("Hapus User ?");
        dialog.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DBDataSource.delete(karId, db);
                Intent intent = new Intent(context, ListData.class);
                Toast.makeText(context, "Dihapus !", Toast.LENGTH_SHORT).show();
                context.startActivity(intent);
            }
        });
        dialog.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        dialog.show();
    }

    // Array untuk menampung intent untuk mengirim data ke activity detail.
    private void OpenDetailKaryawan(String id, String nama, String email, String deplop, String perusahaan) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra("karId", id);
        intent.putExtra("karNama", nama);
        intent.putExtra("karEmail", email);
        intent.putExtra("karDeplop", deplop);
        intent.putExtra("karPerusahaan", perusahaan);
        context.startActivity(intent);

        ListData.activity.finish();
    }
}

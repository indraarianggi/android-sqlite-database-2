package com.example.indraarianggi.inventorikaryawan.adapterKaryawan;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by indraarianggi on 04/12/17.
 */

public class DBDataSource {

    // Variable to hold the database instance.
    private SQLiteDatabase db;

    // Inisialisasi kelas DBHelper.
    private DBHelper dbHelper;

    private String[] allColumns = { DBHelper.COLUMN_ID, DBHelper.COLUMN_NAME,
            DBHelper.COLUMN_EMAIL, DBHelper.COLUMN_DEPLOP, DBHelper.COLUMN_PERUSAHAAN};

    public DBDataSource(Context context) {
        dbHelper = new DBHelper(context);
    }

    // Membuka dan membuat sambungan baru ke database.
    public void open() throws SQLException {
        db = dbHelper.getWritableDatabase();
    }

    // Menutup sambungan ke database.
    public void close() {
        dbHelper.close();
    }

    // Method untuk create/insert data ke database.
    public Karyawan createKaryawan(String nama, String email, String deplop, String perusahaan) {
        // Membuat sebuah ContentValues yang berfungsi
        // untuk memasangkan data dengan nama-nama kolom pada database
        ContentValues values = new ContentValues();

        values.put(DBHelper.COLUMN_NAME, nama);
        values.put(DBHelper.COLUMN_EMAIL, email);
        values.put(DBHelper.COLUMN_DEPLOP, deplop);
        values.put(DBHelper.COLUMN_PERUSAHAAN, perusahaan);

        // Mengeksekusi perintah sql insert data
        // yang akan mengembalikan sebuah insertId.
        long insertId = db.insert(DBHelper.TABLE_NAME, null, values);
        Cursor cursor = db.query(DBHelper.TABLE_NAME, allColumns, DBHelper.COLUMN_ID + " = " + insertId,
                null, null, null, null);

        // Pindah ke data paling pertama.
        cursor.moveToFirst();
        // Mengubah objek pada cursor pertama ke dalam objek karyawan.
        Karyawan newKaryawan = cursorToKaryawan(cursor);
        // Close cursor.
        cursor.close();
        // Mengembalikan karyawan baru.
        return newKaryawan;
    }

    private Karyawan cursorToKaryawan(Cursor cursor) {
        Karyawan karyawan = new Karyawan();

        //debug logcat
        Log.v("info", "the getLong"+cursor.getLong(0));
        Log.v("info", "The setLatLng"+cursor.getString(1)+","+cursor.getString(2));

        // Set atribut pada objek karyawan
        // dengan data cursor yang diambil dari database
        karyawan.setId(cursor.getLong(0));
        karyawan.setNama(cursor.getString(1));
        karyawan.setEmail(cursor.getString(2));
        karyawan.setDeplop(cursor.getString(3));
        karyawan.setPerusahaan(cursor.getString(4));

        return karyawan;
    }

    // Mengambil semua data karyawan.
    public ArrayList<Karyawan> getAllKaryawan() {
        ArrayList<Karyawan> daftarKaryawan = new ArrayList<Karyawan>();

        // Select all SQL query.
        Cursor cursor = db.query(DBHelper.TABLE_NAME, allColumns,
                null, null, null, null, null);

        // Pindah ke data paling pertama.
        cursor.moveToFirst();

        // Jika masih ada data, masukkan data karyawan ke daftarKaryawan
        while (!cursor.isAfterLast()) {
            Karyawan karyawan = cursorToKaryawan(cursor);
            daftarKaryawan.add(karyawan);
            cursor.moveToNext();
        }

        // Pastikan menutup cursor.
        cursor.close();

        return daftarKaryawan;
    }

    // Method edit.
    public void editKaryawan(Karyawan karyawan) {
        Log.w("INFO", "updateUser:" + karyawan.getId());

        // Ambil data id karyawan.
        String strFilter = "_id = " + karyawan.getId();
        // Masukkan ke ContentValues.
        ContentValues args = new ContentValues();
        // Masukkan data sesuai dengan kolom.
        args.put(DBHelper.COLUMN_NAME, karyawan.getNama());
        args.put(DBHelper.COLUMN_EMAIL, karyawan.getEmail());
        args.put(DBHelper.COLUMN_DEPLOP, karyawan.getDeplop());
        args.put(DBHelper.COLUMN_PERUSAHAAN, karyawan.getPerusahaan());

        // Update query.
        db.update(DBHelper.TABLE_NAME, args, strFilter, null);
    }

    public Karyawan getKaryawan(long id) {
        // Inisialisasi karyawan.
        Karyawan karyawan = new Karyawan();

        // Select query.
        Cursor cursor = db.query(DBHelper.TABLE_NAME, allColumns, "_id = " + id,
                null, null, null, null);
        // Ambil data yang pertama.
        cursor.moveToFirst();
        // Masukkan data cursor ke objek karyawan.
        karyawan = cursorToKaryawan(cursor);
        // Tutup sambungan.
        cursor.close();
        // Return karyawan.
        return karyawan;
    }

    public void deleteKaryawan(Karyawan k) {
        String strFilter = "_id = " + k.getId();
        // ContentValues args = new ContentValues();
        db.delete(DBHelper.TABLE_NAME, strFilter, null);
    }

    public static boolean delete(long id, SQLiteDatabase database) {
        database.delete(DBHelper.TABLE_NAME, DBHelper.COLUMN_ID + " = " + id, null);
        return true;
    }
}

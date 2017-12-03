package com.example.indraarianggi.inventorikaryawan.adapterKaryawan;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by indraarianggi on 04/12/17.
 */

public class DBHelper extends SQLiteOpenHelper {

    // Deklarasi konstanta-konstanta yang digunakan pada Database.
    public static final String TABLE_NAME = "karyawan";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "nama";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_DEPLOP = "deplop";
    public static final String COLUMN_PERUSAHAAN = "perusahaan";
    public static final String db_name = "inventori.db";
    public static final int db_version = 1;

    // Perintah membuat tabel.
    private static final String db_create = "CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NAME + " VARCHAR(50) NOT NULL, "
            + COLUMN_EMAIL + " VARCHAR(50) NOT NULL, "
            + COLUMN_DEPLOP + " VARCHAR(50) NOT NULL, "
            + COLUMN_PERUSAHAAN + " VARCHAR(50) NOT NULL);";

    public DBHelper(Context context) {
        super(context, db_name, null, db_version);
    }

    // Dijalankan ketika tidak ada database di disk
    // dan ketika upgrade database. Untuk membuat database baru.
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(db_create);
    }

    // dijalankan apabila upgrade database.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DBHelper.class.getName(),"Upgrading database from version " + oldVersion + "to"
                        +newVersion+ ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}

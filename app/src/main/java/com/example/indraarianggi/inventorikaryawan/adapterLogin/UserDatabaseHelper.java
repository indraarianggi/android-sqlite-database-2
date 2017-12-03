package com.example.indraarianggi.inventorikaryawan.adapterLogin;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by indraarianggi on 03/12/17.
 */

public class UserDatabaseHelper extends SQLiteOpenHelper {

    public UserDatabaseHelper(Context context, String name,
                               SQLiteDatabase.CursorFactory factory, int version) {
        super(context,name, factory, version);
    }

    // Called when no database exist in disk
    // and the helper class needs to create a new one.
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UserDatabaseAdapter.DATABASE_CREATE);
    }

    // Called when there is a database version mismatch meaning
    // that the version of the database on disk needs to be upgraded to the current version
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Log the version upgrade.
        Log.w("TaskDBAdapter", "Upgrading from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + "TEMPLATE");
        // Create a new one.
        onCreate(db);
    }
}

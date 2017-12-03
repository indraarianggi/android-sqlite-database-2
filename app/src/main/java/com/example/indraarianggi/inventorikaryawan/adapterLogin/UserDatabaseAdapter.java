package com.example.indraarianggi.inventorikaryawan.adapterLogin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by indraarianggi on 03/12/17.
 */

public class UserDatabaseAdapter {

    // Inisialisasi nama database dan versi.
    static final String DATABASE_NAME = "user.db";
    static final int DATABASE_VERSION = 1;
    public static final int NAME_COLUMN = 1;

    // Statemen SQL untuk membuat database baru.
    static final String DATABASE_CREATE = "CREATE TABLE user(id INTEGER PRIMARY KEY AUTOINCREMENT," +
            " username TEXT, password TEXT);";

    // Variable to hold the database instance.
    public SQLiteDatabase db;
    // Context of the application using the database.
    private final Context context;
    // Database open/upgrade helper.
    private UserDatabaseHelper userDbHelper;

    public UserDatabaseAdapter(Context _context)
    {
        context = _context;
        userDbHelper = new UserDatabaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public UserDatabaseAdapter open() throws SQLException
    {
        db = userDbHelper.getWritableDatabase();
        return this;
    }

    public void close()
    {
        db.close();
    }

    public SQLiteDatabase getDatabaseInstance()
    {
        return db;
    }

    public void insertEntry(String userName, String password)
    {
        ContentValues newValues = new ContentValues();
        // Assign values for each row.
        newValues.put("username", userName);
        newValues.put("password", password);

        // Insert the row into your database table.
        db.insert("user", null, newValues);
    }

    public int deleteEntry(String userName)
    {
        // String id = String.valueOf(ID);
        String where = "username=?";
        int numberOfEntriesDeleted = db.delete("user", where, new String[]{userName});

        return numberOfEntriesDeleted;
    }

    public String getSingleEntry(String userName)
    {
        Cursor cursor = db.query("user", null, "username=?",
                new String[]{userName}, null, null, null);

        if (cursor.getCount() < 1) { // username not exist
            cursor.close();
            return "NOT EXIST";
        } else {
            cursor.moveToFirst();
            String password = cursor.getString(cursor.getColumnIndex("password"));
            cursor.close();

            return password;
        }
    }

    public void updateEntry(String userName, String password)
    {
        // Define the updated row content.
        ContentValues updatedValues = new ContentValues();
        // Assign values for each row.
        updatedValues.put("username", userName);
        updatedValues.put("password", password);

        String where = "username=?";
        db.update("user", updatedValues, where, new String[]{userName});
    }
}

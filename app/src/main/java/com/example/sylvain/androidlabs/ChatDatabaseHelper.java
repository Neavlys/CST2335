package com.example.sylvain.androidlabs;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ChatDatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "DatabaseFileName";
    public static final int VERSION_NUM = 1;
    public static final String TABLE_NAME = "Items";
    public static final String KEY_ID = "( _id INTEGER PRIMARY KEY AUTOINCREMENT,";
    public static final String KEY_MESSAGE = "MESSAGE";

    public ChatDatabaseHelper(Context ctx) {
        super(ctx, DATABASE_NAME, null, VERSION_NUM);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL( "CREATE TABLE " + TABLE_NAME + KEY_ID + KEY_MESSAGE + ")");
        Log.i("ChatDatabaseHelper", "Calling OnCreate");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        Log.i("ChatDatabaseHelper", "Calling OnUpdate, oldVersion=" + oldVersion + " newVersion=" + newVersion);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }
}

package com.noveogroup.googoltoone.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

// TODO: implements BaseColumns
public class OpenHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "googoltoone.db";
    private static final int DATABASE_VERSION = 1;
    private static OpenHelper instance = null;

    private OpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public synchronized static OpenHelper getInstance(Context context){
        if (instance == null) {
            instance = new OpenHelper(context);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // for players
        sqLiteDatabase.execSQL("CREATE TABLE " + ContentDescriptor.Players.TABLE_NAME + " ( " +
                ContentDescriptor.Players.Cols.ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                ContentDescriptor.Players.Cols.NAME + " TEXT NOT NULL" +
                " )");

        // TODO: for games

        // TODO: testing
        /*ContentValues cv = new ContentValues();
        for (int i = 1; i <= 3; i++) {
            cv.put(CONTACT_NAME, "name " + i);
            cv.put(CONTACT_EMAIL, "email " + i);
            db.insert(CONTACT_TABLE, null, cv);
        }*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if (oldVersion < newVersion) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ContentDescriptor.Players.TABLE_NAME);
            // TODO: for games
            onCreate(sqLiteDatabase);
        }
    }
}

package com.noveogroup.googoltoone.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

// TODO: implements BaseColumns
public class OpenHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "googoltoone.db";
    private static final int DATABASE_VERSION = 4;
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
        // for games
        sqLiteDatabase.execSQL("CREATE TABLE " + ContentDescriptor.Games.TABLE_NAME + " ( " +
                ContentDescriptor.Games.Cols.ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                ContentDescriptor.Games.Cols.ID_PLAYER1 + " INTEGER NOT NULL," +
                ContentDescriptor.Games.Cols.ID_PLAYER2 + " INTEGER NOT NULL," +
                ContentDescriptor.Games.Cols.PLAYER1_SCORE + " INTEGER NOT NULL," +
                ContentDescriptor.Games.Cols.PLAYER2_SCORE + " INTEGER NOT NULL," +
                ContentDescriptor.Games.Cols.TIME_FINISH + " DATETIME DEFAULT CURRENT_TIMESTAMP" +
                " )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if (oldVersion < newVersion) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ContentDescriptor.Players.TABLE_NAME);
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ContentDescriptor.Games.TABLE_NAME);
            onCreate(sqLiteDatabase);
        }
    }
}

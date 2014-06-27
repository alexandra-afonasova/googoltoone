package com.noveogroup.googoltoone.database;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

public class GTOContentProvider extends ContentProvider {
    private static final String LOG_TAG = GTOContentProvider.class.getSimpleName();
    private OpenHelper dbHelper;

    @Override
    public boolean onCreate() {
        dbHelper = OpenHelper.getInstance(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String orderBy) {
        int uriType = ContentDescriptor.uriMatcher.match(uri);
        String tableName = ContentDescriptor.getTableName(uriType);

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            if (tableName != ContentDescriptor.GamesWithPlayersNames.TABLE_NAME) {
                cursor = db.query(tableName, projection, selection, selectionArgs, null, null, orderBy);
            } else {
                // special raw Query
                String rawQuery = "SELECT" +
                            " b." + ContentDescriptor.Players.Cols.ID + "," +
                            " b." + ContentDescriptor.Players.Cols.NAME + " as player1_name," +
                            " c." + ContentDescriptor.Players.Cols.NAME + " as player2_name, " +
                            ContentDescriptor.Games.Cols.PLAYER1_SCORE + ", " +
                            ContentDescriptor.Games.Cols.PLAYER2_SCORE +
                        " FROM " + ContentDescriptor.Games.TABLE_NAME + " a " +
                        " INNER JOIN " + ContentDescriptor.Players.TABLE_NAME + " b " +
                        " ON " +
                            "a." + ContentDescriptor.Games.Cols.ID_PLAYER1 +
                            " = b." + ContentDescriptor.Players.Cols.ID +
                        " INNER JOIN " + ContentDescriptor.Players.TABLE_NAME + " c " +
                        " ON " +
                            "a." + ContentDescriptor.Games.Cols.ID_PLAYER2 +
                            " = c." + ContentDescriptor.Players.Cols.ID +
                        " ORDER BY " + ContentDescriptor.Games.Cols.TIME_FINISH + " DESC";
                cursor = db.rawQuery( rawQuery, null);
            }

            if (cursor != null) {
                cursor.setNotificationUri(getContext().getContentResolver(), uri);
            }
        }

        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        int uriType = ContentDescriptor.uriMatcher.match(uri);
        String tableName = ContentDescriptor.getTableName(uriType);

        if( tableName == ContentDescriptor.GamesWithPlayersNames.TABLE_NAME ){
            throw new RuntimeException("don't insert");
        }

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long id = 0;
        if (db != null) {
            id = db.insertWithOnConflict(tableName, null, contentValues, SQLiteDatabase.CONFLICT_IGNORE);
        }

        getContext().getContentResolver().notifyChange(uri, null);

        if (TextUtils.equals(tableName, ContentDescriptor.Players.TABLE_NAME)) {
            //ContentUris.withAppendedId(ContentDescriptor.Players.TABLE_URI, id);
            return Uri.parse(ContentDescriptor.Players.TABLE_URI + "/" + id);
        }else {
            return null;
        }
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int uriType = ContentDescriptor.uriMatcher.match(uri);
        String tableName = ContentDescriptor.getTableName(uriType);

        if( tableName == ContentDescriptor.GamesWithPlayersNames.TABLE_NAME ){
            throw new RuntimeException("don't insert");
        }

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int result = 0;
        if (db != null) {
            result = db.delete(tableName, selection, selectionArgs);
        }
        getContext().getContentResolver().notifyChange(uri, null);

        return result;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String selection, String[] selectionArgs) {
        int uriType = ContentDescriptor.uriMatcher.match(uri);
        String tableName = ContentDescriptor.getTableName(uriType);

        if( tableName == ContentDescriptor.GamesWithPlayersNames.TABLE_NAME ){
            throw new RuntimeException("don't insert");
        }

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int result = 0;
        try {
            if (db != null) {
                result = db.update(tableName, contentValues, selection, selectionArgs);
            }
        } catch (Throwable e) {
            Log.e(LOG_TAG, "Unable to update SQLiteDatabase");
            result = 0;
        }

        return result;
    }
}

package com.noveogroup.googoltoone.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.LoaderManager;
import android.content.AsyncQueryHandler;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;

import android.view.View;
import android.widget.*;
import com.noveogroup.googoltoone.database.ContentDescriptor;
import com.noveogroup.googoltoone.R;

// TODO: implement without support library yet
@SuppressLint("NewApi")
public class BestScoresActivity extends Activity implements LoaderManager.LoaderCallbacks<Cursor> {
    private SimpleCursorAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.best_scores);

        ListView listView = (ListView) findViewById(R.id.list);

        LoaderManager loaderManager = getLoaderManager();

        // TODO: for test
        insertData();

        // TODO: NAMEs for IDs
        String[] colomns = new String[]{
                ContentDescriptor.Games.Cols.ID_PLAYER1,
                ContentDescriptor.Games.Cols.PLAYER1_SCORE,
                ContentDescriptor.Games.Cols.PLAYER2_SCORE,
                ContentDescriptor.Games.Cols.ID_PLAYER2};
        int[] toFields = new int[]{
                R.id.item_player_one_name,
                R.id.item_player_one_score,
                R.id.item_player_two_score,
                R.id.item_player_two_name};

        adapter = new SimpleCursorAdapter(this, R.layout.best_scores_list_item, null, colomns, toFields, 0);
        listView.setAdapter(adapter);

        loaderManager.initLoader(0, null, this);
    }

    private void insertData() {
        Cursor cursor = getContentResolver().query(ContentDescriptor.Games.TABLE_URI, null, null, null, null);
        int count = cursor.getCount();
        if( count > 0 ){
            cursor.moveToLast();
            String id = cursor.getString(0);
            String idpl1 = cursor.getString(1);
            String idpl2 = cursor.getString(2);
            String scorepl1 = cursor.getString(3);
            String scorepl2 = cursor.getString(4);
            String time = cursor.getString(5);
            String time2 = cursor.getString(5);
        }


        ContentValues cvNewGame = new ContentValues();
        cvNewGame.put(ContentDescriptor.Games.Cols.ID_PLAYER1, 1);
        cvNewGame.put(ContentDescriptor.Games.Cols.ID_PLAYER2, 2);
        cvNewGame.put(ContentDescriptor.Games.Cols.PLAYER1_SCORE, 567 );
        cvNewGame.put(ContentDescriptor.Games.Cols.PLAYER2_SCORE, 765 );

        new AsyncQueryHandler(getContentResolver()) {
        }.startInsert(1, null, ContentDescriptor.Games.TABLE_URI, cvNewGame);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(BestScoresActivity.this, ContentDescriptor.Players.TABLE_URI, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        if (adapter != null && cursor != null) {
            adapter.changeCursor(cursor);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {
        if (adapter != null) {
            adapter.changeCursor(null);
        }
    }
}
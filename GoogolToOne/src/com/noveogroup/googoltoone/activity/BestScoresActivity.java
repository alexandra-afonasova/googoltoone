package com.noveogroup.googoltoone.activity;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import com.noveogroup.googoltoone.R;
import com.noveogroup.googoltoone.database.ContentDescriptor;


public class BestScoresActivity extends FragmentActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private SimpleCursorAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.best_scores);

        String playerOneName = "player1_name";
        String playerTwoName = "player2_name";

        ListView listView = (ListView) findViewById(R.id.list);

        LoaderManager loaderManager = getSupportLoaderManager();

        String[] colomns = new String[]{
                playerOneName,
                ContentDescriptor.Games.Cols.PLAYER1_SCORE,
                ContentDescriptor.Games.Cols.PLAYER2_SCORE,
                playerTwoName};

        int[] toFields = new int[]{
                R.id.item_player_one_name,
                R.id.item_player_one_score,
                R.id.item_player_two_score,
                R.id.item_player_two_name};

        adapter = new SimpleCursorAdapter(this, R.layout.best_scores_list_item, null, colomns, toFields, 0);
        listView.setAdapter(adapter);

        //loaderManager.initLoader(0, null, this);
        loaderManager.initLoader(0, null, this );
    }

    @Override
    public android.support.v4.content.Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new android.support.v4.content.CursorLoader(BestScoresActivity.this, ContentDescriptor.GamesWithPlayersNames.TABLE_URI, null, null, null, null);
    }

    @Override
    public void onLoadFinished(android.support.v4.content.Loader<Cursor> cursorLoader, Cursor cursor) {
        if (adapter != null && cursor != null) {
            adapter.changeCursor(cursor);
        }
    }

    @Override
    public void onLoaderReset(android.support.v4.content.Loader<Cursor> cursorLoader) {
        if (adapter != null) {
            adapter.changeCursor(null);
        }
    }
}
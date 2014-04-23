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
    EditText oneItemEditText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.best_scores);

        ListView listView = (ListView) findViewById(R.id.list);
        //oneItemEditText = (EditText) findViewById(R.id.item);

        //? зачем?
        LoaderManager loaderManager = getLoaderManager();

        // only for testing
        Button insert = (Button) findViewById(R.id.insert);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
            }
        });

        String[] colomns = new String[]{ContentDescriptor.Players.Cols.NAME};
        int[] toFields = new int[]{R.id.item};

        //? зачем?
        adapter = new SimpleCursorAdapter(this, R.layout.best_scores_list_item, null, colomns, toFields, 0);
        listView.setAdapter(adapter);

        //? зачем?
        loaderManager.initLoader(0, null, this);
    }

    private void insertData() {
        //if (!TextUtils.isEmpty(oneItemEditText.getText())) {
            ContentValues values = new ContentValues();
            values.put(ContentDescriptor.Players.Cols.NAME, "Vasa");

            new AsyncQueryHandler(getContentResolver()) {
            }.startInsert(1, null, ContentDescriptor.Players.TABLE_URI, values);
        //}else {
        //    Toast.makeText(this, "Fields can't be empty", Toast.LENGTH_LONG).show();
        //}
    }

    //? зачем?
    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(BestScoresActivity.this, ContentDescriptor.Players.TABLE_URI, null, null, null, null);
    }

    //? зачем?
    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        if (adapter != null && cursor != null) {
            adapter.changeCursor(cursor);
        }
    }

    //? зачем?
    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {
        if (adapter != null) {
            adapter.changeCursor(null);
        }
    }
}
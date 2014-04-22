package com.noveogroup.googoltoone;

import android.app.Activity;
import android.app.LoaderManager;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class BestScoresActivity extends Activity {
    private LoaderManager loaderManager;
    private SimpleCursorAdapter adapter;

    private EditText oneItemEditText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.best_scores);

        ListView listView = (ListView) findViewById(R.id.list);
        oneItemEditText = (EditText) findViewById(R.id.item);

        loaderManager = getLoaderManager();

        // only for testing
        Button insert = (Button) findViewById(R.id.insert);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
            }
        });
    }

    private void insertData() {
        Toast.makeText(this, "insert", Toast.LENGTH_LONG).show();
    }
}
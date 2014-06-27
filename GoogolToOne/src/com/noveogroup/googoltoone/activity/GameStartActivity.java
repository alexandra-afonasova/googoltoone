package com.noveogroup.googoltoone.activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import com.noveogroup.googoltoone.R;
import com.noveogroup.googoltoone.database.ContentDescriptor;

import java.util.ArrayList;

public class GameStartActivity extends android.app.Activity {

    private AutoCompleteTextView player1;
    private AutoCompleteTextView player2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gamestart);

        player1 = (AutoCompleteTextView) findViewById(R.id.player1);
        player2 = (AutoCompleteTextView) findViewById(R.id.player2);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, getNamesFromDB());
        player1.setAdapter(adapter);
        player2.setAdapter(adapter);

        final Button startButton = (Button) findViewById(R.id.startButton);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startOnClickEvent();
            }
        });

    }

    private void startOnClickEvent() {
        Intent intent = new Intent(GameStartActivity.this, GameBackgroundFragmentActivity.class);
        String player1Name = player1.getText().toString();
        String player2Name = player2.getText().toString();

        //CRDone use TextUtils.isEmpty(player1Name)
        if(TextUtils.isEmpty(player1Name)) {
            player1Name = getString(R.string.player1);  //CRDone move strings to resources
        }
        if(TextUtils.isEmpty(player2Name)) {
            player2Name = getString(R.string.player2);
        }
        intent.putExtra(GameBackgroundFragmentActivity.PLAYER1_TAG, player1Name);
        intent.putExtra(GameBackgroundFragmentActivity.PLAYER2_TAG, player2Name);
        startActivity(intent);
    }

    private String[] getNamesFromDB () {
        ArrayList<String> names = new ArrayList<String>();

        Cursor cursor = getContentResolver().query(ContentDescriptor.Players.TABLE_URI, new String[] {ContentDescriptor.Players.Cols.NAME}, null, null, null);
        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            names.add(cursor.getString(0));
        }
        return names.toArray(new String[names.size()]);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        player1 = null;
        player2 = null;
    }
}
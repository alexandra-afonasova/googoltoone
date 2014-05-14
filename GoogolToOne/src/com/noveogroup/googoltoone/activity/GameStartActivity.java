package com.noveogroup.googoltoone.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import com.noveogroup.googoltoone.R;
import com.noveogroup.googoltoone.database.ContentDescriptor;
import com.noveogroup.googoltoone.database.OpenHelper;

import java.util.ArrayList;

public class GameStartActivity extends android.app.Activity {

    //CRDone Remove these tags and use their from GameBackgroungFragmentActivity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gamestart);

        ArrayList<String> names = new ArrayList<String>();
        OpenHelper helper = OpenHelper.getInstance(this);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query("players", new String[] {ContentDescriptor.Players.Cols.NAME}, null, null, null, null, null);
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            names.add(cursor.getString(0));
        }
        String[] namesArray = names.toArray(new String[names.size()]);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, namesArray);
        AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.autocomplete);
        textView.setAdapter(adapter);

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
        final EditText player1 = (EditText) findViewById(R.id.player1);
        final EditText player2 = (EditText) findViewById(R.id.player2);
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

}
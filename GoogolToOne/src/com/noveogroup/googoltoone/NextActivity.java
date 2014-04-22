package com.noveogroup.googoltoone;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class NextActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Testing. Remove this Activity when no more needed

        super.onCreate(savedInstanceState);
        setContentView(R.layout.next);

        TextView text1 = (TextView) findViewById(R.id.text1);
        TextView text2 = (TextView) findViewById(R.id.text2);
        Intent intent = getIntent();

        String player1 = intent.getStringExtra(GameStartActivity.player1Tag);
        String player2 = intent.getStringExtra(GameStartActivity.player2Tag);

        text1.setText(player1);
        text2.setText(player2);

    }
}

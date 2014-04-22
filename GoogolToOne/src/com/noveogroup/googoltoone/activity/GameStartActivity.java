package com.noveogroup.googoltoone.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.noveogroup.googoltoone.R;

public class GameStartActivity extends Activity {

    //Extras tags
    public static final String player1Tag = "player1";
    public static final String player2Tag = "player2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gamestart);

        final Button startButton = (Button) findViewById(R.id.startButton);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startOnClickEvent();
            }
        });

    }

    private void startOnClickEvent() {
        Intent intent = new Intent(GameStartActivity.this, NextActivity.class);
        final EditText player1 = (EditText) findViewById(R.id.player1);
        final EditText player2 = (EditText) findViewById(R.id.player2);
        String player1Name = player1.getText().toString();
        String player2Name = player2.getText().toString();
        if(player1Name.equals("")) {
            player1Name = "Player 1";
        }
        if(player2Name.equals("")) {
            player2Name = "Player 2";
        }
        intent.putExtra(player1Tag, player1Name);
        intent.putExtra(player2Tag, player2Name);
        startActivity(intent);
    }

}
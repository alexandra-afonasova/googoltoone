package com.noveogroup.googoltoone;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class StartupActivity extends Activity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        final Button playButton = (Button) findViewById(R.id.playButton);
        final Button highscoresButton = (Button) findViewById(R.id.highscoresButton);
        final Button rulesButton = (Button) findViewById(R.id.rulesButton);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPlayClickEvent();
            }
        });
        highscoresButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onHighScoresClickEvent();
            }
        });
        rulesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRulesClickEvent();
            }
        });

    }

    private void onPlayClickEvent() {
        Intent intent = new Intent(StartupActivity.this, GameStartActivity.class);
        startActivity(intent);
    }

    private void onHighScoresClickEvent() {
        //CR move message string to resources
        //Placeholder
        //Toast.makeText(getApplicationContext(), "Not implemented yet", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(StartupActivity.this, BestScoresActivity.class);
        startActivity(intent);
    }

    private void onRulesClickEvent() {
        Intent intent = new Intent(StartupActivity.this, RulesActivity.class);
        startActivity(intent);
    }

}
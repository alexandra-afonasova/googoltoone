package com.noveogroup.googoltoone.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.noveogroup.googoltoone.R;

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
        if (isOnline()) {
            Intent intent = new Intent(StartupActivity.this, GameStartActivity.class);
            startActivity(intent);
        } else {
            popDialog();
        }
    }

    private void onHighScoresClickEvent() {
        Intent intent = new Intent(StartupActivity.this, BestScoresActivity.class);
        startActivity(intent);
    }

    private void onRulesClickEvent() {
        Intent intent = new Intent(StartupActivity.this, RulesActivity.class);
        startActivity(intent);
    }

    private boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }

    //CR Use DialogFragment
    private void popDialog() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.no_connection_title)
                .setMessage(R.string.no_connection_message)
                .setNeutralButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .show();
    }

}
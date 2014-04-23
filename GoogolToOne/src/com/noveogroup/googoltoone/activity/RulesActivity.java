package com.noveogroup.googoltoone.activity;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.noveogroup.googoltoone.R;

public class RulesActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rules);

        final Button soundButton = (Button) findViewById(R.id.soundButton);
        final MediaPlayer sound = MediaPlayer.create(RulesActivity.this, R.raw.errorsound);

        soundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sound.start();
            }
        });

    }

}
package com.noveogroup.googoltoone.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.noveogroup.googoltoone.R;
import com.noveogroup.googoltoone.googleAPI.GoogleSuggestion;

public class NextActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Testing. Remove this Activity when no more needed

        super.onCreate(savedInstanceState);
        setContentView(R.layout.next);

        TextView text1 = (TextView) findViewById(R.id.text1);
        TextView text2 = (TextView) findViewById(R.id.text2);
        final EditText query = (EditText) findViewById(R.id.query);
        Button suggest = (Button) findViewById(R.id.suggest);
        Intent intent = getIntent();

        String player1 = intent.getStringExtra(GameStartActivity.player1Tag);
        String player2 = intent.getStringExtra(GameStartActivity.player2Tag);

        text1.setText(player1);
        text2.setText(player2);

        suggest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new GoogleSuggestion().execute(query.getText().toString());
            }
        });
    }
}

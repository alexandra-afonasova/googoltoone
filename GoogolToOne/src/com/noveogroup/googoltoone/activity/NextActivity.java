package com.noveogroup.googoltoone.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import com.noveogroup.googoltoone.R;
import com.noveogroup.googoltoone.googleAPI.GoogleSuggestion;

public class NextActivity extends android.app.Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Testing. Remove this Activity when no more needed

        super.onCreate(savedInstanceState);
        setContentView(R.layout.next);

        TextView text1 = (TextView) findViewById(R.id.text1);
        TextView text2 = (TextView) findViewById(R.id.text2);
        final EditText query = (EditText) findViewById(R.id.query);
        Intent intent = getIntent();

        String player1 = intent.getStringExtra(GameStartActivity.player1Tag);
        String player2 = intent.getStringExtra(GameStartActivity.player2Tag);

        text1.setText(player1);
        text2.setText(player2);

        query.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                EditText query = (EditText) NextActivity.this.findViewById(R.id.query);
                if(query.getText() != null) {
                    new GoogleSuggestion(NextActivity.this).execute(query.getText().toString());
                }
            }
        });

    }
}

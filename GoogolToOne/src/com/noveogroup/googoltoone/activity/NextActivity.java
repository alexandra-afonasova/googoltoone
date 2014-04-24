package com.noveogroup.googoltoone.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;
import com.noveogroup.googoltoone.R;
import com.noveogroup.googoltoone.ScoreUpdater;
import com.noveogroup.googoltoone.fragment.QueryFragment;

public class NextActivity extends FragmentActivity implements ScoreUpdater {

    //CR Move tags to Activity
    //Extras tags
    public static final String player1Tag = "player1";
    public static final String player2Tag = "player2";

    private String playerOneName;
    private String playerTwoName;

    private TextView scoreTextView;
    public boolean ifFirst = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Testing. Remove this Activity when no more needed

        super.onCreate(savedInstanceState);
        setContentView(R.layout.next_with_fragment);

        scoreTextView = (TextView) findViewById(R.id.score);
        Intent intent = getIntent();
        playerOneName = intent.getStringExtra(player1Tag);
        playerTwoName = intent.getStringExtra(player2Tag);

        updateScore(0, 0);

        if(savedInstanceState != null) {
            return;
        }

        QueryFragment queryFragment = new QueryFragment();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, queryFragment).commit();

      }

    public void Fuction() {

    }

    @Override
    public void updateScore(int playerOneScore, int playerTwoScore) {
        scoreTextView.setText( getString(R.string.score_fmt, playerOneScore, playerTwoScore, playerOneName, playerTwoName) );
    }
}

package com.noveogroup.googoltoone.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;
import com.noveogroup.googoltoone.R;
import com.noveogroup.googoltoone.ScoreUpdater;
import com.noveogroup.googoltoone.fragment.QueryFragment;
import com.noveogroup.googoltoone.gamelogic.GameInfo;

public class NextActivity extends FragmentActivity implements ScoreUpdater {

    private GameInfo gameInfo;

    //CR Move tags to Activity
    //Extras tags
    public static final String player1Tag = "player1";
    public static final String player2Tag = "player2";

    private String playerOneName;
    private String playerTwoName;

    private TextView scoreTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Testing. Remove this Activity when no more needed

        super.onCreate(savedInstanceState);
        setContentView(R.layout.next_with_fragment);

        scoreTextView = (TextView) findViewById(R.id.score);
        Intent intent = getIntent();
        playerOneName = intent.getStringExtra(player1Tag);
        playerTwoName = intent.getStringExtra(player2Tag);

        gameInfo = new GameInfo(playerOneName, playerTwoName);
        updateScore();

        if(savedInstanceState != null) {
            return;
        }

        QueryFragment queryFragment = new QueryFragment();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, queryFragment).commit();

      }

    public GameInfo getGameInfo() {
        return gameInfo;
    }

    @Override
    public void updateScore() {
        scoreTextView.setText( getString(R.string.score_fmt,
                gameInfo.getCurrentScoreOnePlayer(), gameInfo.getCurrentScoreTwoPlayer(),
                playerOneName, playerTwoName) );
    }
}

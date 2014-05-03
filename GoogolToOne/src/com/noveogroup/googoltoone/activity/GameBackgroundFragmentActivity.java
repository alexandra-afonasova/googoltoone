package com.noveogroup.googoltoone.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;
import com.noveogroup.googoltoone.R;
import com.noveogroup.googoltoone.ScoreUpdater;
import com.noveogroup.googoltoone.fragment.FragmentUtils;
import com.noveogroup.googoltoone.fragment.QueryFragment;
import com.noveogroup.googoltoone.gamelogic.GameInfo;

//CRDONE fix typo
public class GameBackgroundFragmentActivity extends FragmentActivity implements ScoreUpdater {

    private GameInfo gameInfo;

    //Extras tags
    public static final String PLAYER1_TAG = "player1";
    public static final String PLAYER2_TAG = "player2";

    private String playerOneName;
    private String playerTwoName;

    private TextView scoreTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.next_with_fragment);

        scoreTextView = (TextView) findViewById(R.id.score);
        Intent intent = getIntent();
        playerOneName = intent.getStringExtra(PLAYER1_TAG);
        playerTwoName = intent.getStringExtra(PLAYER2_TAG);

        gameInfo = new GameInfo();
        updateScore();

        if (savedInstanceState != null) {
            return;
        }

        //CRDONE Create and use method newInstance()
        Fragment queryFragment = QueryFragment.newInstance();

        //CRDONE use FragmentUtils.startFragment()
        FragmentUtils.startFragment( queryFragment, getSupportFragmentManager() );
    }

    public GameInfo getGameInfo() {
        return gameInfo;
    }

    @Override
    public void updateScore() {
        scoreTextView.setText(getString(R.string.score_fmt,
                gameInfo.getCurrentScoreOnePlayer(), gameInfo.getCurrentScoreTwoPlayer(),
                playerOneName, playerTwoName));
    }
}

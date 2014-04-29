package com.noveogroup.googoltoone.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;
import com.noveogroup.googoltoone.R;
import com.noveogroup.googoltoone.ScoreUpdater;
import com.noveogroup.googoltoone.fragment.QueryFragment;
import com.noveogroup.googoltoone.gamelogic.GameInfo;

//CRDONE Names of classes which extends FragmentActivity should have the postfix "FragmentActivity"
//CRDONE use more informative name for activity
public class GameBackgroungFragmentActivity extends FragmentActivity implements ScoreUpdater {

    private GameInfo gameInfo;

    //CRDONE Use Java Naming Standards for Constant values
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

        gameInfo = new GameInfo(playerOneName, playerTwoName);
        updateScore();

        if (savedInstanceState != null) {
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
        scoreTextView.setText(getString(R.string.score_fmt,
                gameInfo.getCurrentScoreOnePlayer(), gameInfo.getCurrentScoreTwoPlayer(),
                playerOneName, playerTwoName));
    }
}

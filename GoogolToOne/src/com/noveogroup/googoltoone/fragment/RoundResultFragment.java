package com.noveogroup.googoltoone.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.noveogroup.googoltoone.R;
import com.noveogroup.googoltoone.activity.GameBackgroundFragmentActivity;
import com.noveogroup.googoltoone.gamelogic.GameInfo;
import com.noveogroup.googoltoone.gamelogic.RoundInfo;

public class RoundResultFragment extends Fragment {

    private GameBackgroundFragmentActivity parentActivity;
    private RoundInfo roundInfo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.round_result, container, false);

        parentActivity = (GameBackgroundFragmentActivity) getActivity();

        Button nextRoundBtn = (Button) view.findViewById(R.id.next_round);
        nextRoundBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (parentActivity.getGameInfo().getAndIncreaseRoundNumber() != GameInfo.NUMBER_ROUNDS) {
                    // switch to the next round
                    Fragment newQuery = QueryFragment.newInstance();
                    FragmentUtils.startFragment(newQuery, getFragmentManager());
                } else {
                    // switch to the end game fragment
                    Fragment endGame = EndGameFragment.newInstance();
                    FragmentUtils.startFragment(endGame, getFragmentManager());
                }
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        roundInfo = ((GameBackgroundFragmentActivity)getActivity()).getGameInfo().getCurrentRound();

        TextView score = (TextView) getView().findViewById(R.id.results);
        TextView nextPlayer = (TextView) getView().findViewById(R.id.next_player);
        TextView RoundsRemaining = (TextView) getView().findViewById(R.id.rounds_remaining);

        score.setText(getResult());
        if(parentActivity.getGameInfo().getNextPlayer()) {
            nextPlayer.append(" " + parentActivity.getGameInfo().getPlayerOneName());
        }
        else {
            nextPlayer.append(" " + parentActivity.getGameInfo().getPlayerTwoName());
        }

        int rounds = GameInfo.NUMBER_ROUNDS - parentActivity.getGameInfo().getCurrentNumberRound();

        RoundsRemaining.append(" " + rounds);

    }

    public static Fragment newInstance() {
        return new RoundResultFragment();
    }

    private String getResult() {
        int score = roundInfo.getRoundScoreAnswerer();
        int answers = roundInfo.getCurrentAnswers();
        StringBuilder builder = new StringBuilder();
        builder.append(getString(R.string.result1_ro_res));
        builder.append(" ");
        builder.append(answers);
        builder.append(" ");
        switch (answers) {
            case 1: builder.append(getString(R.string.result2_singular_ro_res));
                    break;
            case 2:
            case 3:
            case 4: builder.append(getString(R.string.result2_rus_ro_res));
                    break;
            case 5:
            case 0: builder.append(getString(R.string.result2_multiple_ro_res));
                    break;
        }
        builder.append(" ");
        builder.append(getString(R.string.result3_ro_res));
        builder.append(" ");
        builder.append(score);
        builder.append(" ");
        builder.append(getString(R.string.result4_ro_res));
        return builder.toString();
    }

}
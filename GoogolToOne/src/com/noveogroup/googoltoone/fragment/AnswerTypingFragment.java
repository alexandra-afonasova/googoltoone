package com.noveogroup.googoltoone.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.noveogroup.googoltoone.R;
import com.noveogroup.googoltoone.ScoreUpdater;
import com.noveogroup.googoltoone.activity.GameBackgroundFragmentActivity;
import com.noveogroup.googoltoone.gamelogic.RoundInfo;

public class AnswerTypingFragment extends Fragment {
    //CRDONE Better to use formatter string in from resources
    private TextView beginRequestTV;
    private EditText answerET;
    private Button checkBtn;

    private RoundInfo roundInfo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.answer_typing, container, false);

        GameBackgroundFragmentActivity parentActivity = (GameBackgroundFragmentActivity) getActivity();
        //CR Better if GameInfo were implement Parcelable and give gameInfo in arguments. Or use singleton pattern for GameInfo and RoundInf0
        roundInfo = parentActivity.getGameInfo().getCurrentRound();

        beginRequestTV = (TextView) view.findViewById(R.id.begin_request);
        answerET = (EditText) view.findViewById(R.id.answer);

        beginRequestTV.setText( getString(R.string.actual_beginning_request_fmt_ans_type, roundInfo.getBeginRequest()) );

        checkBtn = (Button) view.findViewById(R.id.checkButton);
        checkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCheckBtnClick();
            }
        });

        return view;
    }

    private void onCheckBtnClick() {
        // check input with set of correct answer
        String answerStr = answerET.getText().toString();

        // if correct guessed
        if( roundInfo.isCheckAnswer(roundInfo.getBeginRequest() + answerStr) ){
            //CRDONE Use string formatter. Add space before scores
            Toast.makeText( getActivity(), getResources().getString(R.string.check_answer_correct_fmt_ans_typ, roundInfo.getLastAddScore() ), Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText( getActivity(), getResources().getString(R.string.check_answer_try_again_ans_typ), Toast.LENGTH_SHORT).show();
        }

        //CRDONE Cast to ScoreUpdater interface
        ScoreUpdater parentActivity = (ScoreUpdater) getActivity();
        parentActivity.updateScore();

        // if round end then switch fragment
        if( roundInfo.reduceAttempts() ){
            // switch next to round end
            //CRDONE Create and use newInstance() method instead of constructor
            Fragment roundResult = RoundResultFragment.newInstance();

            FragmentUtils.startFragment( roundResult, getFragmentManager() );
            //CRDONE You did this into FragmentUtils.startFragment()
        }
    }

    public static Fragment newInstance() {
        return new AnswerTypingFragment();
    }
}
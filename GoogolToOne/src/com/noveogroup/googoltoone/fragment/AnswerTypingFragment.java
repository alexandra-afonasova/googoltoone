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
import com.noveogroup.googoltoone.activity.NextActivity;
import com.noveogroup.googoltoone.gamelogic.RoundInfo;

public class AnswerTypingFragment extends Fragment {
    private static final String THREE_DOTS = "...";

    //CR do not store pointer to the activity. Use local variable.
    private NextActivity parentActivity;

    private TextView beginRequestTV;
    private EditText answerET;
    private Button checkBtn;

    private RoundInfo roundInfo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.answer_typing, container, false);

        parentActivity = (NextActivity) getActivity();
        //CR Better if GameInfo were implement Parcelable and give gameInfo in arguments. Or use singleton pattern for GameInfo and RoundInf0
        roundInfo = parentActivity.getGameInfo().getCurrentRound();

        beginRequestTV = (TextView) view.findViewById(R.id.begin_request);
        answerET = (EditText) view.findViewById(R.id.answer);

        beginRequestTV.setText( roundInfo.getBeginRequest() + THREE_DOTS );

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

        //CR move strings to resources
        // if correct guessed
        if( roundInfo.checkAnswer( roundInfo.getBeginRequest() + answerStr) ){
            Toast.makeText( getActivity(), "Correct! You earn " + roundInfo.getLastAddScore(), Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText( getActivity(), "Try again!", Toast.LENGTH_SHORT).show();
        }

        parentActivity.updateScore();

        // if round end then switch fragment
        if( roundInfo.reduceAffort() ){
            // switch next to round end
            Fragment roundResult = new RoundResultFragment();
            getFragmentManager().beginTransaction().replace(R.id.fragment_container, roundResult).commit(); //CR Create class FragmentUtils and create methods which do this.
        }
    }
}
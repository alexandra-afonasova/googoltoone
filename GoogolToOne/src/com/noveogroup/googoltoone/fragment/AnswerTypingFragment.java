package com.noveogroup.googoltoone.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.noveogroup.googoltoone.R;
import com.noveogroup.googoltoone.activity.NextActivity;
import com.noveogroup.googoltoone.gamelogic.RoundInfo;

public class AnswerTypingFragment extends Fragment {
    private TextView answerTV;
    private Button checkBtn;

    private RoundInfo roundInfo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.answer_typing, container, false);

        roundInfo = ((NextActivity) getActivity()).getGameInfo().getCurrentRound();

        answerTV = (TextView) view.findViewById(R.id.answer);

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
        String answerStr = answerTV.getText().toString();

        // if correct guessed
        if( roundInfo.checkAnswer(answerStr) ){
            Toast.makeText( getActivity(), "Correct!", Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText( getActivity(), "Try again!", Toast.LENGTH_SHORT).show();
        }

        //TODO: if round end then switch fragment

        // switch next to round end
        /*Fragment roundResult = new RoundResultFragment();
        getFragmentManager().beginTransaction().replace(R.id.fragment_container, roundResult).commit();*/
    }
}
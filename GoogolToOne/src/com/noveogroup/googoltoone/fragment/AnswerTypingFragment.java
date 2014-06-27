package com.noveogroup.googoltoone.fragment;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.noveogroup.googoltoone.R;
import com.noveogroup.googoltoone.ScoreUpdater;
import com.noveogroup.googoltoone.activity.GameBackgroundFragmentActivity;
import com.noveogroup.googoltoone.gamelogic.RoundInfo;

public class AnswerTypingFragment extends Fragment {

    private TextView beginRequestTV;
    private EditText answerET;
    private Button checkBtn;

    private RoundInfo roundInfo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.answer_typing, container, false);

        GameBackgroundFragmentActivity parentActivity = (GameBackgroundFragmentActivity) getActivity();

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
        if( roundInfo.isAnswerCorrect(roundInfo.getBeginRequest() + " " + answerStr) ){

            Toast.makeText( getActivity(), getResources().getString(R.string.check_answer_correct_fmt_ans_typ, roundInfo.getLastAddScore() ), Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText( getActivity(), getResources().getString(R.string.check_answer_try_again_ans_typ), Toast.LENGTH_SHORT).show();

            // error sounds
            MediaPlayer sound = MediaPlayer.create(getActivity(), R.raw.errorsound);
            sound.start();

            // lighting errors images
            int imgId = R.id.error_img1;
            switch ( roundInfo.getCurrentNumberOfErrors() ){
                case 2:
                    imgId = R.id.error_img2;
                    break;
                case 3:
                    imgId = R.id.error_img3;
                    break;
            }
            ImageView curErrorImg = (ImageView) getActivity().findViewById( imgId );
            curErrorImg.setImageResource( R.drawable.error_light );
        }

        ScoreUpdater parentActivity = (ScoreUpdater) getActivity();
        parentActivity.updateScore();

        // if round end then switch fragment
        if( roundInfo.reduceAttempts() || roundInfo.getCurrentNumberOfErrors() == 3 ){
            // switch next to round end
            Fragment roundResult = RoundResultFragment.newInstance();

            FragmentUtils.startFragment( roundResult, getFragmentManager() );
        }
    }

    public static Fragment newInstance() {
        return new AnswerTypingFragment();
    }
}
package com.noveogroup.googoltoone.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.noveogroup.googoltoone.R;
import com.noveogroup.googoltoone.activity.GameBackgroundFragmentActivity;
import com.noveogroup.googoltoone.gamelogic.RoundInfo;
import com.noveogroup.googoltoone.googleAPI.GoogleSuggestionTask;

public class QueryFragment extends Fragment {
    private RoundInfo roundInfo;

    private EditText query;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.enter_query, container,false);
    }

    @Override
    public void onStart() {
        super.onStart();

        query = (EditText) getView().findViewById(R.id.query);
        Button continueButton = (Button) getView().findViewById(R.id.continue_button);
        continueButton.setEnabled(false);

        roundInfo = new RoundInfo();
        // TODO: what is correct way?  CR Try to use singleton?
        ((GameBackgroundFragmentActivity)getActivity()).getGameInfo().setCurrentRound( roundInfo );

        query.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                new GoogleSuggestionTask(QueryFragment.this, roundInfo).execute(s.toString());
            }
        });

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roundInfo.setBeginRequest( query.getText().toString() );

                //replace this fragment with the answer fragment
                Fragment answerTyping = AnswerTypingFragment.newInstance(); //CRDONE Create and use method newInstance()

                //CRDONE Use FragmentUtils.startFragment()
                FragmentUtils.startFragment( answerTyping, getFragmentManager() );
            }
        });

    }

    public static Fragment newInstance() {
        return new QueryFragment();
    }
}
package com.noveogroup.googoltoone.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.noveogroup.googoltoone.R;


public class AnswerTypingFragment extends Fragment {
    private Button checkBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.answer_typing, container, false);

        checkBtn = (Button) view.findViewById(R.id.checkButton);
        checkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: check input with set of correct answer

                //TODO: if round end then switch fragment

                // switch next to round end
                Fragment roundResult = new RoundResultFragment();
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, roundResult).commit();
            }
        });

        return view;
    }
}
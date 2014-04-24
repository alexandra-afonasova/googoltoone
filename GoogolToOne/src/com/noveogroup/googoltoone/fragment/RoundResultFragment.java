package com.noveogroup.googoltoone.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.noveogroup.googoltoone.R;

public class RoundResultFragment extends Fragment {
    private Button nextRoundBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.round_result, container, false);

        nextRoundBtn = (Button) view.findViewById(R.id.next_round);
        nextRoundBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // switch to the next round
                Fragment newQuery = new QueryFragment();
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, newQuery).commit();
            }
        });

        return view;
    }
}
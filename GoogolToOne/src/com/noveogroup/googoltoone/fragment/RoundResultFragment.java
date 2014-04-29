package com.noveogroup.googoltoone.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.noveogroup.googoltoone.R;
import com.noveogroup.googoltoone.activity.GameBackgroungFragmentActivity;
import com.noveogroup.googoltoone.gamelogic.GameInfo;

public class RoundResultFragment extends Fragment {

    private GameBackgroungFragmentActivity parentActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.round_result, container, false);

        parentActivity = (GameBackgroungFragmentActivity) getActivity();

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
}
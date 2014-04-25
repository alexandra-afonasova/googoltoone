package com.noveogroup.googoltoone.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.noveogroup.googoltoone.R;
import com.noveogroup.googoltoone.activity.GameStartActivity;
import com.noveogroup.googoltoone.activity.NextActivity;
import com.noveogroup.googoltoone.activity.StartupActivity;
import com.noveogroup.googoltoone.gamelogic.GameInfo;

public class EndGameFragment extends Fragment {
    private NextActivity parentActivity;

    private Button againBtn;
    private Button enoughBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.end_game, container, false);

        parentActivity = (NextActivity) getActivity();

        againBtn = (Button) view.findViewById(R.id.againBtn);
        againBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( getActivity(), NextActivity.class);
                startActivity(intent);
            }
        });

        enoughBtn = (Button) view.findViewById(R.id.enoughBtn);
        enoughBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), StartupActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
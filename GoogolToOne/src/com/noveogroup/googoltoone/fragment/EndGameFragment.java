package com.noveogroup.googoltoone.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.noveogroup.googoltoone.R;
import com.noveogroup.googoltoone.activity.GameBackgroundFragmentActivity;
import com.noveogroup.googoltoone.activity.StartupActivity;

public class EndGameFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.end_game, container, false);

        Button againBtn = (Button) view.findViewById(R.id.again_btn);
        againBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), GameBackgroundFragmentActivity.class);
                startActivity(intent);
            }
        });

        Button enoughBtn = (Button) view.findViewById(R.id.enough_btn);
        enoughBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), StartupActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    public static Fragment newInstance() {
        return new EndGameFragment();
    }
}
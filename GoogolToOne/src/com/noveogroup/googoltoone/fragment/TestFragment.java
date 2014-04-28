package com.noveogroup.googoltoone.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.noveogroup.googoltoone.R;

/**
 * Created by Sandra on 23.04.2014.
 */
//CR remove if unused
public class TestFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.enter_query, container,false);

    }

    @Override
    public void onStart() {
        super.onStart();
        TextView results = (TextView) getView().findViewById(R.id.results);
        results.setText("Lorem ipsum");
    }
}

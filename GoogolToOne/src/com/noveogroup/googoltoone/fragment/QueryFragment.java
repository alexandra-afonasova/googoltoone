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
import com.noveogroup.googoltoone.googleAPI.GoogleSuggestion;

public class QueryFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.enter_query, container,false);

    }

    @Override
    public void onStart() {
        super.onStart();

        EditText query = (EditText) getView().findViewById(R.id.query);
        Button continueButton = (Button) getView().findViewById(R.id.continue_button);

        query.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                new GoogleSuggestion(QueryFragment.this).execute(s.toString());
            }
        });

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //replace this fragment with the next one

                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new TestFragment(), null)
                        .commit();
            }
        });

    }
}